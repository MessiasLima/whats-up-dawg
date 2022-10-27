package io.github.messiasjunior.whatsupdawg.core.util

import java.util.Locale

/**
 * I needed to do it myself because the [String.capitalize] is deprecated
 */
fun String.capitalizeFirstChar(): String {
    return replaceFirstChar {
        if (it.isLowerCase()) {
            it.titlecase(Locale.getDefault())
        } else {
            it.toString()
        }
    }
}
