import Shared
import SwiftUI

struct CardCharacterComponent: View {
	let model: CardCharacterComponentModel
	
	var body: some View {
		HStack(alignment: .top, spacing: 16) {
			AsyncImage(url: URL(string: model.photoUrl)) { image in
				image
					.resizable()
					.aspectRatio(contentMode: .fill)
			} placeholder: {
				Image(systemName: "tap_bar_character")
					.foregroundColor(.foregroundPrimary)
					.font(.system(size: 20))
			}
			.frame(width: 44, height: 44)
			.clipShape(RoundedRectangle(cornerRadius: 8))
				
			VStack(alignment: .leading, spacing: 2) {
				HStack(alignment: .center, spacing: 4) {
					Text(model.name)
						.font(.system(size: 18, weight: .semibold))
						.foregroundColor(.foregroundPrimary)
						.lineLimit(1)
						.truncationMode(.tail)
						
					if model.showStar {
						Image(systemName: "star.fill")
							.foregroundColor(.accentPrimary)
							.font(.system(size: 16))
					}
						
					Spacer()
				}
					
				Text(model.status)
					.font(.system(size: 14))
					.foregroundColor(.foregroundSecondary)
			}
		}
		.padding(12)
		.buttonStyle(PlainButtonStyle())
		.background(Color(.systemBackground))
		.clipShape(RoundedRectangle(cornerRadius: 16))
		.shadow(color: Color.black.opacity(0.05), radius: 1, x: 0, y: 1)
	}
}
