import SwiftUI

struct NextPageComponent: View {
	var body: some View {
		ZStack {
			ProgressView()
				.progressViewStyle(
					CircularProgressViewStyle(tint: .accentPrimary)
				)
				.frame(width: 32, height: 32)
		}
		.frame(maxWidth: .infinity, maxHeight: .infinity)
	}
}
