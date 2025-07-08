import Shared
import SwiftUI

struct CharacterDetail: View {
	@Environment(\.dismiss) private var dismiss

	private let characterId: String

	@StateObject private var viewModel: SharedViewModelStoreOwner<CharacterDetailViewModel>

	init(characterId: String) {
		self.characterId = characterId
		self._viewModel = StateObject(wrappedValue:
					SharedViewModelStoreOwner {
						CharacterDetailViewModel(
							charactersRepository: inject(),
							favoritesRepository: inject(),
							characterId: characterId,
							componentFactory: inject(),
							drawableResources: inject()
						)
					}
				)
		}

	var body: some View {
		VStack {
		
			Observing(viewModel.instance.actionBarState) { actionBarState in
				ActionBarComponent(
					model: actionBarState,
					onLeadingIconClick: { dismiss() },
					onTrailingIconClick: { viewModel.instance.toggleFavorite() }
				)
			}
			
			Observing(viewModel.instance.state) { state in
				switch onEnum(of: state) {
				case let .data(state):
					content(model: state)

				case .error:
					ErrorMessage(error: "Something went wrong")

				case .loading:
					Loading()
				}
			}
		}
	}

	@ViewBuilder
	private func content(model: CharacterDetailViewState.Data) -> some View {
		ScrollView {
			LazyVStack(spacing: 16) {
				DetailHeaderComponent(model: model.header)

				ForEach(model.info, id: \.title) { infoItem in
					InformationComponent(model: infoItem)
						.padding(.horizontal, 16)
				}
			}
			.padding(.top, 16)
			.padding(.bottom, 24)
		}
		.navigationBarHidden(true)
		.padding(.horizontal, 8)
		.tint(.backgroundSecondary)
		.cornerRadius(16)
	}
}
