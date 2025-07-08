import SwiftUI
import Shared

struct ContentView: View {
    
	var body: some View {
		TabView {
			Tab("Characters", image: "tap_bar_characters") {
				NavigationStack {
					CharactersList()
				}
			}

			Tab("Favorites", image: "tap_bar_favorites") {
				NavigationStack {
					FavoriteCharacters()
				}
			}
		}
	}
}
