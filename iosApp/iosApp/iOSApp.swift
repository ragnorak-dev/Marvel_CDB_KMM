import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        HelperDiKt.doInitKoin()
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
