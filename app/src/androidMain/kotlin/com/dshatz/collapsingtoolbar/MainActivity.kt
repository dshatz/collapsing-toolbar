package com.dshatz.collapsingtoolbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.lerp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp

/**
 * Example activity to demonstrate usage.
 * Collapsed height is calculated from minimum height of **only pinned** children, not all children.
 *
 * Additionally, demonstrates status bar inset handling.
 */
class MainActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Column {
                    CollapsingToolbarWithTitle("Page title", expandedHeight = 130.dp, toolbarContent = { progress ->
                        Row(
                            modifier = Modifier.fillMaxWidth().height(60.dp).padding(15.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .background(MaterialTheme.colorScheme.secondary, shape = CircleShape),
                                    contentAlignment = Alignment.Center) {
                                    Text("AS", color = Color.White)
                                }
                                Text("Arnold Schwarzenegger", modifier = Modifier.alpha(progress))
                            }

                            Text("Action")
                        }
                    }) {
                        Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.verticalScroll(
                            rememberScrollState()
                        )) {
                            (1..20).forEach {
                                Card(Modifier.fillMaxWidth().heightIn(min = 40.dp)) {
                                    Text("Item $it", modifier = Modifier.padding(10.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CollapsingToolbarWithTitle(title: String, expandedHeight: Dp, toolbarContent: @Composable (progress: Float) -> Unit = {}, content: @Composable ColumnScope.() -> Unit) {
    val state = rememberCollapsingToolbarScaffoldState()
    Box(Modifier.fillMaxSize()) {
        Image(
            painter = ColorPainter(MaterialTheme.colorScheme.primary),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        CollapsingToolbarScaffold(
            modifier = Modifier.fillMaxWidth().statusBarsPadding(),
            state = state,
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbar = {
                val progress = state.toolbarState.progress

                CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onPrimary) {

                    /**
                     * The height of the smallest .pinned() element will be the collapsed height.
                     */
                    Box(Modifier.fillMaxWidth()
                        .background(Color.Transparent).pin()) {
                        toolbarContent(progress)
                    }

                    /**
                     * The expanded height will be the height of the largest element.
                     */
                    Box(modifier = Modifier.height(expandedHeight)) {}

                    // Title
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = lerp(18.sp, 32.sp, progress),
                            fontWeight = lerp(FontWeight.Normal, FontWeight.Light, progress)
                        ),
                        modifier = Modifier.padding(start = lerp(0.dp, 15.dp, progress), bottom = 15.dp).road(
                            whenCollapsed = Alignment.Center,
                            whenExpanded = Alignment.BottomStart
                        )
                    )
                }
            }) {
            Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface)) {
                content()
            }
        }
    }
}
