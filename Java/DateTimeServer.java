import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeServer {
    public static void main(String[] args) {
        int port = 6001;
        try {
            // Bind to all network interfaces (0.0.0.0)
            ServerSocket serverSocket = new ServerSocket(port, 0, InetAddress.getByName("0.0.0.0"));
            System.out.println("Server is listening on port " + port);

            // Accept the first client connection
            Socket socket = serverSocket.accept();
            System.out.println("New client connected");

            // Handle the client
            handleClient(socket);

            // Stop the server after handling the first client
            System.out.println("Server will now stop.");
            serverSocket.close(); // Explicitly close the server socket

        } catch (IOException e) {
            System.out.println("Error in server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket socket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            // Get the current date and time
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            writer.println("Current Date and Time: " + now.format(formatter));

        } catch (IOException e) {
            System.out.println("Error handling client: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                System.out.println("Client connection closed.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
