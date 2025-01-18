import java.io.*;
import java.net.*;

public class MyServer {
    public static void main(String[] args) {
        try {
            // Bind the server to all network interfaces (0.0.0.0)
            ServerSocket ss = new ServerSocket(6000, 0, InetAddress.getByName("0.0.0.0"));
            System.out.println("Server started and listening on port 6000");

            // Wait for a client to connect
            Socket s = ss.accept();
            System.out.println("Client connected: " + s.getInetAddress());

            // Get input stream from the client
            DataInputStream dis = new DataInputStream(s.getInputStream());

            // Read and display the message from the client
            String str = dis.readUTF();
            System.out.println("Client says: " + str);

            // Close resources
            dis.close();
            s.close();
            ss.close();

            System.out.println("Connection closed");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
