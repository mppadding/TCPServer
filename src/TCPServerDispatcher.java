import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServerDispatcher implements Runnable {

    private ServerSocket socket;
    private ArrayList<TCPServer> _servers = new ArrayList<TCPServer>();
    private int _port = 9000;

    public TCPServerDispatcher() throws IOException {
        socket = new ServerSocket(_port);
    }

    public void run() {
        System.out.println("Accepting connections on port " + _port);
        try {
            while (true) {
                Socket client = socket.accept();
                System.out.println("Client connected from " + client.getInetAddress().getHostAddress());

                TCPServer tcp = new TCPServer(client);

                _servers.add(tcp);

                new Thread(tcp).start();
            }
        } catch(IOException e) {
            /* Implemented due to closing of the server, not really clean but since accept does not respond to interrupt
             * there is not really another way (variable to hold state wouldn't work either since accept is blocking) */
            if(!e.getMessage().contains("accept failed")) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        for(TCPServer server : _servers) {
            server.shutdown();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
