package com.twocoders.util.commonextensions

import java.net.URL

fun URL.addSlashIfMissing(): String {
    with(this.toExternalForm()) {
        return if (this.endsWith(SLASH)) this else this.plus(SLASH)
    }
}