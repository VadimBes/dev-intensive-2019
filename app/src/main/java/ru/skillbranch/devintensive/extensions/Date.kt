package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*


const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String = "HH:mm:ss dd.MM.yy"):String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int,units:TimeUnits = TimeUnits.SECOND):Date{
    var time = this.time

    time += when(units){
        TimeUnits.SECOND->value * SECOND
        TimeUnits.MINUTE->value * MINUTE
        TimeUnits.HOUR->value * HOUR
        TimeUnits.DAY->value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date:Date = Date()): String {
    val rep = date.time -this.time


    return when(rep){
        in 0..1* SECOND->"только что"
        in 1* SECOND..45* SECOND->"несколько секунд назад"
        in 45* SECOND..75* SECOND->"минуту назад"
        in 75* SECOND..45* MINUTE->{
            val minute = rep/ MINUTE
            "$minute ${if(minute in 1..4) "минуты" else "минут"} назад"
        }
        in 45* MINUTE..75* MINUTE->"час назад"
        in 75* MINUTE..22* HOUR->{
            val hour = rep/ HOUR
            "$hour ${if (hour in 1..4) "часа" else "часов"} назад"
        }
        in 22* HOUR..26* HOUR->"день назад"
        in 26* HOUR..360* DAY->{
            val days = rep/ DAY
            "$days ${if((days%10) in 1..4) "дня" else "дней"} назад"
        }
        in 360* DAY..Long.MAX_VALUE ->"более года назад"
        in 0 downTo -1* SECOND->"только что"
        in -1* SECOND downTo -45* SECOND->"через несколько секунд"
        in -45* SECOND downTo -75* SECOND->" через минуту"
        in -75* SECOND downTo -45* MINUTE->{
            val minute = (rep/ MINUTE - 1)*(-1)
            "через $minute ${if(minute in 1..4) "минуты" else "минут"}"
        }
        in -45* MINUTE downTo -75* MINUTE->"через час"
        in -75* MINUTE downTo -22* HOUR->{
            val hour = (rep/ HOUR - 1)*(-1)
            "через $hour ${if (hour in 1..4) "часа" else "часов"}"
        }
        in -22* HOUR downTo -26* HOUR->"через день"
        in -360* DAY .. -26* HOUR-> {
            val days = (rep/ DAY - 1)*(-1)
            "через $days ${if((days%10) in 1..4) "дня" else "дней"}"
        }
        in -360* DAY downTo Long.MIN_VALUE -> "более чем через год"
        else->" "
    }

}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}