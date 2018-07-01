package Messaging;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class GUI extends JFrame{
	JButton send = new JButton("send");
	JTextArea box = new JTextArea();
	JLabel chat = new JLabel();
	
	int port = 8081;
	Server server;
	Client client;

	public static void main(String[] args) {
		new GUI();
	}
	
	public GUI() {
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!", JOptionPane.YES_NO_OPTION);
		
		if(response == JOptionPane.YES_OPTION) {
			try {
				server = new Server(port);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setTitle("SERVER");
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			send.addActionListener((e)->{
				server.sendClick();
			});
			add(chat);
			add(box);
			add(send);
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			server.start();
		}
		else {
			setTitle("CLIENT");
			String ip = JOptionPane.showInputDialog("Enter the IP Address");
			int port = Integer.parseInt(JOptionPane.showInputDialog("Enter the port number"));
			client = new Client(ip, port);
			send.addActionListener((e)->{
				client.sendClick();
			});
			add(chat);
			add(box);
			add(send);
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			client.start();
			
		}
	}
}
