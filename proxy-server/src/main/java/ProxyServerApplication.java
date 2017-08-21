
import server.Server;

import java.io.IOException;
import java.util.Scanner;

public class ProxyServerApplication {
    public static void main(String[] args) {
        int port = 7080;
        System.out.println("Proxy server program lunched type \"start\" to handle requests");
        while (true){
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if(input.equals("start")){
                try {
                    Server server = new ProxyServer(port);
                    server.run();

                    System.out.println("Proxy Server is waiting for requests, type stop for exit");


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(input.equals("stop")){
                System.out.println("Stopping Proxy server");
                System.exit(1);
            }
            else {
                System.out.println("Type start or stop");
            }
        }
    }
}
