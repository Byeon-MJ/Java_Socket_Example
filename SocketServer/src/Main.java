import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        int socketPort = 9071;
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(socketPort);
            System.out.println("Server socket is listening on port " + socketPort);

            while (true) {
                    System.out.println("Waiting for client request");
                    Socket socket = serverSocket.accept();
                    System.out.println("New client connected " + socket.getInetAddress() + ":" + socket.getPort() + " connected");

                    // ClientHander 실행
                    new ClientHandler(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String getTime() {
        SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
        return f.format(System.currentTimeMillis());
    }
}