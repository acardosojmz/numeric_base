package edu.itvo.convertion

import android.util.Log
import kotlin.math.pow

class Convert {
    companion object {
        fun decimalToBaseX( numberDecimal: Long, base:Long):String {
            val possibilities="0123456789ABCDEF"
            var result=""
            var numberDecimalTmp = numberDecimal
            while (numberDecimalTmp>0) {
                val module= numberDecimalTmp.toInt().rem(base.toInt())
                result = possibilities.substring(module, module+1) + result
                numberDecimalTmp = numberDecimalTmp.div(base)
            }
            return result
        }

        fun binaryToDecimal(binary: String): Long {
            var binaryTmp=binary.reversed()
            var decimalNumber =0L
            for (i in binaryTmp.indices) {
                decimalNumber+= (binaryTmp.substring(i,i+1).toLong() * 2.0.pow(i.toDouble())).toLong()
            }
            return decimalNumber
        }



        private fun <K, V> getKey(hashMap: Map<K, V>, target: V): K {
            return hashMap.filter { target == it.value }.keys.first()
        }
        fun octalToBinary(octal:String):String{
            val map = mapOf("0" to "000","1" to "001", "2" to "010", "3" to "011",
                        "4" to "100", "5" to "101", "6" to "110","7" to "111")
            val octalTmp = octal.trim().toCharArray()
            var binary=""
            octalTmp.forEach {
                 binary+= map[it.toString()]
            }
            return binary
        }

        fun binaryToOctal(binary:String):String{
            val map = mapOf("0" to "000","1" to "001", "2" to "010", "3" to "011",
                "4" to "100", "5" to "101", "6" to "110","7" to "111")
            var binaryTmp=binary.trim()
            binaryTmp = binaryTmp.padStart(binaryTmp.length + (3-(binaryTmp.length % 3)),'0')
            var result = ""
            while (binaryTmp.isNotEmpty()){
                result= getKey(map, binaryTmp.substring(binaryTmp.length-3,binaryTmp.length)) + result
                binaryTmp= binaryTmp.substring(0,binaryTmp.length-3)
            }
            return  result
        }

        fun hexadecimalToBinary(hexadecimal:String):String{
            val map = mapOf("0" to "0000","1" to "0001", "2" to "0010", "3" to "0011",
                "4" to "0100", "5" to "0101", "6" to "0110","7" to "0111",
                "8" to "1000", "9" to "1001", "A" to "1010","B" to "1011",
                "C" to "1100", "D" to "1101", "E" to "1110","F" to "1111",
            )
            val hexa = hexadecimal.toCharArray()
            var binary=""
            hexa.forEach {
                binary+= map[it.toString()]
            }
            return binary
        }
        fun binaryToHexadecimal(binary:String):String{
            val map = mapOf("0" to "0000","1" to "0001", "2" to "0010", "3" to "0011",
                "4" to "0100", "5" to "0101", "6" to "0110","7" to "0111",
                "8" to "1000", "9" to "1001", "A" to "1010","B" to "1011",
                "C" to "1100", "D" to "1101", "E" to "1110","F" to "1111",
            )
            var binaryTmp=binary.trim()
            binaryTmp = binaryTmp.padStart(binaryTmp.length + (4-(binaryTmp.length % 4)),'0')
            var result = ""
            while (binaryTmp.isNotEmpty()){
                result= getKey(map, binaryTmp.substring(binaryTmp.length-4,binaryTmp.length)) + result
                binaryTmp= binaryTmp.substring(0,binaryTmp.length-4)
            }
            return  result
        }
    }
}