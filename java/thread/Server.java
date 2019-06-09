import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;


public class Server{
    private static ServerSocket server;
    private static void init(){
        try{
            int port = 4578;
            server = new ServerSocket(port);
        } catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public static void main(String args[]){
        init();

        while(true){
            try{
                Socket client = server.accept();
                ServerThread thread = new ServerThread(client);
                thread.start();
            } catch(Exception e){
                System.out.println(e.toString());
            }
        }
    }
    

}


class ServerThread extends Thread {
    private Socket client;
    ServerThread(Socket client){
        this.client = client;
    }

    public void run(){
        try{
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());

            out.writeUTF("Nice to meet you!");
            String message = in.readUTF();

            System.out.println("客户端发来的消息为: " + message);
        } catch(Exception e){
            System.out.println(e.toString());
        }
    }
}