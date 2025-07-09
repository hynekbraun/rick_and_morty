import Shared
import SwiftUI

struct CardCharacterSearchComponent: View {
	let model: CardCharacterSearchComponentModel
	
	var body: some View {
			HStack(alignment: .top, spacing: 16) {
				AsyncImage(url: URL(string: model.photoUrl)) { image in
					image
						.resizable()
						.aspectRatio(contentMode: .fill)
				} placeholder: {
					Image(.tapBarCharacters)
						.foregroundColor(.orange)
						.font(.system(size: 20))
				}
				.frame(width: 44, height: 44)
				.clipShape(RoundedRectangle(cornerRadius: 8))
				
				Text(model.name)
					.font(.system(size: 18, weight: .semibold))
					.foregroundColor(.foregroundPrimary)
					.lineLimit(1)
					.truncationMode(.tail)
				
				Spacer()
			}
			.padding(8)
	}
}
