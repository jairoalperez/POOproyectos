package Programa_Zoo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Server_View implements Runnable{

	private JPanel panel;
	private JLabel label;
	private JTextField tfmensaje;
	private JTextArea chat;
	private JButton enviar;
	private JButton atras;
	
	//private Socket socket = null;
	private ServerSocket servidor = null;
	private DataInputStream in = null;
	private Socket socket = null;
	
	public Server_View() {
		
		this.panel = new JPanel();
		
		this.label = new JLabel("Servidor");
		this.label.setBounds(200, 10, 100, 50);
		this.panel.add(label);
		
		this.atras = new JButton ("Atras");
		this.atras.setBounds(1, 1, 75, 50);
		this.atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent d) {
				Base_View base = new Base_View();
	            IG.vista.setContentPane(base.getbase());
	            IG.vista.invalidate();
	        	IG.vista.validate();
	        }
	    });
		this.panel.add(atras);
		
		this.tfmensaje = new JTextField();
		this.tfmensaje.setBounds(50, 75, 275, 30);
		this.panel.add(tfmensaje);
		
		this.enviar = new JButton("Enviar");
		this.enviar.setBounds(330, 75, 100, 30);
		this.enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Sirve boton");
				try {
					Socket socket = new Socket("localhost", 5555);
					DataOutputStream sendsms = new DataOutputStream(socket.getOutputStream());
					sendsms.writeUTF(tfmensaje.getText());
					sendsms.close();
					socket.close();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					System.out.println(e1.getMessage());
				}
				chat.append("\nSERVIDOR: " + tfmensaje.getText());
				tfmensaje.setText(null);
			}
		});
		this.panel.add(enviar);
		
		this.chat = new JTextArea();
		this.chat.setBounds(50, 120, 380, 300);
		this.chat.setEditable(false);
		this.panel.add(chat);
		
		
		
		this.panel.setSize(500, 500);
		this.panel.setLayout(null);
		this.panel.setVisible(true);
		
		Thread mihiloserver = new Thread(this);
		mihiloserver.start();
		
}
		
		public JPanel getchat() {
			return this.panel;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Estoy a la escucha Servidor");
			try {
				servidor = new ServerSocket(9999);
				while (true) {	
				socket = servidor.accept();
				DataInputStream recievesms = new DataInputStream(socket.getInputStream());
				String sms = recievesms.readUTF();
				chat.append("\nCLIENTE: "+sms);
				socket.close();
				recievesms.close();
				}	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//System.out.println(e);
			}
		}
		
		
		
		
		
}
