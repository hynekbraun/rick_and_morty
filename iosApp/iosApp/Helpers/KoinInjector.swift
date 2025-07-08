import Foundation
import Shared

public func inject<T>(
	qualifier: Koin_coreQualifier? = nil,
	parameters: (() -> Koin_coreParametersHolder)? = nil
) -> T {
	KoinGetKt.koinGet(
		clazz: SwiftType(type: T.self, swiftClazz: SwiftKClass<T>()).getClazz(),
		qualifier: qualifier,
		parameters: parameters
	) as! T
}

final class SwiftKClass<T>: NSObject, KotlinKClass {
	var simpleName: String? { String(describing: T.self) }
	var qualifiedName: String? { String(reflecting: T.self) }

	func isInstance(value: Any?) -> Bool { value is T }
}
