package client;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Register extends JFrame{
	
	public Register(JTextField jTNameRegis, JTextField jTEmailRegis, JTextField jTEmailDomain, JPasswordField jPasswordRegis, JButton jBRegister) {
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
		
		JLabel jLRegister = new JLabel("Register");
		jLRegister .setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		jLRegister .setAlignmentX(Component.CENTER_ALIGNMENT);
		jLRegister .setBorder(new EmptyBorder(new Insets(0, 0, 10, 0)));
		
		JPanel jPUserName = new JPanel();
		jPUserName.setLayout(new BoxLayout(jPUserName, BoxLayout.X_AXIS));
		jPUserName.setAlignmentX(Component.CENTER_ALIGNMENT);
		jPUserName.setBorder(new EmptyBorder(new Insets(10, 0, 10, 0)));
		
		JLabel jLName = new JLabel("Name: ");
		jLName.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		jLName.setAlignmentY(Component.CENTER_ALIGNMENT);
		jLName.setPreferredSize(new Dimension(80,20));
		
		jTNameRegis.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jTNameRegis.setAlignmentY(Component.CENTER_ALIGNMENT);
		jTNameRegis.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 12));
		jTNameRegis.setForeground(new Color(112,112,112));
		jTNameRegis.setPreferredSize(new Dimension(250,20));
		
		jPUserName.add(jLName);
		jPUserName.add(jTNameRegis);
		
		JPanel jPEmail = new JPanel();
		jPEmail.setLayout(new BoxLayout(jPEmail, BoxLayout.X_AXIS));
		jPEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
		jPEmail.setBorder(new EmptyBorder(new Insets(10, 0, 10, 0)));
		
		JLabel jLEmail = new JLabel("Email: ");
		jLEmail.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		jLEmail.setAlignmentY(Component.CENTER_ALIGNMENT);
		jLEmail.setPreferredSize(new Dimension(80,20));
		
		jTEmailRegis.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jTEmailRegis.setAlignmentY(Component.CENTER_ALIGNMENT);
		jTEmailRegis.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 12));
		jTEmailRegis.setForeground(new Color(112,112,112));
		jTEmailRegis.setPreferredSize(new Dimension(150,20));
		
		jTEmailRegis.setMaximumSize(new Dimension(150, 20));
		
		jTEmailDomain.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jTEmailDomain.setAlignmentY(Component.CENTER_ALIGNMENT);
		jTEmailDomain.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 12));
		jTEmailDomain.setAlignmentX(Component.RIGHT_ALIGNMENT);
		jTEmailDomain.setText("@gmail.com");
		jTEmailDomain.setDisabledTextColor(new Color(112,112,112));;
		jTEmailDomain.setEnabled(false);
		
		jPEmail.add(jLEmail);
		jPEmail.add(jTEmailRegis);
		jPEmail.add(Box.createHorizontalStrut(5));
		jPEmail.add(jTEmailDomain);
		
		JPanel jPPassWord = new JPanel();
		jPPassWord.setLayout(new BoxLayout(jPPassWord, BoxLayout.X_AXIS));
		jPPassWord.setAlignmentX(Component.CENTER_ALIGNMENT);
		jPPassWord.setBorder(new EmptyBorder(new Insets(10, 0, 15, 0)));
		
		JLabel jLPassWord = new JLabel("PassWord: ");
		jLPassWord.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		jLPassWord.setAlignmentY(Component.CENTER_ALIGNMENT);
		jLPassWord.setPreferredSize(new Dimension(80,20));
		
		jPasswordRegis.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jPasswordRegis.setAlignmentY(Component.CENTER_ALIGNMENT);
		jPasswordRegis.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 12));
		
		jPPassWord.add(jLPassWord);
		jPPassWord.add(jPasswordRegis);
		
		jPSignForm.add(jLRegister );
		jPSignForm.add(jPUserName);
		jPSignForm.add(jPEmail);
		jPSignForm.add(jPPassWord);
		
		jBRegister.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		jBRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		jPSignForm.add(jBRegister);		
		
		panel.add(jPSignForm);
		
		add(panel);
		pack();
	}


}
