import java.io.IOException;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;


public class Client{
    public static void main(String args[]){
        String host = "127.0.0.1";
        int port = 4578;

        try{
            Socket client = new Socket(host,port);

            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());

            String receive = in.readUTF();
            System.out.println("收到: " + receive);
            String send = "";

            System.out.print(">");
            Scanner input = new Scanner(System.in);
            send = input.next();

            out.writeUTF(send);



        } catch(Exception e){
            System.out.println(e.toString());
        }
    }

}