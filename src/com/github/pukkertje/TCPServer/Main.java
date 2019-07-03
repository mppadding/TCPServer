package com.github.pukkertje.TCPServer;

import com.github.pukkertje.TCPServer.command.*;
import com.github.pukkertje.TCPServer.server.TCPServerDispatcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static void addCommands(CommandManager manager) {
        manager.addCommand(new Joke());
        manager.addCommand(new News());
        manager.addCommand(new Ping());
    }

    public static void main(String[] args) {
        CommandManager manager = CommandManager.getInstance();
        addCommands(manager);

        try {
            TCPServerDispatcher dispatcher = new TCPServerDispatcher();
            Thread dispatchThread = new Thread(dispatcher);
            dispatchThread.start();

            InputStreamReader inputStream = new InputStreamReader(System.in);
            BufferedReader input = new BufferedReader(inputStream);

            System.out.println("Type q to quit");

            while(true) {
                if(!dispatchThread.isAlive()) {
                    dispatchThread = new Thread(dispatcher);
                    dispatchThread.start();
                }

                if(System.in.available() > 0) {
                    if(input.readLine().toLowerCase().startsWith("q")) {
                        dispatcher.shutdown();
                        break;
                    }
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        System.out.println("Goodbye");
    }
}