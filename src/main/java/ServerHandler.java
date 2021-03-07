import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerHandler {

    private InputStream inputStream;
    private OutputStream outputStream;
    private Socket client;
    private ObjectMapper objectMapper = new ObjectMapper();

    public ServerHandler(Socket socket) {

        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            client = socket;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readTransferObject() {
        byte[] dataFromClient = new byte[1024];
        try {
            int bytesRead = inputStream.read(dataFromClient, 0, dataFromClient.length);
            System.out.println("Bytes sent: " + bytesRead);

            String messageFromClient = new String(dataFromClient);
            System.out.println("Reading string with bytes sent from client: " + messageFromClient);

            // from JSON to custom object
            Customer customer = objectMapper.readValue(messageFromClient, Customer.class);
            System.out.println("Reading value from the Customer object: " + customer.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToClient() {
        Customer customer = new Customer();
        customer.set(10, "John");

        byte[] toClient = new byte[1024];
        try {
            toClient = objectMapper.writeValueAsBytes(customer);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        int numberOfBytes = toClient.length;
        try {
            outputStream.write(toClient, 0, numberOfBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
