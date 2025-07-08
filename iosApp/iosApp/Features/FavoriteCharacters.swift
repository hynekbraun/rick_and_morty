import Shared
import SwiftUI

struct FavoriteCharacters: View {
	@StateObject
	private var viewModel: SharedViewModelStoreOwner<FavoritesListViewModel> = .init()

	var body: some View {
		VStack {
			Observing(viewModel.instance.state) { state in
				content(state)
			}
			.frame(maxHeight: .infinity)
		}
	}

	@ViewBuilder
	private func content(_ state: FavoritesListViewState) -> some View {
		switch onEnum(of: state) {
		case let .data(data):
			VStack {
				ActionBarComponent(
					model: ActionBarComponentModel(title: L10n.favorites, leadingIcon: nil, trailingIcon: nil)
				)
				ScrollView {
					ForEach(data.characters, id: \.id) { character in
						NavigationLink(
							destination: { CharacterDetail(characterId: character.id) },
							label: {
								CardCharacterComponent(model: character)
									.frame(maxWidth: .infinity)
							}
						)
					}
				}
				.padding(.horizontal, 8)
			}

		case .loading:
			Loading()

		case let .error(error):
			ErrorMessage(error: error.description())
		}
	}
}
