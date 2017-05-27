package com.hy.command;

/**
 * Created by huangyong on 2017/5/27.
 * 命令模式调用者（调用命令，命令模式的入口）
 */
public class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void runCommand() {
        command.execute();
    }
}
