import SwiftUI
import Shared

struct InformationComponent: View {
	let model: InformationComponentModel
	
	var body: some View {
		VStack(alignment: .leading, spacing: 0) {
			Text(model.title)
				.font(.headline)
				.foregroundColor(.foregroundSecondary)
				.lineLimit(1)
				.truncationMode(.tail)
			
			Text(model.value)
				.font(.caption2)
				.foregroundColor(.foregroundPrimary)
		}
	}
}
