package com.hy.command;

/**
 * Created by huangyong on 2017/5/27.
 * 具体命令实现对象
 */
public class ConcreteCommand implements Command {
    private Receiver receiver;
    private String state;//命令对象的状态

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action();
    }
}
