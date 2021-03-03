package de.kxmpetentes.engine.utils

import java.io.*
import java.net.URL
import java.nio.charset.StandardCharsets
import javax.net.ssl.HttpsURLConnection


class Hastebin {


  fun post(message: String, url: String, raw: Boolean): String {
    val postData: ByteArray = message.toByteArray(StandardCharsets.UTF_8)
    val postDataLength: Int = postData.size

    val url: URL = URL(url)
    val connection: HttpsURLConnection = url.openConnection() as HttpsURLConnection
    connection.doOutput = true
    connection.instanceFollowRedirects = false
    connection.requestMethod = "POST"
    connection.setRequestProperty("User-Agent", "Hastebin Java API")
    connection.setRequestProperty("Content-Length", postDataLength.toString())
    connection.useCaches = false

    var response: String = ""
    val wr: DataOutputStream

    try {
      wr = DataOutputStream(connection.outputStream)
      wr.write(postData)
      val reader: BufferedReader = BufferedReader(InputStreamReader(connection.inputStream))
      response = reader.readLine()
    } catch (exception: IOException) {
      exception.printStackTrace()
    }

    if (response.contains("\"key\"")) {
      response = response.substring(response.indexOf(":") + 2, response.length - 2)

      val postUrl: String
      if (raw) {
        postUrl = "${url.toString()}/raw"
      } else {
        postUrl = url.toString()
      }

      response = postUrl + response
    }

    return response
  }

}
