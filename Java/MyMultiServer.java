import java.io.*;
import java.net.*;

public class MyMultiServer {
    public static void main(String[] args) {
        try {
            // Bind the server to all network interfaces (0.0.0.0)
            ServerSocket ss = new ServerSocket(6000, 0, InetAddress.getByName("0.0.0.0"));
            System.out.println("Server started and listening on port 6000");

            // Accept and handle multiple clients
            while (true) {
                Socket s = ss.accept();
                System.out.println("Client connected: " + s.getInetAddress());

                // Create a new thread for each client connection
                new ClientHandler(s).start();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        DataInputStream dis = null;

        try {
            // Get input stream from the client
            dis = new DataInputStream(socket.getInputStream());

            // Read and display the message from the client
            String str = dis.readUTF();
            System.out.println("Client says: " + str);

        } catch (IOException e) {
            System.out.println("Error handling client " + socket.getInetAddress() + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (dis != null) {
                    dis.close();
                }
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
                System.out.println("Connection closed for client: " + socket.getInetAddress());
            } catch (IOException e) {
                System.out.println("Error closing resources: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
