import java.net.Socket;
import java.net.ServerSocket;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;


class Server{
	private ServerSocket serverSocket;

	public Server(int port){
			try{
					serverSocket = new ServerSocket(port);
					serverSocket.setSoTimeout(10000);
			} catch(Exception e){
					System.out.println(e.toString());
			}
	}

    public static void main(String args[]){
			Server server = new Server(4578);
			while(true){

					try{
							Socket clientSocket = server.serverSocket.accept();
							DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
							DataInputStream in = new DataInputStream(clientSocket.getInputStream());

							out.writeUTF("Nice to meet you!");
							String message = in.readUTF();
							System.out.println("客户端发来的消息为: "+message);
					} catch(Exception e){
							System.out.println(e.toString());
					}
			}
	}
}
