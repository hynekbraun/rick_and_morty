import SwiftUI
import Shared

struct DetailHeaderComponent: View {
	
	let model: DetailHeaderComponentModel
	
	var body: some View {
		VStack(spacing: 0) {
			HStack(alignment: .top, spacing: 16) {
				AsyncImage(url: URL(string: model.imageUrl)) { image in
					image
						.resizable()
						.aspectRatio(contentMode: .fill)
				} placeholder: {
					Image(.tapBarCharacters)
						.resizable()
						.aspectRatio(contentMode: .fill)
				}
				.frame(width: 140, height: 140)
				.clipShape(RoundedRectangle(cornerRadius: 8))
				
				VStack(alignment: .leading, spacing: 14) {
					Text(model.title)
						.font(.caption)
						.foregroundColor(.foregroundSecondary)
						.lineLimit(1)
						.truncationMode(.tail)
					
					Text(model.name)
						.font(.title2)
						.foregroundColor(.foregroundPrimary)
				}
				
				Spacer()
			}
			.padding(16)
			
			Rectangle()
				.fill(.foregroundSecondary)
				.frame(height: 1)
				.frame(maxWidth: .infinity)
		}
	}
}
