package echoServer.Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    static Socket socket;

    public Client() {
        try {
            socket = new Socket("127.0.0.1", 8080);

            Thread t1 = new Thread(() -> {
                try {
                    while (true) {
                        Scanner sc = new Scanner(socket.getInputStream());
                        String str = sc.nextLine();

                        if (str.equals("quit")) {
                            System.out.println("Server down...");
                            System.out.println("Connecting closed.");
                            break;
                        }
                        System.out.println("Server: " + str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Thread t2 = new Thread(() -> {
                try {
                    while (true) {
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        Scanner sc = new Scanner(System.in);
                        String str = sc.nextLine();

                        if (str.equals("quit")) {
                            out.println("quit");
                            System.out.println("disconnected");
                            break;
                        }

                        out.println(str);
                        System.out.println("Client: " + str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            t1.start();
            t2.start();

            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

