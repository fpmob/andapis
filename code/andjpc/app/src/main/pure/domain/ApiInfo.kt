//  Copyright © 2025 Christopher Augustus
//
//  This Source Code Form is subject to the terms of the Mozilla Public
//  License, v. 2.0. If a copy of the MPL was not distributed with this
//  file, You can obtain one at https://mozilla.org/MPL/2.0/.

package org.andapis.pure.domain

enum class ApiChange { None, Added, Altered, Deprecated, Removed }
enum class ApiKind   { None, Attribute, Class, Function, Method, Package, Type }

data class ApiSpec(
    val apiInt: Int             = 0,
    val change: ApiChange       = ApiChange.None,
    val kind:   ApiKind         = ApiKind.None,
    val name:   String          = "(unspecified)",
    val exec:   String          = "",
    val list:   List<ApiSpec>   = emptyList(),
)