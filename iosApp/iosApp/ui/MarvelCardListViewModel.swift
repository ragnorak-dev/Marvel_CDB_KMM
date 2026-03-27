import Foundation
import shared

@MainActor
class MarvelViewModel: ObservableObject {

    enum LoadState { case idle, loading, error(String) }

    @Published var heroes: [MarvelCardModel] = []
    @Published var favorites: [MarvelCardModel] = []
    @Published var loadState: LoadState = .idle

    private let provider = MarvelUseCaseProvider()
    private var favoritesTask: Task<Void, Never>?

    var favoriteCodes: Set<String> { Set(favorites.map { $0.code }) }

    func isFavorite(_ hero: MarvelCardModel) -> Bool {
        favoriteCodes.contains(hero.code)
    }

    // MARK: - Load heroes list (SKIE bridges suspend → async throws)

    func loadHeroes() async {
        guard case .idle = loadState, heroes.isEmpty else { return }
        loadState = .loading
        do {
            let result = try await provider.marvelCardListUseCase.invoke()
            result.onSuccess { [weak self] value in
                self?.heroes = (value as? [MarvelCardModel]) ?? []
                self?.loadState = .idle
            }.onFailure { [weak self] error in
                self?.loadState = .error(error.message ?? "Failed to load heroes")
            }
        } catch {
            loadState = .error(error.localizedDescription)
        }
    }

    // MARK: - Observe favorites Flow (SKIE bridges Flow → SkieSwiftFlow → AsyncSequence)

    func startObservingFavorites() {
        favoritesTask?.cancel()
        favoritesTask = Task {
            for await favList in provider.favoriteFlow {
                if Task.isCancelled { break }
                favorites = favList as? [MarvelCardModel] ?? []
            }
        }
    }

    func stopObservingFavorites() {
        favoritesTask?.cancel()
        favoritesTask = nil
    }

    // MARK: - Favorite toggle

    func toggleFavorite(_ hero: MarvelCardModel) {
        Task {
            if isFavorite(hero) {
                try? await provider.deleteFavoriteUseCase.invoke(marvelCardModel: hero)
            } else {
                try? await provider.addFavoriteUseCase.invoke(marvelCardModel: hero)
            }
        }
    }

    deinit {
        favoritesTask?.cancel()
    }
}
