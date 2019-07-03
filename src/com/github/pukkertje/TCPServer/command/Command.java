package com.github.pukkertje.TCPServer.command;

public abstract class Command {

    public abstract String execute(String[] arguments);

    public abstract String getHelp();
    public abstract String getCommand();
    public abstract int getArgumentNumber();
    public abstract boolean hasEnoughArguments(int arguments);
}
