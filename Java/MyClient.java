import java.io.*;
import java.net.*;
import java.util.Scanner;  // Import Scanner for dynamic input

public class MyClient {  
    public static void main(String[] args) {  
        Socket s = null;  
        DataOutputStream dout = null;  
        Scanner sc = null;  // Declare the Scanner object outside the try block

        try {  
            // Create a Scanner object for taking input from the user
            sc = new Scanner(System.in);  
            
            // Ask for the server IP address
            System.out.print("Enter server IP address: ");
            String serverIP = sc.nextLine();  // Read the server IP address from the user

            // Ask for the message to be sent
            System.out.println("Enter message to send to server:");
            String message = sc.nextLine();  // Read the message from the user

            // Connect to the server using the input IP address
            s = new Socket(serverIP, 6000);  // Connect to the server using its IP address
            dout = new DataOutputStream(s.getOutputStream());  

            // Send the message to the server
            dout.writeUTF(message);  
            dout.flush();  // Ensure the message is sent

            System.out.println("Message sent to server: " + message);  // Confirmation message

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                // Ensure resources are closed
                if (dout != null) dout.close();  
                if (s != null) s.close();     
                if (sc != null) sc.close();    // Close the Scanner object
                System.out.println("Connection closing...");
            } catch (IOException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }  
}
