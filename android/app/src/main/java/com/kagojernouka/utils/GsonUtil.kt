package com.kagojernouka.utils

import com.google.gson.Gson


/*
 * Created by : 
 * <a href="https://www.github.com/ashiqursuperfly">Ashiqur Rahman</a> on 11/15/20.
*/

object GsonUtil {
    fun toJson(obj: Any): String {
        return Gson().toJson(obj)
    }

    fun<T> fromJson(jsonStr: String, any: Class<T>): T {
        return Gson().fromJson(jsonStr, any)
    }
}