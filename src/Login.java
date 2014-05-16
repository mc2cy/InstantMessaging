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

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Login {

	private JFrame Login;
	private JTextField userEmail;
	private JPasswordField userPassword;
	private String userEmail1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("deprecation")
	private void initialize() {
		Login = new JFrame();
		Login.getContentPane().setBackground(Color.ORANGE);
		Login.setBackground(Color.BLUE);
		Login.setForeground(Color.RED);
		Login.setTitle("Login");
		Login.setBounds(100, 100, 382, 484);
		Login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Login.getContentPane().setLayout(null);

		JLabel lblEnterName = new JLabel("Email : ");
		lblEnterName.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblEnterName.setBounds(66, 179, 65, 17);
		Login.getContentPane().add(lblEnterName);

		userEmail = new JTextField();
		userEmail.setBounds(131, 179, 141, 20);
		Login.getContentPane().add(userEmail);
		userEmail.setColumns(10);

		JButton btnClickMe = new JButton("Submit");
		btnClickMe.addActionListener(new BtnClickMeActionListener());
		btnClickMe.setBounds(57, 293, 89, 23);
		Login.getContentPane().add(btnClickMe);
		//System.exit(2);

		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblPassword.setBounds(42, 208, 79, 21);
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
				
				if ( userEmail1.equals("") && pass.equals(""))
				{
					userEmail1 = "cs2110test@gmail.com";
					pass = "softwaredevelopment";
				}
				
				connection.login(userEmail1, pass);

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

