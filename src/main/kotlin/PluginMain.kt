package xyz.xeonds.mirai.coderunner

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
    override fun onEnable() {
        logger.info { "Code-Runner loaded" }
        //配置文件目录 "${dataFolder.absolutePath}/"
        val eventChannel = GlobalEventChannel.parentScope(this)
        eventChannel.subscribeAlways<GroupMessageEvent> {
            if (message.contentToString().startsWith("```")) {
                group.sendMessage("代码运行功能开发中")
            }
        }
        eventChannel.subscribeAlways<FriendMessageEvent> {
            sender.sendMessage("暂不支持私聊运行")
        }
    }
}
