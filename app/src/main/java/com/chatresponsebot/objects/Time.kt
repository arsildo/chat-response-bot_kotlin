package com.chatresponsebot.objects

import java.sql.Timestamp
import java.text.SimpleDateFormat

object Time {

    fun timeStampCreator(): String {
        val timeStamp = Timestamp(System.currentTimeMillis())
        val simpleDateFormat = SimpleDateFormat("HH:mm")
        val time = simpleDateFormat.format(timeStamp.time)

        return time.toString()
    }

}