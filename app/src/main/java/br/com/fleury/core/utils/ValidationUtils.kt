package br.com.grupofleury.core.utils

import android.util.Patterns
import androidx.core.text.isDigitsOnly
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ValidationUtils{
    companion object {
        fun validateCPF(cpf : String) : Boolean{
            val cpfClean = cpf.replace(".", "").replace("-", "")

            //## check if size is eleven
            if (cpfClean.length != CPF_LENGTH)
                return false

            //## check if is number
            try {
                cpfClean.toLong()
            }catch (e : Exception){
                return false
            }

            //continue
            val dvCurrent10 = cpfClean.substring(9,10).toInt()
            val dvCurrent11= cpfClean.substring(10,11).toInt()

            //the sum of the nine first digits determines the tenth digit
            val cpfNineFirst = IntArray(9)
            var i = 1
            while (i < 10) {
                cpfNineFirst[i-1] = cpfClean.substring(i-1, i).toInt()
                i++
            }
            //multiple the nine digits for your weights: 10,9..2
            val sumProductNine = IntArray(9)
            var weight = 10
            var position = 0
            while (weight >= 2){
                sumProductNine[position] = weight * cpfNineFirst[position]
                weight--
                position++
            }
            //Verify the nineth digit
            var dvForTenthDigit = sumProductNine.sum() % 11
            dvForTenthDigit = 11 - dvForTenthDigit //rule for tenth digit
            if(dvForTenthDigit > 9)
                dvForTenthDigit = 0
            if (dvForTenthDigit != dvCurrent10)
                return false

            //### verify tenth digit
            val cpfTenFirst = cpfNineFirst.copyOf(10)
            cpfTenFirst[9] = dvCurrent10
            //multiple the nine digits for your weights: 10,9..2
            val sumProductTen = IntArray(10)
            var w = 11
            var pos = 0
            while (w >= 2){
                sumProductTen[pos] = w * cpfTenFirst[pos]
                w--
                pos++
            }
            //Verify the nineth digit
            var dvForeleventhDigit = sumProductTen.sum() % 11
            dvForeleventhDigit = 11 - dvForeleventhDigit //rule for tenth digit
            if(dvForeleventhDigit > 9)
                dvForeleventhDigit = 0
            if (dvForeleventhDigit != dvCurrent11)
                return false

            return true
        }

        fun validateBirthDate(birthDate: String) : Boolean {
            val pattern = "dd/MM/yyyy"
            val sdf = SimpleDateFormat(pattern, Locale.getDefault())
            sdf.isLenient = false

            return try {
                val initialDate = sdf.parse("01/01/1900")
                val birth = sdf.parse(birthDate)
                val today = Calendar.getInstance().time
                initialDate < birth && birth <= today
            } catch (e: ParseException) {
                false
            }
        }

        fun validateEmail(email: String) : Boolean {
            return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }


        fun validatePhone(phone: String) : Boolean {
            return phone.isNotEmpty() && phone.replaceSpecialChars().length == PHONE_LENGTH
                && phone.replaceSpecialChars().isDigitsOnly()
        }

        const val CPF_LENGTH = 11
        const val DATE_LENGTH = 10
        const val PHONE_LENGTH = 11
    }
}