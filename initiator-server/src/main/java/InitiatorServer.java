import client.Client;
import client.SimpleClient;
import server.SimpleServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InitiatorServer extends SimpleServer {

    private String proxyAddress;
    private int proxyServerPort;


    public InitiatorServer(int port, String proxyAddress, int proxyServerPort) throws IOException {
        super(port);
        this.proxyAddress = proxyAddress;
        this.proxyServerPort = proxyServerPort;
    }

    @Override
    public String generateResponse(String request) throws InterruptedException {
        System.out.println("Generating response message ECHO server");
        Client client = new SimpleClient(proxyAddress, proxyServerPort);
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
        return super.generateResponse(request);
    }
    public static List<Integer> getDataToSend() {
        List<Integer> numbers = new ArrayList<Integer>();


        for (int i = 0; i < 500; i++) {
            numbers.add(i);
        }
        return numbers;
    }
}
