package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer extends Thread implements Server {

    private ServerSocket serverSocket;
    private boolean waitForConnection = true;

    public SimpleServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(0);//infinite timeout
    }

    private void acceptRequest() throws IOException, InterruptedException {
        System.out.println("Waiting for client on port " +
                serverSocket.getLocalPort() + "...");
        Socket server = serverSocket.accept();

        System.out.println("Just connected to " + server.getRemoteSocketAddress());


        String response = generateResponse(getRequestData(server));
        DataOutputStream out = new DataOutputStream(server.getOutputStream());
        out.writeUTF(response);
        server.close();

    }

    private String getRequestData(Socket server) throws IOException {
        DataInputStream in = new DataInputStream(server.getInputStream());

        String receivedData = in.readUTF();
        return receivedData;
    }

    public String generateResponse(String request) throws InterruptedException {
        System.out.println("REQUEST->"+request);
        return "No response, default";
    }
@Override
    public void run() {

        while (waitForConnection) {
            try {
                acceptRequest();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
