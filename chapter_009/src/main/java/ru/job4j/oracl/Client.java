package ru.job4j.oracl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Client {

    private final Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    private void start() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner console = new Scanner(System.in);
            out.println("Hello Oracle");
            String line;
            do {
                String str = in.readLine();
                while (!str.isEmpty()) {
                    System.out.println(str);
                }
                line = console.nextLine();
                out.println(line);
            } while (!line.equals("exit"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Client(new Socket(InetAddress.getByName("127.0.0.1"), 1111)).start();
    }
}