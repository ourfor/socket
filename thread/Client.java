import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;


public class Client{
	public static void main(String args[]){
		String host = "127.0.0.1";
		int port = 4578;
		try{
			Socket client = new Socket(host,port);
			DataInputStream in = new DataInputStream(client.getInputStream());
			DataOutputStream out = new DataOutputStream(client.getOutputStream());
			String message = in.readUTF();
			System.out.println("收到: " + message);
			out.writeUTF("你想怎么样?");
		} catch(Exception e){
			System.out.println(e.toString());
		}
	}
}
