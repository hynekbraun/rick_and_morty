import SwiftUI

struct ActionBarSearchComponent: View {
	@State
	var text: String
	let prompt: String
	let onTextChanged: (String) -> Void
	
	let onDeleteClicked: () -> Void
	let onLeadingIconClick: () -> Void
	
	@FocusState private var isTextFieldFocused: Bool
	
	var body: some View {
		VStack(spacing: 0) {
			HStack(alignment: .center, spacing: 8) {
				Button(action: {
					hapticFeedback()
					onLeadingIconClick()
				}) {
					Image(uiImage: .chevronBack)
						.resizable()
						.frame(width: 24, height: 24)
				}
				.buttonStyle(PlainButtonStyle())
				
				TextField(prompt, text: $text)
					.textFieldStyle(PlainTextFieldStyle())
					.font(.system(size: 16, weight: .medium))
					.focused($isTextFieldFocused)
					.frame(maxWidth: .infinity)
					.onChange(of: text) { newValue in
						onTextChanged(newValue)
					}
				
				if !text.isEmpty {
					Button(action: {
						hapticFeedback()
						onDeleteClicked()
						text = ""
					}) {
						Image(uiImage: .close)
							.font(.system(size: 16, weight: .medium))
							.frame(width: 24, height: 24)
					}
					.buttonStyle(PlainButtonStyle())
				}
			}
			.padding(.horizontal, 16)
			.padding(.vertical, 12)
			
			Rectangle()
				.fill(
					LinearGradient(
						gradient: Gradient(colors: [
							Color.black.opacity(0.25),
							Color.clear
						]),
						startPoint: .top,
						endPoint: .bottom
					)
				)
				.frame(height: 6)
		}
		.onAppear {
			DispatchQueue.main.asyncAfter(deadline: .now() + 0.1) {
				isTextFieldFocused = true
			}
		}
	}
	
	private func hapticFeedback() {
		let impactFeedback = UIImpactFeedbackGenerator(style: .medium)
		impactFeedback.impactOccurred()
	}
}
