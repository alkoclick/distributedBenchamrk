package node;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener implements Runnable{

		private int serverPort;

		@Override
		public void run() {
			try{
				serverPort  = 7777; // the server port
				ServerSocket listenSocket = new ServerSocket(serverPort);
				while (true) {
					System.out.println("Server started @ port "+serverPort+" ...");
					Socket clientSocket = listenSocket.accept();
					System.out.println("Request from client " + clientSocket.getInetAddress()+" at port "+ clientSocket.getPort());				
					ConnectionListener nListener = new ConnectionListener(clientSocket);
				}
			} catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
		}
		
	}