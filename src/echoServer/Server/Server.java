package echoServer.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    static ServerSocket server;
    static Socket socket;

    public Server() {
        try {
            server = new ServerSocket(8080);
            System.out.println("Server is ready...");
            System.out.println("Waiting for a connection...");
            socket = server.accept();
            System.out.println("Connection established: " + socket);

            Thread t1 = new Thread(() -> {
                try {
                    while (true) {
                        Scanner sc = new Scanner(socket.getInputStream());
                        String str = sc.nextLine();

                        if (str.equals("quit")) {
                            System.out.println("Client disconnected");
                            break;
                        }


                        System.out.println("Client: " + str);
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
                        System.out.println("Server: " + str);
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

            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

