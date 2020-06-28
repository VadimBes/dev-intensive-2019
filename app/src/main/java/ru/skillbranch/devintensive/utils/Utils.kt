package ru.skillbranch.devintensive.utils

import java.util.*

object Utils {
    fun parseFullName(fullName:String?):Pair<String?,String?>{
        val pars = fullName?.split(" ")
        var firstName = pars?.getOrNull(0)
        var lastName = pars?.getOrNull(1)
        if (firstName ==""){
            firstName = null
            lastName = null
        }
        println("$firstName $lastName")
        return Pair(firstName,lastName)
    }

    fun transliteration(payload: String,divider:String = " "): String {
        val res = payload.split(" ")
        var result = ""

        res.forEach {
        var buf = it.toLowerCase(Locale("ru")).replace(Regex("[абвгдеёжзийклмнопрстуфхцчшщъыьэюя]")){
            when(it.value){
                "а"-> "a"
            "б"-> "b"
            "в"-> "v"
            "г"-> "g"
            "д"-> "d"
            "е"-> "e"
            "ё"-> "e"
            "ж"-> "zh"
            "з"-> "z"
            "и"-> "i"
            "й"-> "i"
            "к"-> "k"
            "л"-> "l"
            "м"-> "m"
            "н"-> "n"
            "о"-> "o"
            "п"-> "p"
            "р"-> "r"
            "с"-> "s"
            "т"-> "t"
            "у"-> "u"
            "ф"-> "f"
            "х"-> "h"
            "ц"-> "c"
            "ч"-> "ch"
            "ш"-> "sh"
            "щ"-> "sh'"
            "ъ"-> ""
            "ы"-> "i"
            "ь"-> ""
            "э"-> "e"
            "ю"-> "yu"
            "я"-> "ya"
                else -> it.value
            }
        }
            buf = buf.replace(buf[0],buf[0].toUpperCase())
            result+=buf+divider
        }
        result = result.removeRange(result.length-1,result.length)
        return result
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val firstI = firstName?.firstOrNull()
        val secondI = lastName?.firstOrNull()
        if ((firstI==null||firstI==' ') && (secondI==null||secondI==' ')) return null
    return "${firstName?.firstOrNull()?:""}${lastName?.firstOrNull()?:""}"
    }
}