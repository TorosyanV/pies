package client;

import java.io.*;
import java.net.Socket;

public class SimpleClient implements Client {
    String serverName = "localhost";
    private int port = 7070;
    Socket client;

    public SimpleClient(String serverName, int port) {
        this.serverName = serverName;
        this.port = port;
    }

    public void connect() throws IOException {

        System.out.println("Connecting to " + serverName + " on port " + port);
        client = new Socket(serverName, port);
        System.out.println("Connection Success  " + client.getRemoteSocketAddress());

    }

    public String send(String data) throws IOException {
        OutputStream outToServer = client.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);
        out.writeUTF(data);

        InputStream inFromServer = client.getInputStream();
        DataInputStream in = new DataInputStream(inFromServer);

        String responseData = in.readUTF();
        System.out.println("Response from server " + responseData);
        client.close();

        return responseData;
    }

    public boolean isConnected() {
        return client.isConnected();
    }

    public void disconnect() throws IOException {
        client.close();
    }
}
