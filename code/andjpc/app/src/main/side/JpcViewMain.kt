//  Copyright © 2024 - 2025 Christopher Augustus
//
//  This Source Code Form is subject to the terms of the Mozilla Public
//  License, v. 2.0. If a copy of the MPL was not distributed with this
//  file, You can obtain one at https://mozilla.org/MPL/2.0/.

package org.andapis.side

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*

import org.andapis.pure.domain.*
import org.andapis.pure.present.*
import org.andapis.ui.theme.AndjpcTheme

fun colorFrom(c: ColorPalette)
  = c.value.let { Color(red = it.r, green = it.g, blue = it.b, alpha = it.a) }

val paddingCommon   = 16
val paddingItemVert = 4
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
        var hasApiGroup by remember { mutableStateOf(true) }
        var hasApiSpec  by remember { mutableStateOf(true) }
        var hasExecNew  by remember { mutableStateOf(true) }
        var hasExecOld  by remember { mutableStateOf(true) }
    /* TODO: @@@ DEPRECATED THE BUTTONS
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(0.32f)
                    .padding(top = paddingCommon.dp,
                             end = paddingCommon.dp,
                          bottom = paddingCommon.dp)) {
                jpcButtonPanel("API group", ColorPalette.BackApiGroup   ) { hasApiGroup  = !hasApiGroup }
                jpcButtonPanel("exec new",  ColorPalette.BackExecNew) { hasExecNew = !hasExecNew}
            }
            Column(modifier = Modifier.weight(0.32f)
                    .padding(top = paddingCommon.dp,
                             end = paddingCommon.dp,
                          bottom = paddingCommon.dp)) {
                jpcButtonPanel("API spec",  ColorPalette.BackApiSpec   ) { hasApiSpec  = !hasApiSpec }
                jpcButtonPanel("exec old",  ColorPalette.BackExecOld) { hasExecOld = !hasExecOld }
            }
        }
    */
        var mutStateExec by remember { mutableStateOf("") }
        var mutStateSpec by remember { mutableStateOf(ApiSpec()) }
        if (hasApiGroup) Row(modifier = Modifier.weight(1.0f)) {
            jpcViewApiSpec(
                ColorPalette.BackApiGroup,
                ColorPalette.BordApiGroup,
                ColorPalette.ForeApiGroup,
                apiSpecs,
                { spec -> mutStateSpec = spec }) }
        if (hasApiSpec)  Row(modifier = Modifier.weight(1.0f)) {
            jpcViewApiSpec(
                ColorPalette.BackApiSpec,
                ColorPalette.BordApiSpec,
                ColorPalette.ForeApiSpec,
                mutStateSpec.list,
                { spec ->
                    if (spec.list.isEmpty())
                        mutStateExec = spec.exec
                    else
                        mutStateSpec = spec
                }) }
        if (hasExecNew)  Row(modifier = Modifier.weight(1.0f)) {
            jpcViewExecNew(mutStateExec) }
        if (hasExecOld)  Row(modifier = Modifier.weight(1.0f)) {
            jpcViewExecOld() }
    }

/* TODO: @@@ DEPRECATED THE BUTTONS
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
*/

@Composable
fun jpcViewTextItem(
    text:       String,
    colorFore:  ColorPalette,
    maxLines:   Int = 1
) = Text(
        text        = text,
        color       = colorFrom(colorFore),
        fontFamily  = FontFamily.Monospace,
        fontWeight  = FontWeight.Bold,
        style       = MaterialTheme.typography.titleLarge,
        maxLines    = maxLines,
        modifier    = Modifier
            .padding(
                start = paddingCommon.dp, top    = paddingItemVert.dp,
                end   = paddingCommon.dp, bottom = paddingItemVert.dp),
    )

@Composable
fun RowScope.jpcViewPanel(
    colorBack:  ColorPalette,
    colorBord:  ColorPalette,
    content:    @Composable() () -> Unit = {}
) = Box(modifier = Modifier
        .background(colorFrom(colorBack))
        .border(widthBordPanel.dp, colorFrom(colorBord))
        .fillMaxHeight()
        .padding(top    = paddingCommon.dp / 2,
                 bottom = paddingCommon.dp / 2)
        .weight(1.0f)
    ) { content() }

@Composable
fun RowScope.jpcViewApiSpec(
    colorBack:      ColorPalette,
    colorBord:      ColorPalette,
    colorFore:      ColorPalette,
    subspecs:       List<ApiSpec>,
    onItemSelected: (ApiSpec) -> Unit
) = jpcViewPanel(colorBack, colorBord) {
        LazyColumn {
            items(subspecs) { subspec ->
                Row(modifier = Modifier
                    .clickable { onItemSelected(subspec) }
                ) {
                    jpcViewTextItem(subspec.name, colorFore)
                }
            }
        }
    }

@Composable
fun RowScope.jpcViewExecNew(
    exec: String
) = jpcViewPanel(ColorPalette.BackExecNew, ColorPalette.BordExecNew) {
        jpcViewTextItem(exec, ColorPalette.ForeExecNew, 4)
    }

@Composable
fun RowScope.jpcViewExecOld()
  = jpcViewPanel(ColorPalette.BackExecOld,  ColorPalette.BordExecOld)