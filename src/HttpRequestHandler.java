import java.io.*;
import java.net.Socket;

public class HttpRequestHandler implements Runnable {
    private Socket clientSocket;

    public HttpRequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String headerLine = in.readLine();  // <HTTP Method> <URI> <HTTP Version>
            if (headerLine == null) return;

            String[] requestTokens = headerLine.split(" ");
            String httpMethod = requestTokens[0];
            String uri = requestTokens[1];

            try {
                if (uri.startsWith("/api/users")) {
                    UserController.processRequest(httpMethod, uri, in, out);
                } else if (uri.startsWith("/api/exercises")) {
                    ExerciseController.processRequest(httpMethod, uri, in, out);
                } else {
                    sendErrorResponse(out, 404, "Resource not found");
                }
            } catch (Exception e) {
                sendErrorResponse(out, 500, "Internal Server Error");
                e.printStackTrace();
            }

            out.close();
            in.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendErrorResponse(PrintWriter out, int statusCode, String message) {
        out.println("HTTP/1.1 " + statusCode + " " + message);
        out.println("Content-Type: text/plain");
        out.println();
        out.println(message);
    }
}
