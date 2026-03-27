import SwiftUI
import shared

struct HeroDetailsView: View {
    let hero: MarvelCardModel
    @EnvironmentObject var viewModel: MarvelViewModel

    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 0) {
                heroImage
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

                    statsRow

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
                favoriteButton
            }
        }
    }

    // MARK: - Hero image

    private var heroImage: some View {
        AsyncImage(url: imageURL(from: hero.imagesrc)) { phase in
            switch phase {
            case .success(let image):
                image.resizable().scaledToFit()
            case .failure:
                Color.gray.opacity(0.2).frame(height: 300)
            default:
                ShimmerCard().frame(height: 300)
            }
        }
        .frame(maxWidth: .infinity)
        .clipped()
    }

    // MARK: - Stats

    private var statsRow: some View {
        HStack(spacing: 24) {
            statItem(label: "ATK", value: Int(hero.attack))
            statItem(label: "DEF", value: Int(hero.defense))
            statItem(label: "HP", value: Int(hero.health))

            Spacer()
        }
    }

    private func statItem(label: String, value: Int) -> some View {
        VStack(spacing: 2) {
            Text("\(value)")
                .font(.system(size: 20, weight: .bold))
            Text(label)
                .font(.caption)
                .foregroundStyle(.secondary)
        }
    }

    // MARK: - Favorite button

    private var favoriteButton: some View {
        Button {
            viewModel.toggleFavorite(hero)
        } label: {
            Image(systemName: viewModel.isFavorite(hero) ? "star.fill" : "star")
                .font(.system(size: 22))
                .foregroundStyle(viewModel.isFavorite(hero) ? .yellow : .gray)
        }
    }

    private func imageURL(from src: String?) -> URL? {
        guard let src, !src.isEmpty else { return nil }
        let urlString = src.hasPrefix("http") ? src : "https://marvelcdb.com\(src)"
        return URL(string: urlString)
    }
}
