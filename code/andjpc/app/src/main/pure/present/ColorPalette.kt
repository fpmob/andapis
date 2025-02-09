//  Copyright © 2022 - 2025 Christopher Augustus
//
//  This Source Code Form is subject to the terms of the Mozilla Public
//  License, v. 2.0. If a copy of the MPL was not distributed with this
//  file, You can obtain one at https://mozilla.org/MPL/2.0/.

package org.andapis.pure.present

data class ColorRgba(
    val r: Float, val g: Float, val b: Float, val a: Float = 1f)

enum class ColorPalette(val value: ColorRgba) {
   BackApiGroup ( ColorRgba( 0.6f, 0.7f, 0.7f ) ),
   BackApiSpec  ( ColorRgba( 0.6f, 0.7f, 0.6f ) ),
   BackExecNew  ( ColorRgba( 0.7f, 0.7f, 0.6f ) ),
   BackExecOld  ( ColorRgba( 0.7f, 0.6f, 0.6f ) ),
   BordDebug    ( ColorRgba( 1.0f, 1.0f, 0.0f ) ),
   BordApiGroup ( ColorRgba( 0.0f, 0.2f, 0.7f ) ),
   BordApiSpec  ( ColorRgba( 0.0f, 0.5f, 0.0f ) ),
   BordExecNew  ( ColorRgba( 0.6f, 0.4f, 0.0f ) ),
   BordExecOld  ( ColorRgba( 0.6f, 0.0f, 0.0f ) ),
   ForeApiGroup ( ColorRgba( 0.4f, 0.4f, 0.8f ) ),
   ForeApiSpec  ( ColorRgba( 0.6f, 0.7f, 0.6f ) ),
   ForeExecNew  ( ColorRgba( 0.6f, 0.4f, 0.2f ) ),
   ForeExecOld  ( ColorRgba( 0.6f, 0.3f, 0.3f ) ),
}
