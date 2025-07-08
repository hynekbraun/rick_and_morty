import Shared
import SwiftUI

@main
struct iOSApp: App {
	init() {
		KoinKt.doInitKoin(
			platformModules: [
				ApplicationModule.shared.createKoinModule(
					strings: { StringsResImpl() },
					drawables: { DrawableResImpl() }
				),
			],
			appDeclaration: { _ in }
		)
	}

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
