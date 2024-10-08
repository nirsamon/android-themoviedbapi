package com.nino.codilitytask3tmdb.util

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.nino.codilitytask3tmdb.ui.main.LocalNavController
import com.nino.codilitytask3tmdb.ui.theme.AppTheme

fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.getString(@StringRes resId: Int) =
    activity.getString(resId)

fun ComposeContentTestRule.setTestContent(content: @Composable BoxScope.() -> Unit) = setContent {
    AppTheme {
        CompositionLocalProvider(LocalNavController provides rememberNavController()) {
            Surface(Modifier.fillMaxSize().systemBarsPadding()) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    content()
                }
            }
        }
    }
}
