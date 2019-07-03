package com.github.pukkertje.TCPServer.command;

public class Joke extends Command {

    private final String[] _jokes = {
            "Imagine C++ but in French\n\tOui++\n",
            "Why are programmers so selfish?\n\tBecause they always choose i for the iterator!\n",
            "Why don't Pokemon trainers use JavaScript?\n\tBecause it's weakly typed.\n",
            "What's a Python Programmer's favourite Pokemon?\n\tA piplup.\n",
            "I hacked my friend's computer.\n\tThat thing didn't stand a chance against my axe.\n",
            "What part of corn controls all the other parts?\n\tThe kernel.\n",
    };

    public Joke() {

    }

    @Override
    public String execute(String[] arguments) {
        return _jokes[(int)(Math.random() * _jokes.length)];
    }

    @Override
    public String getHelp() {
        return "joke\t\tReturns a random joke.\n";
    }

    @Override
    public String getCommand() {
        return "joke";
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
