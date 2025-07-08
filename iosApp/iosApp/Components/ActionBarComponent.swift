import SwiftUI
import Shared

struct ActionBarComponent: View {
    let model: ActionBarComponentModel
    let onLeadingIconClick: (() -> Void)?
    let onTrailingIconClick: (() -> Void)?
    
    init(
        model: ActionBarComponentModel,
        onLeadingIconClick: (() -> Void)? = nil,
        onTrailingIconClick: (() -> Void)? = nil
    ) {
        self.model = model
        self.onLeadingIconClick = onLeadingIconClick
        self.onTrailingIconClick = onTrailingIconClick
    }
    
    var body: some View {
        VStack(spacing: 0) {
            HStack(alignment: .center, spacing: 8) {
                if let leadingIcon = model.leadingIcon {
					if let leadingIcon = model.leadingIcon {
						Button(action: {
							hapticFeedback()
							onLeadingIconClick?()
						}) {
							Image(uiImage: leadingIcon)
								.resizable()
								.frame(width: 24, height: 24)
						}
						.buttonStyle(PlainButtonStyle())
					}
                }
                
                if let title = model.title {
                    Text(title)
						.font(.headline)
                        .foregroundColor(.foregroundPrimary)
                        .lineLimit(1)
                        .truncationMode(.tail)
                        .frame(maxWidth: .infinity, alignment: .leading)
                } else {
                    Spacer()
                }
                
                if let trailingIcon = model.trailingIcon {
                    Button(action: {
                        hapticFeedback()
                        onTrailingIconClick?()
                    }) {
						Image(uiImage: trailingIcon)
                            .resizable()
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
                .frame(maxWidth: .infinity)
        }
    }
    
    private func hapticFeedback() {
        let impactFeedback = UIImpactFeedbackGenerator(style: .heavy)
        impactFeedback.impactOccurred()
    }
}
