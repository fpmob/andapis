//  Copyright © 2025 Christopher Augustus
//
//  This Source Code Form is subject to the terms of the Mozilla Public
//  License, v. 2.0. If a copy of the MPL was not distributed with this
//  file, You can obtain one at https://mozilla.org/MPL/2.0/.

package org.andapis.side

import android.net.MacAddress
import android.net.wifi.p2p.WifiP2pDirInfo
import java.security.SecureRandom

val apiCalls = mapOf(
/* TODO: ### THESE APIS ARE MARKED @hide
    "getAvailableAdvancedProtectionFeatures"
    to { "### RESULT" },
    "isUsdPublisherSupported"
    to { "### RESULT" },
    "isUsdSubscriberSupported"
    to { "### RESULT" },
    "isClientIsolationEnabled"
    to { "### RESULT" },
    "setClientIsolationEnabled"
    to { "### RESULT" },
    "isSdkAtLeastB"
    to { "### RESULT" },
*/
    "WifiP2pDirInfo.WifiP2pDirInfo"
    to {
        val macThis = MacAddress.fromString("11:22:33:44:55:66")
        val macThat = MacAddress.fromString("77:88:99:aa:bb:cc")
        val nonce   = SecureRandom().generateSeed(8)
        //val dirTag  = Truncate-64(HMAC-SHA-256(DevIk, "DIR" || macThat || nonce))
        val dirTag  = SecureRandom().generateSeed(8)
        WifiP2pDirInfo(macThis, nonce, dirTag).toString()
    },
    "WifiP2pDirInfo.describeContents"
    to { "### RESULT" },
    "WifiP2pDirInfo.getDirTag"
    to { "### RESULT" },
    "WifiP2pDirInfo.getMacAddress"
    to { "### RESULT" },
    "WifiP2pDirInfo.getNonce"
    to { "### RESULT" },
    "WifiP2pDirInfo.toString"
    to { "### RESULT" },
    "WifiP2pDirInfo.writeToParcel"
    to { "### RESULT" },
)