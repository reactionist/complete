package clientserver.protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class StoreServerTCP implements Runnable {
    private ServerSocket serverSocket;


    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true) {
            new StoreServerTCPHandler(serverSocket.accept()).start();
        }
    }

    @Override
    public void run() {
        StoreServerTCP server = new StoreServerTCP();
        try {
            server.start(6666);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}