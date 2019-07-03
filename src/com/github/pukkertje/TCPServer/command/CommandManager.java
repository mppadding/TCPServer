package com.github.pukkertje.TCPServer.command;

import com.github.pukkertje.TCPServer.config.Language;

import java.util.concurrent.ConcurrentHashMap;

public class CommandManager {
    private ConcurrentHashMap<String, Command> _commands = new ConcurrentHashMap<String, Command>();

    private static CommandManager ourInstance = new CommandManager();

    public static CommandManager getInstance() {
        return ourInstance;
    }

    private CommandManager() {
    }

    public void addCommand(Command command) {
        _commands.putIfAbsent(command.getCommand(), command);
    }

    public String getHelp() {
        StringBuilder sb = new StringBuilder();
        sb.append("Help for Matthijs' Server:\nquit\t\tAborts the connection\nhelp\t\tRuns this command\n");

        for(Command cmd : _commands.values()) {
            sb.append(cmd.getHelp());
        }

        return sb.toString();
    }

    public String execute(String[] arguments) {
        if(arguments[0].equals("help"))
            return getHelp();

        Command comm = _commands.get(arguments[0].toLowerCase());
        if(comm == null) return Language.UNKNOWN_COMMAND.replace("{command}", arguments[0]);

        if(!comm.hasEnoughArguments(arguments.length-1)) {
            return ("Invalid number of args. Command needs " + comm.getArgumentNumber() + " arguments.\n");
        } else {
            return comm.execute(arguments);
        }
    }
}
