import SwiftUI
import shared

// MARK: - Shimmer

struct ShimmerCard: View {
    @State private var isAnimating = false

    var body: some View {
        LinearGradient(
            colors: [Color.gray.opacity(0.3), Color.gray.opacity(0.1), Color.gray.opacity(0.3)],
            startPoint: isAnimating ? .topLeading : .bottomTrailing,
            endPoint: isAnimating ? .bottomTrailing : .topLeading
        )
        .onAppear {
            withAnimation(.linear(duration: 1.2).repeatForever(autoreverses: false)) {
                isAnimating = true
            }
        }
    }
}

struct HeroCardShimmer: View {
    var body: some View {
        VStack(spacing: 0) {
            ShimmerCard().frame(width: 180, height: 190)
            ShimmerCard().frame(width: 140, height: 18).padding(.vertical, 16)
        }
        .frame(width: 180, height: 250)
        .clipShape(RoundedRectangle(cornerRadius: 16))
    }
}

// MARK: - Hero card

struct HeroCardView: View {
    let hero: MarvelCardModel
    let isFavorite: Bool

    var body: some View {
        ZStack(alignment: .topTrailing) {
            VStack(spacing: 0) {
                AsyncImage(url: heroImageURL()) { phase in
                    switch phase {
                    case .success(let img): img.resizable().scaledToFill()
                    case .failure: Color.gray.opacity(0.3)
                    default: ShimmerCard()
                    }
                }
                .frame(width: 180, height: 190)
                .clipped()

                Text(hero.name)
                    .font(.system(size: 13, weight: .semibold))
                    .lineLimit(2)
                    .multilineTextAlignment(.center)
                    .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)
                    .padding(.horizontal, 8)
            }
            .frame(width: 180, height: 250)
            .background(Color(.systemBackground))
            .clipShape(RoundedRectangle(cornerRadius: 16))
            .shadow(color: .black.opacity(0.1), radius: 4, x: 0, y: 2)

            if isFavorite {
                Image(systemName: "star.fill")
                    .foregroundStyle(.yellow)
                    .font(.system(size: 18))
                    .padding(8)
            }
        }
    }

    private func heroImageURL() -> URL? {
        guard let src = hero.imagesrc, !src.isEmpty else { return nil }
        let urlStr = src.hasPrefix("http") ? src : "https://marvelcdb.com\(src)"
        return URL(string: urlStr)
    }
}

// MARK: - Hero details

struct HeroDetailsView: View {
    let hero: MarvelCardModel
    @EnvironmentObject var viewModel: MarvelViewModel

    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 0) {
                AsyncImage(url: heroImageURL()) { phase in
                    switch phase {
                    case .success(let img): img.resizable().scaledToFit()
                    case .failure: Color.gray.opacity(0.2).frame(height: 300)
                    default: ShimmerCard().frame(height: 300)
                    }
                }
                .frame(maxWidth: .infinity)
                .clipped()
                .padding(.bottom, 16)

                VStack(alignment: .leading, spacing: 12) {
                    Text(hero.name)
                        .font(.system(size: 22, weight: .semibold))

                    if !hero.subname.isEmpty {
                        Text(hero.subname)
                            .font(.subheadline)
                            .foregroundStyle(.secondary)
                    }

                    Divider()

                    HStack(spacing: 24) {
                        statItem(label: "ATK", value: Int(hero.attack))
                        statItem(label: "DEF", value: Int(hero.defense))
                        statItem(label: "HP",  value: Int(hero.health))
                        Spacer()
                    }

                    if !hero.text.isEmpty {
                        Text(hero.text)
                            .font(.body)
                            .foregroundStyle(.secondary)
                            .padding(.top, 4)
                    }
                }
                .padding(.horizontal, 16)
            }
        }
        .navigationBarTitleDisplayMode(.inline)
        .toolbar {
            ToolbarItem(placement: .topBarTrailing) {
                Button {
                    viewModel.toggleFavorite(hero)
                } label: {
                    Image(systemName: viewModel.isFavorite(hero) ? "star.fill" : "star")
                        .foregroundStyle(viewModel.isFavorite(hero) ? .yellow : .gray)
                        .font(.system(size: 22))
                }
            }
        }
    }

    private func statItem(label: String, value: Int) -> some View {
        VStack(spacing: 2) {
            Text("\(value)").font(.system(size: 20, weight: .bold))
            Text(label).font(.caption).foregroundStyle(.secondary)
        }
    }

    private func heroImageURL() -> URL? {
        guard let src = hero.imagesrc, !src.isEmpty else { return nil }
        let urlStr = src.hasPrefix("http") ? src : "https://marvelcdb.com\(src)"
        return URL(string: urlStr)
    }
}

// MARK: - Favourites

struct FavouritesView: View {
    @EnvironmentObject var viewModel: MarvelViewModel

    private let columns = [GridItem(.flexible()), GridItem(.flexible())]

    var body: some View {
        NavigationStack {
            Group {
                if viewModel.favorites.isEmpty {
                    VStack(spacing: 12) {
                        Image(systemName: "star.slash")
                            .font(.system(size: 48))
                            .foregroundStyle(.secondary)
                        Text("No favourites yet").foregroundStyle(.secondary)
                        Text("Tap ★ on any hero to add it here")
                            .font(.caption).foregroundStyle(.tertiary)
                            .multilineTextAlignment(.center)
                    }
                    .padding()
                } else {
                    ScrollView {
                        LazyVGrid(columns: columns, spacing: 16) {
                            ForEach(viewModel.favorites, id: \.code) { hero in
                                NavigationLink {
                                    HeroDetailsView(hero: hero).environmentObject(viewModel)
                                } label: {
                                    HeroCardView(hero: hero, isFavorite: true)
                                }
                                .buttonStyle(.plain)
                            }
                        }
                        .padding(16)
                    }
                }
            }
            .navigationTitle("Favourites")
        }
    }
}

// MARK: - Heroes list

struct MarvelCardListView: View {
    @EnvironmentObject var viewModel: MarvelViewModel

    private let columns = [GridItem(.flexible()), GridItem(.flexible())]

    var body: some View {
        NavigationStack {
            Group {
                switch viewModel.loadState {
                case .loading:
                    ScrollView {
                        LazyVGrid(columns: columns, spacing: 16) {
                            ForEach(0..<6, id: \.self) { _ in HeroCardShimmer() }
                        }
                        .padding(16)
                    }

                case .error(let msg):
                    VStack(spacing: 12) {
                        Image(systemName: "exclamationmark.triangle")
                            .font(.system(size: 48)).foregroundStyle(.red)
                        Text(msg).multilineTextAlignment(.center).foregroundStyle(.secondary)
                        Button("Retry") { Task { await viewModel.loadHeroes() } }
                            .buttonStyle(.borderedProminent)
                    }
                    .padding()

                case .idle:
                    if viewModel.heroes.isEmpty {
                        VStack {
                            Image(systemName: "person.slash")
                                .font(.system(size: 48)).foregroundStyle(.secondary)
                            Text("No heroes found").foregroundStyle(.secondary)
                        }
                    } else {
                        ScrollView {
                            LazyVGrid(columns: columns, spacing: 16) {
                                ForEach(viewModel.heroes, id: \.code) { hero in
                                    NavigationLink {
                                        HeroDetailsView(hero: hero).environmentObject(viewModel)
                                    } label: {
                                        HeroCardView(hero: hero, isFavorite: viewModel.isFavorite(hero))
                                    }
                                    .buttonStyle(.plain)
                                }
                            }
                            .padding(16)
                        }
                    }
                }
            }
            .navigationTitle("Marvel Heroes")
        }
        .task {
            await viewModel.loadHeroes()
            viewModel.startObservingFavorites()
        }
    }
}
