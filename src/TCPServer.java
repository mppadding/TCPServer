import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServer implements Runnable {

    private final String _url = "https://news.google.com/rss/headlines/section/geo/";

    private enum COMMAND {
        HELP,
        NEWS,
        QUIT,
        PING,
        UNKNOWN
    }

    private Socket _client;
    private InputStream _in = null;
    private OutputStream _out = null;

    private boolean _running;

    public TCPServer(Socket client) {
        _client = client;
        _running = true;
    }

    private COMMAND parse(String input) {
        input = input.toLowerCase();

        if(input.equals("quit") || input.equals("exit")) {
            return COMMAND.QUIT;
        } else if(input.startsWith("news")) {
            return COMMAND.NEWS;
        } else if(input.equals("ping")) {
            return COMMAND.PING;
        } else if(input.equals("help")) {
            return COMMAND.HELP;
        }

        return COMMAND.UNKNOWN;
    }

    private void display(String message) {
        try {
            _out.write(message.getBytes());
        } catch(IOException e) {
            System.out.println("Error writing  message to OutputStream");
            e.printStackTrace();
        }
    }

    private void news(String location) {
        ArrayList<NewsItem> items;

        if(Cache.inCache(location)) {
            items = Cache.fetch(location);
        } else {
            InputStream is = HTTPSClient.fetchURL(_url + location);
            items = RSSParser.parse(is);
            Cache.addCache(location, items);
        }

        if(items == null || items.size() == 0) {
            display("Unknown location or no news found.\n");
            return;
        }

        for(NewsItem item : items) {
            display(Language.NEWS_ITEM
                    .replace("{title}", item.getTitle())
                    .replace("{url}", item.getUrl())
                    .replace("{date}", item.getDate()));
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
                    msg = msg.trim();

                    COMMAND command = parse(msg);
                    String[] args = msg.split(" ");

                    if (command == COMMAND.HELP) {
                        display(Language.HELP_MESSAGE);
                    } else if (command == COMMAND.NEWS) {
                        if (args.length > 1) {
                            String location = msg.substring(5);
                            display(Language.NEWS_FOR.replace("{location}", location));
                            news(location.replace(" ", ""));
                        } else {
                            display(Language.ERROR_ARGS
                                    .replace("{command}", "news")
                                    .replace("{number}", "minimal 1"));
                        }
                    } else if (command == COMMAND.PING) {
                        display("Pong!\n");
                    } else if (command == COMMAND.QUIT) {
                        display(Language.GOODBYE);
                        break;
                    } else if (command == COMMAND.UNKNOWN) {
                        display(Language.UNKNOWN_COMMAND.replace("{command}", args[0]));
                    }

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
