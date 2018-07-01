package Messaging;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Server extends Thread {

	int port;
	ServerSocket serverSocket;
	
	public Server(int port) throws IOException {
		this.port = port;
		serverSocket = new ServerSocket(port);
	}
	
	public void run() {
		boolean run = true;
		while(run) {
			try {
				JOptionPane.showMessageDialog(null, "Connecting...");
				Socket socket = serverSocket.accept();
				JOptionPane.showMessageDialog(null, "Connected");
				
				DataInputStream input = new DataInputStream(socket.getInputStream());
				System.out.println(input.readUTF());
				DataOutputStream output = new DataOutputStream(socket.getOutputStream());
				output.writeUTF("hello");
				
			} catch (SocketTimeoutException e) {
				System.out.println("SocketTimeoutExcpetion");
				run = false;
			} catch (IOException e) {
				System.out.println("IOException");
				run = false;
			}
		}
	}
	
	public String getIPAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "ERROR!!!!!";
		}
	}
	
	public int getPort() {
		return port;
	}
}
