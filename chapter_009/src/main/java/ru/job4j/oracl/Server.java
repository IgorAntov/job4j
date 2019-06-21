package ru.job4j.oracl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.TreeMap;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Server {
    private final TreeMap<String, String> dbFrases = new TreeMap<>();
    private final Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
        this.dbFrases.put("Hello Oracle", "Hi\nbuddy\n");
        this.dbFrases.put("How are you", "Good\n");
    }

    public void init() throws IOException {
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String ask;
        do {
            System.out.println("waiting for a question");
            ask = in.readLine();
            System.out.println(ask);
            if (ask != null && dbFrases.containsKey(ask)) {
                out.println(dbFrases.get(ask));
            } else if (ask != null && !ask.equals("exit")) {
                out.println("I don't understand");
            }
        } while (ask != null && !"exit".equals(ask));
    }

    public static void main(String[] args) throws IOException {
        final Socket socket = new ServerSocket(1111).accept();
        new Server(socket).init();
    }
}