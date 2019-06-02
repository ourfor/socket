import java.net.Socket;
import java.net.ServerSocket;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
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
							BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
							BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                            out.writeLine("Have a good night");
							String message = in.readLine();
							System.out.println("客户端发来的消息为: "+message);
					} catch(Exception e){
							System.out.println(e.toString());
					}
			}
	}
}
