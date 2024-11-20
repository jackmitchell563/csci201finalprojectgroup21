import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server is listening on port 8080");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            new Thread(new HttpRequestHandler(clientSocket)).start();
        }
    }
}
