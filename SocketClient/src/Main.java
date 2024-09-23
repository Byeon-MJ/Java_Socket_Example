import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
//        String serverIp = "127.0.0.1";
        String serverIp = "192.168.0.161";
        int socketPort = 9071;

        // JSON 데이터 생성
        String cmd = "faceprintExFace";
        String tran_num = "0000IDV80101";
        String idImage = "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";
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