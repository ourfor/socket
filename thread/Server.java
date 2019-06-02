import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Date;


public class Server{
	private static ServerSocket server = null;
	public static void main(String args[]){
		try{
			int port = 4578;
			server = new ServerSocket(4578);
			while(true){
				Socket client = server.accept();
				Thread thread = new ServerThread(client);
				thread.start();
			}
		} catch(Exception e){
			System.out.println(e.toString());
		}
	}
}

class ServerThread extends Thread{
	private Socket client = null;

	ServerThread(Socket client){
		this.client = client;
	}

	public void run(){

	    DataInputStream in = null;
		DataOutputStream out = null;

		try{
			in = new DataInputStream(client.getInputStream());
			out = new DataOutputStream(client.getOutputStream());
			out.writeUTF("Nice to meet you! "+new Date());
			String message = in.readUTF();
			System.out.println("接到信息: " + message);

		} catch(Exception e){
			System.out.println(e.toString());
		}

	}
}

