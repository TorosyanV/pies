
import client.Client;
import client.SimpleClient;
import server.SimpleServer;

import java.io.IOException;

public class ProxyServer extends SimpleServer {

    String echoServer = "localhost";
    int echoServerPort = 7090;

    public ProxyServer(int port) throws IOException {
        super(port);
    }

    @Override
    public String generateResponse(String request) throws InterruptedException {
        String response = "";
        System.out.println("Generating response message Proxy server");
        Client client = new SimpleClient(echoServer, echoServerPort);
        try {
            client.connect();
            response = client.send(request);
            client.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
