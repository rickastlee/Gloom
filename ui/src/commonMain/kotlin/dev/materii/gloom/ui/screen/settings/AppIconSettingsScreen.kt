package dev.materii.gloom.ui.screen.settings

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import dev.materii.gloom.Res
import dev.materii.gloom.domain.manager.enums.AppIcon
import dev.materii.gloom.domain.manager.enums.AppIconCollection
import dev.materii.gloom.ui.component.NavBarSpacer
import dev.materii.gloom.ui.component.toolbar.LargeToolbar
import dev.materii.gloom.ui.screen.settings.component.SettingsHeader
import dev.materii.gloom.ui.screen.settings.component.appicon.AppIconSetting
import dev.materii.gloom.ui.screen.settings.viewmodel.AppIconsSettingsViewModel
import dev.icerock.moko.resources.compose.stringResource

class AppIconsSettingsScreen : Screen {

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    override fun Content() {
        val viewModel: AppIconsSettingsViewModel = koinScreenModel()
        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

        Scaffold(
            topBar = { Toolbar(scrollBehavior) },
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { pv ->
            LazyColumn(
                modifier = Modifier
                    .padding(pv)
                    .selectableGroup()
            ) {
                AppIconCollection.entries.forEach { iconCollection ->
                    item(iconCollection.name) {
                        SettingsHeader(stringResource(iconCollection.nameRes))
                    }

                    AppIcon.entries.filter { it.collection == iconCollection }.forEach { appIcon ->
                        item(appIcon) {
                            AppIconSetting(
                                appIcon = appIcon,
                                selected = viewModel.preferenceManager.appIcon == appIcon,
                                onSelected = { viewModel.setIcon(appIcon) }
                            )
                        }
                    }
                }

                item("spacer") { NavBarSpacer() }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun Toolbar(
        scrollBehavior: TopAppBarScrollBehavior
    ) {
        LargeToolbar(
            title = stringResource(Res.strings.settings_app_icon),
            scrollBehavior = scrollBehavior
        )
    }

}