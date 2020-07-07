package br.com.fleury.core.utils

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SecureData(
    var value: ByteArray?,
    var iv: ByteArray?,
    var salt: ByteArray?
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SecureData

        if (value != null) {
            if (other.value == null) return false
            if (!value!!.contentEquals(other.value!!)) return false
        } else if (other.value != null) return false
        if (iv != null) {
            if (other.iv == null) return false
            if (!iv!!.contentEquals(other.iv!!)) return false
        } else if (other.iv != null) return false
        if (salt != null) {
            if (other.salt == null) return false
            if (!salt!!.contentEquals(other.salt!!)) return false
        } else if (other.salt != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = value?.contentHashCode() ?: 0
        result = 31 * result + (iv?.contentHashCode() ?: 0)
        result = 31 * result + (salt?.contentHashCode() ?: 0)
        return result
    }
}