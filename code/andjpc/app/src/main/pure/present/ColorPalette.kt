//  Copyright © 2022 - 2025 Christopher Augustus
//
//  This Source Code Form is subject to the terms of the Mozilla Public
//  License, v. 2.0. If a copy of the MPL was not distributed with this
//  file, You can obtain one at https://mozilla.org/MPL/2.0/.

package org.andapis.pure.present

data class ColorRgba(
    val r: Float, val g: Float, val b: Float, val a: Float = 1f)

enum class ColorPalette(val value: ColorRgba) {
   BackApiExec  ( ColorRgba( 0.2f, 0.2f, 0.1f ) ),
   BackApiTree  ( ColorRgba( 0.1f, 0.2f, 0.1f ) ),
   BackExecErr  ( ColorRgba( 0.2f, 0.1f, 0.1f ) ),
   BackExecRes  ( ColorRgba( 0.1f, 0.1f, 0.2f ) ),
   BordDebug    ( ColorRgba( 1.0f, 1.0f, 0.0f ) ),
   BordApiTree  ( ColorRgba( 0.0f, 0.0f, 0.0f ) ),
   BordApiExec  ( ColorRgba( 0.0f, 0.0f, 0.0f ) ),
   BordExecRes  ( ColorRgba( 0.0f, 0.0f, 0.0f ) ),
   BordExecErr  ( ColorRgba( 0.0f, 0.0f, 0.0f ) ),
   ForeApiExec  ( ColorRgba( 0.8f, 0.8f, 0.6f ) ),
   ForeApiTree  ( ColorRgba( 0.6f, 0.8f, 0.6f ) ),
   ForeExecRes  ( ColorRgba( 0.6f, 0.7f, 0.8f ) ),
   ForeExecErr  ( ColorRgba( 0.8f, 0.6f, 0.6f ) ),
}
