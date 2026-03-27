import SwiftUI
import shared

struct ContentView: View {
    @StateObject private var viewModel = MarvelViewModel()

    var body: some View {
        TabView {
            MarvelCardListView()
                .tabItem {
                    Label("Heroes", systemImage: "shield.fill")
                }
            
            FavouritesView()
                .tabItem {
                    Label("Favourites", systemImage: "star.fill")
                }
        }
        .environmentObject(viewModel)
        .tint(Color(red: 0, green: 0.686, blue: 0.604)) // primary teal
    }
}
