package server;

import java.io.IOException;

public interface Server {

    String generateResponse(String request) throws InterruptedException;
    void run();

}
