package br.com.grupofleury.core.utils

import android.annotation.SuppressLint
import android.util.Base64
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("DefaultLocale")
fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.capitalize() }

fun String.formatStringDate(
    outputFormat: String = "yyyy-MM-dd'T'HH:mm:ss",
    inputFormat: String = "yyyy-MM-dd'T'HH:mm:ss",
    outputLocale: Locale = LocaleUtils.PT_BR,
    inputLocale: Locale = LocaleUtils.PT_BR
): String {
    var format =
        if (inputFormat.isNotEmpty()) SimpleDateFormat(
            inputFormat,
            inputLocale
        ) else SimpleDateFormat.getDateTimeInstance()

    val newDate = format.parse(this)

    format = SimpleDateFormat(outputFormat, outputLocale)

    return if (newDate != null) format.format(newDate) else ""
}

fun String.formatStringDateOrNull(
    outputFormat: String = "yyyy-MM-dd'T'HH:mm:ss",
    inputFormat: String = "yyyy-MM-dd'T'HH:mm:ss",
    outputLocale: Locale = LocaleUtils.PT_BR,
    inputLocale: Locale = LocaleUtils.PT_BR
): String? {
    val result = this.formatStringDate(outputFormat, inputFormat, outputLocale, inputLocale)
    return if (result.isEmpty()) null else result
}

fun String?.toDate(
    inputFormat: String = "yyyy-MM-dd'T'HH:mm:ss",
    inputLocale: Locale = LocaleUtils.PT_BR
): Date? {
    return try {
        if (this.isNullOrEmpty()) return null

        val format =
            if (inputFormat.isNotEmpty()) SimpleDateFormat(
                inputFormat,
                inputLocale
            ) else SimpleDateFormat.getDateTimeInstance()

        format.parse(this)
    } catch (e: Exception) {
        null
    }
}

fun String.encodeToBase64String(): String =
    Base64.encodeToString(this.toByteArray(StandardCharsets.UTF_8), Base64.NO_WRAP) ?: ""

fun String.replaceSpecialChars(): String {
    return this.replace(".", "").replace("-", "")
        .replace("(", "").replace(")", "")
        .replace("/", "").replace(" ", "")
        .replace("*", "")
}

fun String.getHiddenPhoneWithDdd(ddd: String): String {
    var hiddenPhone = "(${ddd}) "
    for(i in 1..(this.length - 4)){
        hiddenPhone += "X"
    }
    hiddenPhone +=  "-"
    hiddenPhone += this.substring(this.length - 4, this.length)
    return hiddenPhone
}

fun String.getHiddenEmail(): String {
    val emailUser = substringBeforeLast("@")
    var hiddenEmail = ""
    for(i in 1..5){
        hiddenEmail += "x"
    }
    hiddenEmail += this.substring((emailUser.length - 4), this.length)
    return hiddenEmail
}