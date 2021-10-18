package io.github.kakaocup.compose.node

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import io.github.kakaocup.compose.node.core.KDSL
import io.github.kakaocup.compose.screen.ComposeScreen
import java.lang.Exception

open class KNode : KDSL<KNode>, NodeAssertions, NodeActions, TextActions {
    override val nodeMatcher: SemanticsMatcher
    override val composeTestRule: AndroidComposeTestRule<*, *>
    override val useUnmergedTree: Boolean

    constructor(composeScreen: ComposeScreen<*>, function: ViewBuilder.() -> Unit) {
        composeTestRule = composeScreen.composeTestRule

        val viewBuilder = ViewBuilder().apply(function)

        nodeMatcher = viewBuilder.nodeMatcher ?: throw Exception("No matchers declared for KNode")
        useUnmergedTree = viewBuilder.useUnmergedTree
    }
}
