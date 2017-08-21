package client;

import java.io.IOException;

public interface Client {
    void connect() throws IOException;
    String send(String data) throws IOException;
    void disconnect() throws IOException;

}
