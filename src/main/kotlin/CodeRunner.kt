package xyz.xeonds.mirai.coderunner

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class CodeRunner(private val language: String, val code: String) {
    private var res = ""
    fun run(): CodeRunner {
        val client = OkHttpClient()
        val request = Request.Builder()
        var langId = ""
        var langExt = ""

        when (language) {
            //code runner of c. Powered py runoob.com!
            "c" -> {
                langId = "7";langExt = "c"
            }
            "python" -> {
                langId = "15";langExt = "py3"
            }
            "java" -> {
                langId = "8";langExt = "java"
            }
        }
        request.method(
            "POST", FormBody.Builder()
                .add("code", code)
                .add("token", "4381fe197827ec87cbac9552f14ec62a")
                .add("stdin", "")
                .add("language", langId)
                .add("fileext", langExt).build())
            .url("https://tool.runoob.com/compile2.php")

        this.res = client.newCall(request.build()).execute().body?.string() ?: ""

        return this
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun toString(): String {
        val data = Json.decodeFromString<ResultC>(res)
        val msg = StringBuilder()

        if (res != "") {
            msg.append("---执行结果---\n")
            msg.append("语言：${language}\n")
            msg.append("输出：${data.output}\n")
            if (data.errors.trimIndent() != "") {
                msg.append("错误：${data.errors}\n")
            }
            msg.append("Code-Runner-V0.1.0")
            return msg.toString()
        }
        return "无结果"
    }
}

@Serializable
data class ResultC(
    @SerialName("output") val output: String,
    @SerialName("errors") val errors: String,
)