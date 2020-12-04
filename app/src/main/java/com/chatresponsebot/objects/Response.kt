package com.chatresponsebot.objects

import com.chatresponsebot.objects.Constants.open_Google
import com.chatresponsebot.objects.Constants.open_Search
import java.lang.Exception
import java.util.*

object Response {

    fun normalResponses( message: String): String {
        val random = (0..2).random()
        val userMessage = message.toLowerCase(Locale.ROOT)

        return when {

            // If user types "Hello" or "hi"
            userMessage.contains("hello") || userMessage.contains("hi") || userMessage.contains("hey")    -> {
                when (random) {
                    0 -> "Hello Dude!"
                    2 -> "What is up?"
                    3 -> "Hey!"
                    else -> "dunno about that"
                }
            }


            userMessage.contains("flip") && userMessage.contains("coin") -> {
                val randomNumber = (0..1).random()
                val result = if (randomNumber==0) "heads" else "tails"
                return "It landed on $result!"
            }


            // solve user given equation
            userMessage.contains("solve") || userMessage.contains("do") -> {
                val equation : String = userMessage.substringAfter("solve")
                return try {
                    val equals = SolvingLogic.solveGivenMath(equation ?: "0")
                    return equals.toString()
                }catch (e: Exception){
                    "Sorry Dude I cannot help you with that one..."
                }

            }

            // respond with current time
            userMessage.contains("time") && userMessage.contains("?") -> {
                Time.timeStampCreator()
            }

            //open google
            userMessage.contains("open") && userMessage.contains("google") -> {
                open_Google
            }

            //open google
            userMessage.contains("search")  -> {
                open_Search
            }

            // if user asks "how are you"
            userMessage.contains("how are you") -> {
                when (random) {
                    0 -> "good how about you?"
                    2 -> "not so great... need some more cpu power..feeling slow today"
                    3 -> "ehh good I guess"
                    else -> "Do not know sorry."
                }
            }

            else -> {
                    when (random) {
                        0 -> "yes...?"
                        2 -> "yea right...Like I would know that?"
                        3 -> "hmm...nope I do not know that..."
                        else -> "I do not understand *robot noises"
                    }
            }


        }
    }

}