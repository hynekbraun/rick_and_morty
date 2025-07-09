import Shared
import SwiftUI

struct Search: View {
	@Environment(\.dismiss) private var dismiss
	@StateObject
	private var viewModel: SharedViewModelStoreOwner<SearchViewModel> = .init()
	
	var body: some View {
		VStack(alignment: .leading, spacing: 0) {
			Observing(viewModel.instance.queryState) { query in
				ActionBarSearchComponent(
					text: query,
					prompt: L10n.searchPrompt,
					onTextChanged: { text in viewModel.instance.changeQuery(query: text) },
					onDeleteClicked: { viewModel.instance.clearQuery() },
					onLeadingIconClick: { dismiss() }
				)
			}
			
			Observing(viewModel.instance.state) { state in
				content(state)
			}
			.frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topLeading)
		}
	}
	
	@ViewBuilder
	private func content(_ state: SearchViewState) -> some View {
		switch onEnum(of: state) {
		case let .data(data):
			ScrollView {
				LazyVStack(spacing: 8) {
					ForEach(data.characters, id: \.id) { character in
						NavigationLink(
							destination: { CharacterDetail(characterId: character.id) },
							label: {
								CardCharacterSearchComponent(model: character)
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
			.frame(maxWidth: .infinity, maxHeight: .infinity)
			
		case .initial:

			VStack {
				Spacer()
				Text("Start typing to search...")
					.foregroundColor(.secondary)
					.font(.subheadline)
				Spacer()
			}
			.frame(maxWidth: .infinity, maxHeight: .infinity)
			
		case let .error(error):
			VStack {
				Spacer()
				ErrorMessage(error: error.description())
				Spacer()
			}
			.frame(maxWidth: .infinity, maxHeight: .infinity)
		}
	}
}
