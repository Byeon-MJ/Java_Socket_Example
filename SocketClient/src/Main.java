import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        String serverIp = "127.0.0.1";
        int socketPort = 9071;

        // JSON 데이터 생성
        String cmd = "SocketTest";
        String tran_num = "000011112222";
        String idImage = "Test Image";
        int idType = 1;
        int featureType = 0;
        String jsonString =  "{"
                + "\"cmd\":\"" + cmd + "\","
                + "\"tran_num\":\"" + tran_num + "\","
                + "\"idImage\":\"" + idImage + "\","
                + "\"idType\":\"" + idType + "\","
                + "\"featureType\":\"" + featureType + "\""
                + "}";

        // TODO : Multi-Threading 적용하여 여러 클라이언트가 동시에 서버에 접속할 수 있도록 구현
        int threadCnt = 20;
        for (int i = 1; i <= threadCnt; i++) {
            System.out.println("Thread " + i + " is running");
            int finalI = i;
            new Thread(() -> {
                try (Socket socket = new Socket(serverIp, socketPort);
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
                ){ // 소켓을 생성하여 연결을 요청
                    System.out.println("Connecting to server " + serverIp);

                    // 서버로 메시지 보내기
                    out.println(jsonString);

                    // 소켓으로부터 받은 메시지를 출력한다.
                    System.out.println("Message from server: " + in.readLine());
                    System.out.println("Thread " + finalI + " is finished, closing socket");
                } catch (ConnectException ce) {
                    ce.printStackTrace();
                } catch (IOException ie) {
                    ie.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }
}