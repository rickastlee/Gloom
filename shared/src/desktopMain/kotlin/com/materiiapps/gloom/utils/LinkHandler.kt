package com.materiiapps.gloom.utils

import androidx.compose.runtime.compositionLocalOf
import java.awt.Desktop
import java.net.URI

actual class LinkHandler {

    // Custom tabs only supported on Android
    actual fun openLink(link: String, forceCustomTab: Boolean) {
        Desktop.getDesktop().browse(URI.create(link))
    }

}

actual val LocalLinkHandler = compositionLocalOf<LinkHandler> { error("No link handler set") }