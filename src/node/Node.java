package node;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;

public class Node {
	private static Integer serverPort;
	private static Scanner input;
	private static String nextNode;
	private static String nextNodePort;
	private static long myID;

	public static void main(String[] args) throws Exception {
		if (args.length==1) {
			serverPort = Integer.valueOf(args[0]);
		}
		input = new Scanner(System.in);
		new Thread(new ServerListener()).start();
		init();
//		while (true){
//		System.out.println("Write where you want to connect: ");
//		System.out.println("Connect IP: ");
//		String ip = input.nextLine();
//		System.out.println("Connect Port: ");
//		String port = input.nextLine();
		System.out.println("Press enter to initiate election");
		input.nextLine();
		connect("Election:"+myID,nextNode,nextNodePort);
//		}
	}
	
	private static void init() throws Exception{
//		myID = new Random().nextInt(1000);
		myID = benchMark();
		System.out.println("Chosen ID is "+myID);
		System.out.println("Next Node IP: ");
		nextNode = input.nextLine();
		System.out.println("Next Node Port: ");
		nextNodePort = input.nextLine();
	}
	
	/**
	 * Connect to given parameters
	 * @param Ip Connect to this IP
	 * @param Port Connect to this port, in the above IP
	 */
	public static void connect(String msg,String Ip,String Port){
		Socket s = null;
		try{
			s = new Socket(Ip, Integer.valueOf(Port));    
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintStream out = new PrintStream( s.getOutputStream());
//			out.println("Connected");
//			System.out.println("Connected to remote host");
			out.println( msg );
		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
     }
	
	public static void sendMsgToNextNode(String msg){
		connect( msg, nextNode, nextNodePort );
	}

	public static long getMyID() {
		return myID;
	}
	
	private static long benchMark() {
		long total = 0;
		for (int j=0;j<100;++j){
			long startTime = System.nanoTime();
			int count = 0;
			for (int i=0;i<100000;++i){
				count++;
			}
			long endTime = System.nanoTime();
			total += (endTime - startTime);
		}
		long score = total / 10;
		return score;
	}

}
