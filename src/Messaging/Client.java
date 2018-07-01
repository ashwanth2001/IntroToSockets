package Messaging;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	String ip;
	int port;
	Socket socket;
	ObjectOutputStream output;
	ObjectInputStream input;
	
	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public void start(){
		try {
			socket = new Socket(ip, port);
			
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());
			output.flush();
		
			System.out.println("connected");
			while(socket.isConnected()) {
			
			}
			System.out.println("disconnected");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
