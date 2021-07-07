package com.example.learningmviarchitecture

import android.content.Context
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

@Throws(Exception::class)
fun getStringFromFile(context: Context, filePath: String?): String {
    val stream: InputStream = context.classLoader.getResourceAsStream(filePath)
    val ret = convertStreamToString(stream)
    stream.close()
    return ret
}

@Throws(Exception::class)
fun convertStreamToString(`is`: InputStream?): String {
    val reader = BufferedReader(InputStreamReader(`is`))
    val sb = StringBuilder()
    var line: String?
    while (reader.readLine().also { line = it } != null) {
        sb.append(line).append("\n")
    }
    reader.close()
    return sb.toString()
}