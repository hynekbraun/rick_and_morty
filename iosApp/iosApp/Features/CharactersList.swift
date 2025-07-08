import Shared
import SwiftUI

struct CharactersList: View {

	@StateObject
	private var viewModel: SharedViewModelStoreOwner<CharactersListViewModel> = .init()

	var body: some View {
		VStack {
			Observing(viewModel.instance.state) { state in
				content(state)
			}
			.frame(maxHeight: .infinity)
		}
	}

	@ViewBuilder
	private func content(_ state: CharactersListViewState) -> some View {
		switch onEnum(of: state) {
		case let .data(data):
			ScrollView {
						LazyVStack(spacing: 8) {
							// Top spacer
							Spacer()
								.frame(height: 2)
							
							// Character items
							ForEach(data.characters, id: \.id) { character in
								NavigationLink(
									destination: { CharacterDetail(characterId: character.id) },
									label: {
										CardCharacterComponent(model: character)
										.frame(maxWidth: .infinity)
									}
								)
							}
							
							// Next page loading component
							if data.nextPage != nil {
								NextPageComponent()
									.frame(maxWidth: .infinity)
									.onAppear {
										viewModel.instance.getNextPage()
									}
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
