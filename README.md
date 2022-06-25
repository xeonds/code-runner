# Code-Runner

一个在QQ消息中运行代码的mirai插件，支持群聊和私聊。

特别感谢菜鸟教程提供的代码在线运行工具！

## 用法

在群、 bot 的私聊中发送下列格式的消息即可运行：


`````markdown
```c
#include <stdio.h>
int main(void){
    printf("Hello, world!\n);

    return 0;
}
`````

其中的`C`表示代码语言是C语言。目前可用的语言有`c`，`python`和`java`。使用相应语言只需将三个反引号后的c换成相应的记号。注意大小写和换行：第一行不能有额外内容。

如果有错误的话，bot会在消息中带上错误。

## TODO

- bot消息长度限制：防止刷屏
- stdin支持：支持运行前带上标准输入（~~这不就OJ吗~~）

有其他建议欢迎提issue。