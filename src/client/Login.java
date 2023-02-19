package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {
	
	public Login(JButton jBLogin, JButton jBSignUp, JTextField jTEmail, JPasswordField jPassword) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setLocation(50, 50);
		setTitle("Website Builder");
		setResizable(false);
//		Init
		JPanel panel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        panel.setBorder(new EmptyBorder(new Insets(20, 50, 20, 50)));
		
		JPanel jPSignForm = new JPanel();
		jPSignForm.setLayout(new BoxLayout(jPSignForm, BoxLayout.Y_AXIS));
		jPSignForm.setAlignmentX(Component.CENTER_ALIGNMENT);
		jPSignForm.setBorder(new EmptyBorder(new Insets(25, 20, 25, 20)));
		
		JLabel jLSignIn= new JLabel("Login");
		jLSignIn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		jLSignIn.setAlignmentX(Component.CENTER_ALIGNMENT);
		jLSignIn.setBorder(new EmptyBorder(new Insets(0, 0, 10, 0)));
		
		JPanel jPEmail = new JPanel();
		jPEmail.setLayout(new BoxLayout(jPEmail, BoxLayout.X_AXIS));
		jPEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
		jPEmail.setBorder(new EmptyBorder(new Insets(15, 0, 15, 0)));
		
		JLabel jLEmail = new JLabel("Email: ");
		jLEmail.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		jLEmail.setAlignmentY(Component.CENTER_ALIGNMENT);
		jLEmail.setPreferredSize(new Dimension(80,20));
		
//		jTEmail = new JTextField();
		jTEmail.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		jTEmail.setAlignmentY(Component.CENTER_ALIGNMENT);
		jTEmail.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 12));
		jTEmail.setForeground(new Color(112,112,112));
		jTEmail.setPreferredSize(new Dimension(200,20));
		
		jPEmail.add(jLEmail);
		jPEmail.add(jTEmail);
		
		JPanel jPPassWord = new JPanel();
		jPPassWord.setLayout(new BoxLayout(jPPassWord, BoxLayout.X_AXIS));
		jPPassWord.setAlignmentX(Component.CENTER_ALIGNMENT);
		jPPassWord.setBorder(new EmptyBorder(new Insets(15, 0, 15, 0)));
		
		JLabel jLPassWord = new JLabel("PassWord: ");
		jLPassWord.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		jLPassWord.setAlignmentY(Component.CENTER_ALIGNMENT);
		jLPassWord.setPreferredSize(new Dimension(80,20));
		
//		jPassword = new JPasswordField();
		jPassword.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		jPassword.setAlignmentY(Component.CENTER_ALIGNMENT);
		jPassword.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 12));
		
		jPPassWord.add(jLPassWord);
		jPPassWord.add(jPassword);
		
		jPSignForm.add(jLSignIn);
		jPSignForm.add(jPEmail);
		jPSignForm.add(jPPassWord);
		
//		jBLogin = new JButton("Login");
		jBLogin.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		jBLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
//		jBLogin.addActionListener(this);
		jPSignForm.add(jBLogin);		
		
//		jBSignUp = new JButton("Create new account");
		jBSignUp.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 13));
		jBSignUp.setForeground(Color.BLUE);
		jBSignUp.setOpaque(false);
		jBSignUp.setContentAreaFilled(false);
		jBSignUp.setBorderPainted(false);
//		jBSignUp.addActionListener(this);
		
		JPanel jPSignUp = new JPanel(new BorderLayout());
		jPSignUp.add(jBSignUp, BorderLayout.LINE_END);
		
		panel.add(jPSignForm);

		panel.add(jPSignUp);
		
		add(panel);
		pack();
		
	}
	
}
