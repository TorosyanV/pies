import client.Client;
import client.SimpleClient;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SimpleClientApplication {
    public static void main(String[] args) throws InterruptedException {

        String serverName = "localhost";
        int port = 7070;
        Client client = new SimpleClient(serverName, port);
        try {

            List<Integer> numbers = getDataToSend();
            for (Integer number : numbers) {
                Thread.sleep(1000);
                client.connect();
                String responseData = client.send(number.toString());
                client.disconnect();
                System.out.println("Response from Server -> " + responseData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<Integer> getDataToSend() {
        List<Integer> numbers = new ArrayList<Integer>();


        for (int i = 0; i < 500; i++) {
            numbers.add(i);
        }
        return numbers;
    }
}
