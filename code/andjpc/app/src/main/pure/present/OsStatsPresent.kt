//  Copyright © 2022 - 2025 Christopher Augustus
//
//  This Source Code Form is subject to the terms of the Mozilla Public
//  License, v. 2.0. If a copy of the MPL was not distributed with this
//  file, You can obtain one at https://mozilla.org/MPL/2.0/.

package org.andapis.pure.present

import org.andapis.pure.domain.*

data class OsStatsPresent(
    val name: String = "",
    val version: String = ""
)

fun osStatsPresent(model: OsStats): OsStatsPresent = model.run {
    OsStatsPresent(
        family.toString() + prefixNonEmpty(" ", nickname),
        version + prefixNonZero(" : API ", apiLevel)
    )
}

fun osStatsPresentString(model: OsStats)= osStatsPresent(model).run {
    name + " " + version
}

fun prefixNonEmpty(prefix: String, string: String) =
    if (string.isNotEmpty()) prefix + string else ""

fun prefixNonZero(prefix: String, int: Int) =
    if (int != 0) prefix + int else ""
