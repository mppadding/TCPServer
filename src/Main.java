import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            TCPServerDispatcher dispatcher = new TCPServerDispatcher();
            Thread dispatchThread = new Thread(dispatcher);
            dispatchThread.start();

            System.out.println("Type q to quit");

            while(true) {
                if(scanner.hasNextLine()) {
                    String input = scanner.nextLine();
                    if(input.toLowerCase().startsWith("q")) {
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