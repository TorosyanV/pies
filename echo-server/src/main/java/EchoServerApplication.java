import server.Server;
import server.SimpleServer;

import java.io.IOException;
import java.util.Scanner;

public class EchoServerApplication {
    public static void main(String[] args) {
        int port = 7090;
        System.out.println("ECHO server program lunched type \"start\" to handle requests");
        while (true){
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if(input.equals("start")){
                try {
                    Server server = new EchoServer(port);
                    server.run();

                    System.out.println("ECHO Server is waiting for requests, type stop for exit");


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(input.equals("stop")){
                System.out.println("Stopping ECHO server");
                System.exit(1);
            }
            else {
                System.out.println("Type start or stop");
            }


        }



//        int port = Integer.parseInt(args[0]);

    }
}
