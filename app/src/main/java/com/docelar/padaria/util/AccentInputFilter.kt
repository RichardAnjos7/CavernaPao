package com.docelar.padaria.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.Normalizer

/**
 * TextWatcher que normaliza o texto para NFC (Unicode) após cada alteração.
 * Converte sequências como "a" + tilde combinante (U+0303) em "ã" (U+00E3),
 * corrigindo o problema em que o "ã" não aparece em alguns teclados/emuladores.
 */
object AccentInputFilter {

    fun install(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            private var isNormalizing = false
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s == null || isNormalizing) return
                val current = s.toString()
                val normalized = Normalizer.normalize(current, Normalizer.Form.NFC)
                if (normalized != current) {
                    isNormalizing = true
                    val cursor = editText.selectionStart
                    val newCursor = (cursor + normalized.length - current.length).coerceIn(0, normalized.length)
                    s.replace(0, s.length, normalized)
                    editText.setSelection(newCursor)
                    isNormalizing = false
                }
            }
        })
    }
}
