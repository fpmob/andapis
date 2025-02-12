//  Copyright © 2025 Christopher Augustus
//
//  This Source Code Form is subject to the terms of the Mozilla Public
//  License, v. 2.0. If a copy of the MPL was not distributed with this
//  file, You can obtain one at https://mozilla.org/MPL/2.0/.

package org.andapis.pure.domain

val apiSpecs = listOf(
    ApiSpec(kind=ApiKind.Package, name="android.hardware.camera2", list=listOf(
        ApiSpec(kind=ApiKind.Class, name="CameraRequest", list=listOf(
            ApiSpec(36, ApiChange.Altered, ApiKind.Type, "COLOR_CORRECTION_MODE"),
        )),
    )),
    /* TODO: ### THESE APIS ARE MARKED @hide
    ApiSpec(kind=ApiKind.Package, name="android.net.wifi", list=listOf(
        ApiSpec(kind=ApiKind.Class, name="WifiManager", list=listOf(
            ApiSpec(36, ApiChange.Added, ApiKind.Method, "getAvailableAdvancedProtectionFeatures",
                    "getAvailableAdvancedProtectionFeatures() -> List<AdvancedProtectionFeature>"),
            ApiSpec(36, ApiChange.Added, ApiKind.Method, "isUsdPublisherSupported"),
            ApiSpec(36, ApiChange.Added, ApiKind.Method, "isUsdSubscriberSupported"),
        )),
        ApiSpec(kind=ApiKind.Class, name="SoftApConfiguration", list=listOf(
            ApiSpec(36, ApiChange.Added, ApiKind.Method, "isClientIsolationEnabled"),
            ApiSpec(36, ApiChange.Added, ApiKind.Method, "setClientIsolationEnabled"),
        )),
    )),
    ApiSpec(kind=ApiKind.Package, name="android.net.wifi.usd", list=listOf(
        ApiSpec(36, ApiChange.Added, ApiKind.Class, "PublishSession"),
        ApiSpec(36, ApiChange.Added, ApiKind.Class, "SessionCallback"),
        ApiSpec(36, ApiChange.Added, ApiKind.Class, "SubscribeSession"),
        ApiSpec(36, ApiChange.Added, ApiKind.Class, "SubscribeSessionCallback"),
        ApiSpec(36, ApiChange.Added, ApiKind.Class, "UsdManager"),
    )),
    ApiSpec(kind=ApiKind.Package, name="android.net.wifi.util", list=listOf(
        ApiSpec(kind=ApiKind.Class, name="Environment", list=listOf(
            ApiSpec(36, ApiChange.Added, ApiKind.Method, "isSdkAtLeastB"),
        )),
    )),
    */
    ApiSpec(kind=ApiKind.Package, name="android.net.wifi.p2p", list=listOf(
        ApiSpec(kind=ApiKind.Class, name="WifiP2pDirInfo", list=listOf(
            ApiSpec(36, ApiChange.Added, ApiKind.Method, "WifiP2pDirInfo",
                    "WifiP2pDirInfo() -> WifiP2pDirInfo"),
            ApiSpec(36, ApiChange.Added, ApiKind.Method, "describeContents"),
            ApiSpec(36, ApiChange.Added, ApiKind.Method, "getDirTag"),
            ApiSpec(36, ApiChange.Added, ApiKind.Method, "getMacAddress"),
            ApiSpec(36, ApiChange.Added, ApiKind.Method, "getNonce"),
            ApiSpec(36, ApiChange.Added, ApiKind.Method, "toString"),
            ApiSpec(36, ApiChange.Added, ApiKind.Method, "writeToParcel"),
        )),
    )),
)
