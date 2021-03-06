import java.net.Socket;
import java.util.ArrayList;

public class StartServer {
    public static void main(String[] args) {

        ServerTransfer serverTransfer = new ServerTransfer();
        serverTransfer.start();

        Customer customer = new Customer();
        customer.setName("John");
        customer.setId(10);
    }
}
