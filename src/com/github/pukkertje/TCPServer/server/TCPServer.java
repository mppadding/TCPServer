package com.github.pukkertje.TCPServer.server;

import com.github.pukkertje.TCPServer.command.CommandManager;
import com.github.pukkertje.TCPServer.config.Language;

import java.io.*;
import java.net.Socket;

public class TCPServer implements Runnable {

    private Socket _client;
    private InputStream _in = null;
    private OutputStream _out = null;

    private boolean _running;

    public TCPServer(Socket client) {
        _client = client;
        _running = true;
    }

    private void display(String message) {
        try {
            _out.write(message.getBytes());
        } catch(IOException e) {
            System.out.println("Error writing  message to OutputStream");
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            _in = _client.getInputStream();
            _out = _client.getOutputStream();

            display(Language.WELCOME);
            display("> ");

            BufferedReader input = new BufferedReader(new InputStreamReader(_client.getInputStream()));
            String msg;
            while (_running) {
                if(input.ready() && (msg = input.readLine()) != null) {
                    msg = msg.trim().toLowerCase();
                    String[] args = msg.split(" ");

                    if(args[0].startsWith("quit") || args[0].startsWith("exit")) {
                        display(Language.GOODBYE);
                        break;
                    }

                    String out = CommandManager.getInstance().execute(args);
                    if(out != null)
                        display(out);

                    display("> ");
                }
            }

        } catch (IOException e) {
            if(_running) {
                e.printStackTrace();
            }
        } finally {
            if(_in != null) {
                try {
                    _in.close();
                } catch(IOException e) {
                    System.out.println("Error while closing InputStream");
                    e.printStackTrace();
                }
            }
            if(_out != null) {
                try {
                    _out.close();
                } catch(IOException e) {
                    System.out.println("Error while closing OutputStream");
                    e.printStackTrace();
                }
            }
            try {
                _client.close();
            } catch(IOException e) {
                System.out.println("Error closing Socket");
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        display("Server shutting down. Goodbye.\n");
        _running = false;
    }
}
