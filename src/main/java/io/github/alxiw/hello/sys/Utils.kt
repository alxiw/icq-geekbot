package io.github.alxiw.hello.sys

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pengrad.telegrambot.model.Chat
import io.github.alxiw.hello.sys.Language.Companion.fromCode

object Utils {

    fun checkLanguage(cmd: String): Language? {
        when (cmd.lowercase()) {
            "" -> return Language.EN
            "uk" -> return Language.UK
            "us" -> return Language.US
        }

        return fromCode(cmd)
    }

    fun makeUpName(chat: Chat): String {
        val first = chat.firstName()
        val last = chat.lastName()
        if (first.isNullOrEmpty() && last.isNullOrEmpty()) {
            return ""
        }
        if (last.isNullOrEmpty()) {
            return first
        }
        if (first.isNullOrEmpty()) {
            return last
        }

        return "$first $last"
    }

    fun getDatabaseResponse(value: Int): Response {
        return if (value <= -1) {
            return Response.ERROR
        } else if (value == 0) {
            return Response.NO_CHANGES
        } else {
            return Response.SUCCESS
        }
    }

    fun parseTranslateResponse(responseString: String): String? {
        val gson = Gson()
        val type = object : TypeToken<List<Any?>>() {}.type
        val parsedData: List<Any?> = gson.fromJson(responseString, type)
        if (parsedData.isEmpty()) return null
        val list = parsedData[0]
        if (list !is List<*> || list.isEmpty()) return null
        val result = StringBuilder()
        list.forEach {
            if (it !is List<*>) return@forEach
            if (it.isEmpty()) return@forEach
            val chunk = it[0]
            if (chunk !is String) return@forEach
            result.append(chunk)
        }
        return result.toString()
    }
}
