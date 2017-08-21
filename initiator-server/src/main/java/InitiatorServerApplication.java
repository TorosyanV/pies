import client.Client;
import client.SimpleClient;
import server.Server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InitiatorServerApplication {
    public static void main(String[] args) {
        int port = 7090;
        int proxyServerPort = 7080;
        String proxyAddress = "localhost";

        writeNumbersToFile();
        List<Integer> numbers = readNumbersFromFile();

        Client client = new SimpleClient(proxyAddress, proxyServerPort);
        try {

            for (Integer number : numbers) {

                Thread.sleep(1000);
                client.connect();
                String responseData = client.send(number.toString());
                client.disconnect();
            }




        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Initiator server program lunched type \"start\" to handle requests");
        while (true){
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if(input.equals("start")){
                try {
                    Server server = new InitiatorServer(port,proxyAddress,proxyServerPort);
                    server.run();
                    System.out.println("Initiator Server is waiting for requests, type stop for exit");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(input.equals("stop")){
                System.out.println("Stopping Initiator server");
                System.exit(1);
            }
            else {
                System.out.println("Type start or stop");
            }


        }
    }

    public static void writeNumbersToFile(){
        System.out.println("Writing numbers to file");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("initiator_send.txt", "UTF-8");

            for (int i=0; i<=100; i++){
                writer.println(i);
            }
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public static List<Integer> readNumbersFromFile() {
        List<Integer> numbers=new ArrayList<>();

        try {
            try (BufferedReader br = new BufferedReader(new FileReader("initiator_send.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);


                    numbers.add(Integer.valueOf(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return numbers;
    }


}
