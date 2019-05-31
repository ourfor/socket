import java.net.Socket;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.DataInputStream;


class client{
		public static void main(String args[]){
			String serverName = args[0];
			int port = Integer.parseInt(args[1]);
			try{
					Socket client = new Socket(serverName,port);

					DataOutputStream out = new DataOutputStream(client.getOutputStream());
					DataInputStream in = new DataInputStream(client.getInputStream());
					
					out.writeUTF(args[2]);
					String message = in.readUTF();
					System.out.println("来自服务端的消息为: "+message);
			} catch(Exception e){
					System.out.println(e.toString());
			}

			
		}
}
