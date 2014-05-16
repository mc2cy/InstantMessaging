import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;


public class LoginDetailWindow {

	private JFrame frmLoggingIn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginDetailWindow window = new LoginDetailWindow();
					window.frmLoggingIn.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginDetailWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoggingIn = new JFrame();
		frmLoggingIn.setTitle("Logging In\u2026\n");
		frmLoggingIn.setBounds(100, 100, 450, 300);
		frmLoggingIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoggingIn.getContentPane().setLayout(null);
		
		JLabel label = DefaultComponentFactory.getInstance().createLabel("");
		label.setBounds(-14, 166, 120, 16);
		frmLoggingIn.getContentPane().add(label);
		
		JLabel lblPleaseWaitWhile = new JLabel("Please Wait While You are Being Logged In...");
		lblPleaseWaitWhile.setBounds(18, 30, 283, 22);
		frmLoggingIn.getContentPane().add(lblPleaseWaitWhile);
	}
}
