@file:Suppress("INTERFACE_WITH_SUPERCLASS")

package dbTest

import kotlin.js.Promise

@JsModule("sql.js")
@JsNonModule
external fun initSqlJs(): Promise<Any>



