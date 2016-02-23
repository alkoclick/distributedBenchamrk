package node;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import electionAlgorithms.BaseAlgorithm;
import electionAlgorithms.LCR;

public class ConnectionListener implements Runnable{

	private BufferedReader in;
	private PrintStream out;

	public ConnectionListener(Socket clientSocket) throws IOException {
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		out = new PrintStream(clientSocket.getOutputStream());
		run();
	}

	@Override
	public void run() {
		System.out.println("Node Listener started");
		BaseAlgorithm algorithm = new LCR();
		try {
			algorithm.onMessage(in.readLine());
//			String msg = in.readLine();
//			while (msg != null) {
//				System.out.println(msg);
//				msg = in.readLine();
//			}
//			Node.sendMsgToNextNode(in.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
