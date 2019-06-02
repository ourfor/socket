import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class Server_update{
	public static void main(String args[]){
		try{
				ServerSocket server = new ServerSocket(4578);
				Socket client = server.accept();
				DataInputStream in = new DataInputStream(client.getInputStream());
				DataOutputStream out = new DataOutputStream(client.getOutputStream());
				out.writeUTF("Have a good time!");
				String message = in.readUTF();
				System.out.println("来自客户端的消息为: "+message);
		} catch(Exception e){
				System.out.println(e.toString());
		}
	}
}
