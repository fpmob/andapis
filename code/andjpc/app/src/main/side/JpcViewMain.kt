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
        var hasExecRes by remember { mutableStateOf(true) }
        var hasApiExec by remember { mutableStateOf(true) }
        var hasApiTree by remember { mutableStateOf(true) }
    /* TODO: @@@ DEPRECATED THE BUTTONS
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(0.32f)
                    .padding(top = paddingCommon.dp,
                             end = paddingCommon.dp,
                          bottom = paddingCommon.dp)) {
                jpcButtonPanel("API group", ColorPalette.BackApiTree   ) { hasApiTree  = !hasApiTree }
                jpcButtonPanel("exec new",  ColorPalette.BackApiExec) { hasApiExec = !hasApiExec}
            }
            Column(modifier = Modifier.weight(0.32f)
                    .padding(top = paddingCommon.dp,
                             end = paddingCommon.dp,
                          bottom = paddingCommon.dp)) {
                jpcButtonPanel("API spec",  ColorPalette.BackApiSpec   ) { hasApiFinal  = !hasApiFinal }
                jpcButtonPanel("exec old",  ColorPalette.BackExecRes) { hasExecRes = !hasExecRes }
            }
        }
    */
        var mutStateExecResult   by remember { mutableStateOf("") }
        var mutStateExecSpecPair by remember { mutableStateOf(Pair(ApiSpec(),ApiSpec())) }

        if (hasExecRes)  Row(modifier = Modifier.weight(1.0f)) {
            jpcViewExecResult(mutStateExecResult) }
        if (hasApiExec)  Row(modifier = Modifier.weight(1.0f)) {
            jpcViewApiExec(
                mutStateExecSpecPair,
                { specPair ->
                    mutStateExecResult = apiCalls[  specPair.first .name + "."
                                                  + specPair.second.name]
                        ?.let { it() }
                        ?: "### MISSING API CALL"
                }) }
        if (hasApiTree) Row(modifier = Modifier.weight(1.0f)) {
            jpcViewApiTree(
                ColorPalette.BackApiTree,
                ColorPalette.BordApiTree,
                ColorPalette.ForeApiTree,
                apiSpecs,
                { specPair -> mutStateExecSpecPair = specPair }) }
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
fun RowScope.jpcViewApiTree(
    colorBack:      ColorPalette,
    colorBord:      ColorPalette,
    colorFore:      ColorPalette,
    specList:       List<ApiSpec>,
    onSpecSelected: (Pair<ApiSpec,ApiSpec>) -> Unit
) = jpcViewPanel(colorBack, colorBord) {
        LazyColumn {
            items(specList) { spec: ApiSpec ->
                jpcViewApiTreeItemRec(colorFore, Pair(spec,spec), onSpecSelected)
            }
        }
    }

@Composable
fun jpcViewApiTreeItemRec(
    colorFore:      ColorPalette,
    specPair:       Pair<ApiSpec,ApiSpec>,
    onSpecSelected: (Pair<ApiSpec,ApiSpec>) -> Unit
): Unit = Column {
        var mutStateExpanded by remember { mutableStateOf(false) }
        val spec = specPair.second
        Row(modifier = Modifier.clickable {
            if (spec.list.isEmpty())
                onSpecSelected(specPair)
            else
                mutStateExpanded = !mutStateExpanded
        }) {
            jpcViewTextItem(spec.name, colorFore)
        }
        Column(modifier = Modifier.padding(start = paddingCommon.dp)) {
            if (mutStateExpanded)
                spec.list.map { subspec: ApiSpec ->
                    jpcViewApiTreeItemRec(colorFore, Pair(spec,subspec), onSpecSelected)
                }
        }
    }

@Composable
fun RowScope.jpcViewApiExec(
    specPair:        Pair<ApiSpec,ApiSpec>,
    onSpecSelected: (Pair<ApiSpec,ApiSpec>) -> Unit
) = jpcViewPanel(ColorPalette.BackApiExec, ColorPalette.BordApiExec) {
        Box(modifier = Modifier.clickable { onSpecSelected(specPair) }) {
            jpcViewTextItem(specPair.second.exec, ColorPalette.ForeApiExec, 4)
        }
    }

@Composable
fun RowScope.jpcViewExecResult(
    result: String
) = jpcViewPanel(ColorPalette.BackExecRes, ColorPalette.BordExecRes) {
        jpcViewTextItem(result, ColorPalette.ForeApiExec, 4)
    }