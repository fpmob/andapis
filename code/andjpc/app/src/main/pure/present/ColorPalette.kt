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
   BackApiExec  ( ColorRgba( 0.7f, 0.7f, 0.6f ) ),
   BackExecRes  ( ColorRgba( 0.7f, 0.6f, 0.6f ) ),
   BordDebug    ( ColorRgba( 1.0f, 1.0f, 0.0f ) ),
   BordApiGroup ( ColorRgba( 0.5f, 0.6f, 0.6f ) ),
   BordApiSpec  ( ColorRgba( 0.5f, 0.6f, 0.5f ) ),
   BordApiExec  ( ColorRgba( 0.6f, 0.4f, 0.0f ) ),
   BordExecRes  ( ColorRgba( 0.6f, 0.0f, 0.0f ) ),
   ForeApiGroup ( ColorRgba( 0.0f, 0.0f, 0.5f ) ),
   ForeApiSpec  ( ColorRgba( 0.0f, 0.3f, 0.0f ) ),
   ForeApiExec  ( ColorRgba( 0.6f, 0.4f, 0.2f ) ),
   ForeExecRes  ( ColorRgba( 0.6f, 0.3f, 0.3f ) ),
}
