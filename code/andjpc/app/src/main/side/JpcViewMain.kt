//  Copyright © 2024 - 2025 Christopher Augustus
//
//  This Source Code Form is subject to the terms of the Mozilla Public
//  License, v. 2.0. If a copy of the MPL was not distributed with this
//  file, You can obtain one at https://mozilla.org/MPL/2.0/.

package org.andapis.side

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*

import org.andapis.pure.present.*
import org.andapis.ui.theme.AndjpcTheme

fun colorFrom(c: ColorPalette)
  = c.value.let { Color(red = it.r, green = it.g, blue = it.b, alpha = it.a) }

val paddingCommon   = 16
val paddingOsStats  = 8
val widthBordPanel  = 4

@Composable
fun jpcViewMain()
  = AndjpcTheme {
        Scaffold(
            topBar = { jpcTopAppBar() }
        ) { padding ->
            jpcViewInner(padding)
        }
    }

@Preview(showBackground = true)
@Composable
fun jpcViewMainPreview() = jpcViewMain()

@OptIn(ExperimentalMaterial3Api::class) // (still experimental?!)
@Composable
fun jpcTopAppBar()
  = CenterAlignedTopAppBar(title = { Text(osStatsPresentString(osStats())) })

@Composable
fun jpcViewInner(padding: PaddingValues)
  = Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize().padding(padding)
    ) {
        var hasDraws  by remember { mutableStateOf(false) }
        var hasPerfs  by remember { mutableStateOf(false) }
        var hasRandom by remember { mutableStateOf(false) }
        var hasStress by remember { mutableStateOf(false) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(0.32f)
                    .padding(top = paddingCommon.dp,
                             end = paddingCommon.dp,
                          bottom = paddingCommon.dp)) {
                jpcButtonPanel("Draws",  ColorPalette.BackDraw  ) { hasDraws  = !hasDraws }
                jpcButtonPanel("Random", ColorPalette.BackRandom) { hasRandom = !hasRandom}
            }
            Column(modifier = Modifier.weight(0.32f)
                    .padding(top = paddingCommon.dp,
                             end = paddingCommon.dp,
                          bottom = paddingCommon.dp)) {
                jpcButtonPanel("Perfs",  ColorPalette.BackPerf  ) { hasPerfs  = !hasPerfs }
                jpcButtonPanel("Stress", ColorPalette.BackStress) { hasStress = !hasStress }
            }
        }
        if (hasDraws || hasPerfs)
            Row(modifier = Modifier.weight(1.0f)) {
                if (hasDraws)  jpcViewDraws()
                if (hasPerfs)  jpcViewPerfs()
            }
        if (hasRandom || hasStress)
            Row(modifier = Modifier.weight(1.0f)) {
                if (hasRandom) jpcViewRandom()
                if (hasStress) jpcViewStress()
            }
    }

@Composable
fun jpcButtonPanel(
    label:      String,
    colorBack:  ColorPalette,
    action:     () -> Unit
) =
    Button({ action() },
        shape = (2f * paddingCommon).let { RoundedCornerShape(it, it, it, it) },
        colors = ButtonDefaults.buttonColors(containerColor = colorFrom(colorBack)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = label,
            letterSpacing = 0.sp,
            overflow = TextOverflow.Clip,
            style = MaterialTheme.typography.labelLarge)
    }

@Composable
fun RowScope.jpcViewPanel(colorBack: ColorPalette, colorBord: ColorPalette)
  = Box(modifier = Modifier
        .background(colorFrom(colorBack))
        .border(widthBordPanel.dp, colorFrom(colorBord))
        .fillMaxHeight()
        .weight(1.0f)
) {}

@Composable
fun RowScope.jpcViewDraws()
  = jpcViewPanel(ColorPalette.BackDraw,   ColorPalette.BordDraw)

@Composable
fun RowScope.jpcViewPerfs()
  = jpcViewPanel(ColorPalette.BackPerf,   ColorPalette.BordPerf)

@Composable
fun RowScope.jpcViewRandom()
  = jpcViewPanel(ColorPalette.BackRandom, ColorPalette.BordRandom)

@Composable
fun RowScope.jpcViewStress()
  = jpcViewPanel(ColorPalette.BackStress, ColorPalette.BordStress)
