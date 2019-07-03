package com.github.pukkertje.TCPServer.command;

public class Ping extends Command{

    public Ping() {

    }

    @Override
    public String execute(String[] arguments) {
        return "Pong!\n";
    }

    @Override
    public String getHelp() {
        return "ping\t\tReturns pong!\n";
    }

    @Override
    public String getCommand() {
        return "ping";
    }

    @Override
    public int getArgumentNumber() {
        return 0;
    }

    @Override
    public boolean hasEnoughArguments(int arguments) {
        return arguments == 0;
    }
}
