// Copyright 2022 Christopher Augustus
//
// This Source Code Form is subject to the terms of the Mozilla Public
// License, v. 2.0. If a copy of the MPL was not distributed with this
// file, You can obtain one at https://mozilla.org/MPL/2.0/.
//

package org.andapis.side

import org.andapis.pure.domain.*

fun osStats(): OsStats =
    android.os.Build.VERSION.SDK_INT.let { apiLevel ->
        OsKnown.values().find {
            it.value.family == OsFamily.Android &&
            it.value.apiLevel == apiLevel
        }?.value ?: OsStats(
            family = OsFamily.Android,
            apiLevel = apiLevel
        )
    }
