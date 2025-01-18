import java.io.*;
import java.net.*;

public class DateTimeClient {
    public static void main(String[] args) {
        // Ask the user for the server IP (use localhost for local machine, or IP for remote machine)
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the server IP address (localhost for local machine): ");
        try {
            String host = consoleReader.readLine().trim(); // User input for IP or hostname
            int port = 6001;  // Server port

            // Create socket connection to server
            try (Socket socket = new Socket(host, port);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                // Read and print the server's response (current date and time)
                String serverResponse = reader.readLine();
                System.out.println("Server response: " + serverResponse);

            } catch (IOException e) {
                System.out.println("Error: Could not connect to server. " + e.getMessage());
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Error reading input: " + e.getMessage());
        }
    }
}
