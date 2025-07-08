import Shared
import SwiftUI

final class SharedViewModelStoreOwner<VM: Shared.ViewModel>: ObservableObject, ViewModelStoreOwner {
	let viewModelStore: ViewModelStore = .init()
	private let key: String = .init(describing: type(of: VM.self))
	
	init(factory: () -> VM) {
		let viewModel = factory()
		viewModelStore.put(key: key, viewModel: viewModel)
	}
	
	// Keep the old init for ViewModels without parameters
	init(_ viewModel: VM = inject()) {
		viewModelStore.put(key: key, viewModel: viewModel)
	}
	
	var instance: VM {
		viewModelStore.get(key: key) as! VM
	}
	
	deinit {
		viewModelStore.clear()
	}
}
