import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class Client_update {
	public static void main(String args[]){
		String serverHost = "127.0.0.1";
		int port = 4578;
		try{
			
			Socket client = new Socket(serverHost,port);
			DataInputStream in = new DataInputStream(client.getInputStream());
			DataOutputStream out = new DataOutputStream(client.getOutputStream());
			String message = in.readUTF();
			out.writeUTF("你是个傻逼");
			System.out.println("服务端发送过来的消息为: " + message);
		} catch(Exception e){
			System.out.println(e.toString());
		}
	}
}
