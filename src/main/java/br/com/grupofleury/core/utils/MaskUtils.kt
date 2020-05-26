package br.com.grupofleury.core.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import br.com.grupofleury.core.utils.replaceSpecialChars


class MaskUtils {
    companion object {
        fun setMask(mask: String, view: EditText): TextWatcher {
            return object : TextWatcher {
                var isUpdating: Boolean = false
                var oldString: String = ""
                override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    val str = s.toString().replaceSpecialChars()
                    var strMasked = ""

                    if (count == 0)//is deleting
                        isUpdating = true

                    if (isUpdating) {
                        oldString = str
                        isUpdating = false
                        return
                    }

                    var i = 0
                    for (m: Char in mask.toCharArray()) {
                        if (m != '#' && str.length > oldString.length) {
                            strMasked += m
                            continue
                        }
                        try {
                            strMasked += str[i]
                        } catch (e: Exception) {
                            break
                        }
                        i++
                    }

                    isUpdating = true
                    view.setText(strMasked)
                    view.setSelection(strMasked.length)
                }

                override fun afterTextChanged(editable: Editable) {}
            }
        }

        const val DATE_MASK = "##/##/####"
        const val CPF_MASK = "###.###.###-##"
    }
}