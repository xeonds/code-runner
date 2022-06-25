package xyz.xeonds.mirai.coderunner

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.FriendMessageEvent
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.utils.info

object PluginMain : KotlinPlugin(JvmPluginDescription(
    id = "xyz.xeonds.mirai.coderunner", name = "Code-Runner", version = "0.1.0"
) {
    author("xeonds")
    info(
        """
            代码运行工具
        """.trimIndent()
    )
}) {
    @OptIn(ExperimentalSerializationApi::class)
    override fun onEnable() {
        logger.info { "Code-Runner loaded" }
        //配置文件目录 "${dataFolder.absolutePath}/"
        val eventChannel = GlobalEventChannel.parentScope(this)
        eventChannel.subscribeAlways<GroupMessageEvent> {
            if (message.contentToString().startsWith("```")) {
                //code:二元数组，分别为语言标志和待执行代码
                val code=message.contentToString().split("\n", limit = 2)
                group.sendMessage(CodeRunner(code[0].replace("```",""),code[1]).run().toString())
            }
        }
        eventChannel.subscribeAlways<FriendMessageEvent> {
            if (message.contentToString().startsWith("```")) {
                val code=message.contentToString().split("\n", limit = 2)
                sender.sendMessage(CodeRunner(code[0].replace("```",""),code[1]).run().toString())
            }
        }
    }
}
