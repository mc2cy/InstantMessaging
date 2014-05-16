import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import javax.swing.JPanel;

public class WelcomeWindow {

	private JFrame Login;
	protected JTextField userEmail;
	protected JPasswordField userPassword;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeWindow window = new WelcomeWindow();
					window.Login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WelcomeWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("deprecation")
	private void initialize() {
		Login = new JFrame();
		Login.setResizable(false);
		Login.getContentPane().setBackground(Color.ORANGE);
		Login.setBackground(Color.BLUE);
		Login.setForeground(Color.RED);
		Login.setTitle("Login");
		Login.setBounds(100, 100, 384, 373);
		Login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Login.getContentPane().setLayout(null);
		
		JPanel panel = new ImagePanel("logo.png",null);
		panel.setBounds(57, 6, 244, 161);
		Login.getContentPane().add(panel);

		JLabel lblEnterName = new JLabel("Email : ");
		lblEnterName.setBounds(66, 179, 65, 17);
		lblEnterName.setFont(new Font("Segoe UI", Font.BOLD, 14));
		Login.getContentPane().add(lblEnterName);

		userEmail = new JTextField();
		userEmail.setBounds(131, 179, 141, 20);
		Login.getContentPane().add(userEmail);
		userEmail.setColumns(10);

		JButton btnClickMe = new JButton("Submit");
		btnClickMe.setBounds(57, 293, 89, 23);
		btnClickMe.addActionListener(new BtnClickMeActionListener());
		Login.getContentPane().add(btnClickMe);
		//System.exit(2);

		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(42, 208, 79, 21);
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 14));
		Login.getContentPane().add(lblPassword);

		userPassword = new JPasswordField();
		userPassword.setBounds(131, 210, 170, 20);
		Login.getContentPane().add(userPassword);

		userPassword.addActionListener(new BtnClickMeActionListener());
		
	}

	private class BtnClickMeActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
			char[] passChars = userPassword.getPassword();
			String pass = "";
			for (int i = 0; i < passChars.length; i++) {
				pass += passChars[i];
			}
			
			ConnectionConfiguration config = new ConnectionConfiguration(
					"talk.google.com", 5222, "gmail.com");
			XMPPConnection connection = new XMPPConnection(config);
			try {
				System.out.println("Trying to connect...");
				connection.connect();

				System.out.println("Trying to login...");
				
//				if ( userEmail1.equals("") && pass.equals(""))
//				{
//					userEmail1 = "cs2110test@gmail.com";
//					pass = "softwaredevelopment";
//				}
				System.out.println("Email printing!" + userEmail.getText());
				connection.login(userEmail.getText(), pass);

				// See if you are authenticated
				boolean connected = connection.isAuthenticated();
				System.out.println("   Login successful? "
						+ connected);
				if(connected){
					Login.setVisible(false);
				}
				else{
					System.out.println("Try to login again please");
					Login.setVisible(true);
				}
			} catch (XMPPException e1) {
				System.out.println("Error connecting");
				e1.printStackTrace();
			}

			Scanner stdin = new Scanner(System.in);
			// System.out.print("Chat with whom? ");
			// String myBuddy = stdin.next() + "@gmail.com";
			String myBuddy = "uvacs2110@gmail.com";
			//Login.setVisible(false);
			System.out.println("Instantiating ChatManager...");
			ChatManager chatmanager = connection.getChatManager();

			System.out.println("Starting new chat...");
			Chat newChat = chatmanager.createChat(myBuddy,
					new MessageListener() {
						// the even handler is below
						public void processMessage(Chat chat, Message message) {
							System.out.println("\n[From: " + message.getFrom()
									+ "] " + message.getBody());
						}
					});

			System.out.print("Enter message: ");
			String msg = stdin.nextLine();
			while (!msg.startsWith("bye")) {
				try {
					newChat.sendMessage(msg);
				} catch (XMPPException e) {
					System.out.println("Error Delivering message");
				}
				System.out.print("Enter message: ");
				msg = stdin.nextLine();
			}
			{
				System.exit(0);
			}
		}

	}
}

