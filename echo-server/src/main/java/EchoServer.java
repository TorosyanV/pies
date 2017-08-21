import server.SimpleServer;

import java.io.*;

public class EchoServer extends SimpleServer {
    public EchoServer(int port) throws IOException {
        super(port);
    }

    @Override
    public String generateResponse(String request) throws InterruptedException {
        System.out.println("Generating response message ECHO server");
        writeNumbersToFile(request);
        return super.generateResponse(request);
    }

    public void writeNumbersToFile(String number) {
        System.out.println("Writing numbers to file");

        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream(
                    new File("echo_send.txt"),
                    true /* append = true */));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        pw.println(number);
        pw.close();


    }
}
