package com.twocoders.extensions.common

import java.net.URL

fun URL.addSlashIfMissing(): String {
    with(this.toExternalForm()) {
        return if (this.endsWith(SLASH)) this else this.plus(SLASH)
    }
}