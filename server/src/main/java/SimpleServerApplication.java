import server.Server;
import server.SimpleServer;

import java.io.IOException;
import java.util.Scanner;

public class SimpleServerApplication {
    public static void main(String[] args) {
//        int port = Integer.parseInt(args[0]);
        int port = 7070;
        try {
            SimpleServer server = new SimpleServer(port);
            server.start();
            System.out.println("Server is running, type stop for exit");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("stop")) {
                System.exit(1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
