import SwiftUI
import shared

struct FavouritesView: View {
    @EnvironmentObject var viewModel: MarvelViewModel

    private let columns = [
        GridItem(.flexible()),
        GridItem(.flexible())
    ]

    var body: some View {
        NavigationStack {
            Group {
                if viewModel.favorites.isEmpty {
                    emptyView
                } else {
                    favouritesGrid
                }
            }
            .navigationTitle("Favourites")
        }
    }

    // MARK: - Favourites grid

    private var favouritesGrid: some View {
        ScrollView {
            LazyVGrid(columns: columns, spacing: 16) {
                ForEach(viewModel.favorites, id: \.code) { hero in
                    NavigationLink {
                        HeroDetailsView(hero: hero)
                            .environmentObject(viewModel)
                    } label: {
                        HeroCardView(hero: hero, isFavorite: true)
                    }
                    .buttonStyle(.plain)
                }
            }
            .padding(16)
        }
    }

    // MARK: - Empty state

    private var emptyView: some View {
        VStack(spacing: 12) {
            Image(systemName: "star.slash")
                .font(.system(size: 48))
                .foregroundStyle(.secondary)
            Text("No favourites yet")
                .foregroundStyle(.secondary)
            Text("Tap the ★ on any hero to add it here")
                .font(.caption)
                .foregroundStyle(.tertiary)
                .multilineTextAlignment(.center)
        }
        .padding()
    }
}
