import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientHandler extends Thread {
    private Socket socket;
    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true)
        ) {
            // 클라이언트로부터 받은 메시지를 처리하는 로직 작성
            System.out.println("Message from client: " + in.readLine());

            // 클라이언트로 메시지를 전송
            out.println("[Notice] Test Message from Server " + socket.getInetAddress() + ":" + socket.getPort());
            System.out.println("Message sent to client: " + socket.getInetAddress() + ":" + socket.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                System.out.println("Socket is closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

