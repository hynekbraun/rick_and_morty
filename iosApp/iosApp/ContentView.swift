import SwiftUI
import Shared

struct ContentView: View {
    
	var body: some View {
		TabView {
			Tab(L10n.characters, image: "tap_bar_characters") {
				NavigationStack {
					CharactersList()
				}
			}

			Tab(L10n.favorites, image: "tap_bar_favorites") {
				NavigationStack {
					FavoriteCharacters()
				}
			}
		}
	}
}
