import SwiftUI
import shared

struct HeroCardView: View {
    let hero: MarvelCardModel
    let isFavorite: Bool

    var body: some View {
        ZStack(alignment: .topTrailing) {
            VStack(spacing: 0) {
                heroImage
                heroName
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

    private var heroImage: some View {
        AsyncImage(url: imageURL(from: hero.imagesrc)) { phase in
            switch phase {
            case .success(let image):
                image.resizable().scaledToFill()
            case .failure:
                Color.gray.opacity(0.3)
            default:
                ShimmerCard()
            }
        }
        .frame(width: 180, height: 190)
        .clipped()
    }

    private var heroName: some View {
        Text(hero.name)
            .font(.system(size: 13, weight: .semibold))
            .lineLimit(2)
            .multilineTextAlignment(.center)
            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)
            .padding(.horizontal, 8)
    }

    private func imageURL(from src: String?) -> URL? {
        guard let src, !src.isEmpty else { return nil }
        let urlString = src.hasPrefix("http") ? src : "https://marvelcdb.com\(src)"
        return URL(string: urlString)
    }
}

// MARK: - Shimmer loading card

struct HeroCardShimmer: View {
    var body: some View {
        VStack(spacing: 0) {
            ShimmerCard()
                .frame(width: 180, height: 190)
            ShimmerCard()
                .frame(width: 140, height: 18)
                .padding(.vertical, 16)
        }
        .frame(width: 180, height: 250)
        .clipShape(RoundedRectangle(cornerRadius: 16))
    }
}

struct ShimmerCard: View {
    @State private var isAnimating = false

    var body: some View {
        LinearGradient(
            colors: [
                Color.gray.opacity(0.3),
                Color.gray.opacity(0.1),
                Color.gray.opacity(0.3)
            ],
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
