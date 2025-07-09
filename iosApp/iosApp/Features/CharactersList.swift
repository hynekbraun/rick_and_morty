import Shared
import SwiftUI

struct CharactersList: View {
	@StateObject
	private var viewModel: SharedViewModelStoreOwner<CharactersListViewModel> = .init()

	@State private var showingSearch = false

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
			VStack {
				ActionBarComponent(
					model: ActionBarComponentModel(
						title: L10n.favorites,
						leadingIcon: nil,
						trailingIcon: .search,
					),
					onTrailingIconClick: {
						showingSearch = true
					}
				)

				ScrollView {
					LazyVStack(spacing: 8) {
						Spacer()
							.frame(height: 2)

						ForEach(data.characters, id: \.id) { character in
							NavigationLink(
								destination: { CharacterDetail(characterId: character.id) },
								label: {
									CardCharacterComponent(model: character)
										.frame(maxWidth: .infinity)
								}
							)
						}

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
			}
			.sheet(isPresented: $showingSearch) {
				Search()
			}

		case .loading:
			Loading()

		case let .error(error):
			ErrorMessage(error: error.description())
		}
	}
}
