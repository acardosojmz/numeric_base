package edu.itvo.convertion

import android.text.InputFilter
import android.text.Spanned
import android.util.Log
import java.util.regex.Matcher
import java.util.regex.Pattern


class ValidateFilter (private val base:Int): InputFilter {
    override fun filter(
        source: CharSequence, start: Int, end: Int, dest: Spanned,
        dstart: Int, dend: Int
    ): CharSequence? {
        for (i in start until end) {
            val checkMe = source[i].toString().uppercase()
            val charValidate = "0123456789ABCDEF".substring(0,base)
            val pattern: Pattern =
                Pattern.compile("[" + charValidate+"]*")
            val matcher: Matcher = pattern.matcher(checkMe)
            val valid: Boolean = matcher.matches()
            if (!valid) {
                Log.d("", "invalid")
                return ""
            }
        }
        return null
    }
}