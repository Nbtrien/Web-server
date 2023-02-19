package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class NewSite extends JFrame{
	
	private JPanel mainJPanel;
	private JTextField jTDomain;
	private JPasswordField jPassword;
	private JButton jBSubmit;
	private JPanel jPDomainForm;
	private JPanel jPUploadForm;
	
	private JButton jBUpload;
	private JTextField jTDirectPath;
	
	private JFileChooser chooser;
	private String choosertitle;
	private int sum = 0;
	
	public NewSite(String email, JTextField jTDomain, JPasswordField jPassword, JButton jBSubmit, JButton jBUpload) {
		
		this.jTDomain = jTDomain;
		this.jPassword = jPassword;
		this.jBSubmit = jBSubmit;
		this.jBUpload = jBUpload;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(50, 50);
		setTitle("Website Builder");
		setResizable(false);

        mainJPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(mainJPanel, BoxLayout.Y_AXIS);
        mainJPanel.setLayout(boxlayout);
        
        JPanel header  = new Header(email);
//        panel.add(header);
        
        JPanel jPHead = new JPanel();
        jPHead.setLayout(new BorderLayout());
        jPHead.setBackground(Color.white);
        jPHead.setBorder(new EmptyBorder(50, 00, 50, 00));
        
        JLabel jLTitle = new JLabel("CREATE NEW WEBSITE", SwingConstants.CENTER);
        jLTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        jPHead.add(jLTitle, BorderLayout.CENTER);
        
        mainJPanel.add(jPHead);
        jPDomainForm = createJPanelDomain();
        mainJPanel.add(jPDomainForm);
//        mainJPanel.add(createJPanelUpload());
        add(mainJPanel);
        
        pack();
	}
	
	
	
	public int getSum() {
		return sum;
	}


	private JPanel createJPanelDomain() {
		JPanel panel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
        
        JPanel jPForm = new JPanel();
        jPForm.setLayout(new BoxLayout(jPForm, BoxLayout.Y_AXIS));
        jPForm.setBorder(new EmptyBorder(100, 150, 100, 150));
        
        JPanel jPDomain = new JPanel();
        jPDomain.setLayout(new BoxLayout(jPDomain, BoxLayout.X_AXIS));
        jPDomain.setBorder(new EmptyBorder(new Insets(0, 0, 20, 0)));
        
        JLabel jLDomain = new JLabel("Enter your website name: ");
        jLDomain.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        jLDomain.setAlignmentY(Component.CENTER_ALIGNMENT);
        jLDomain.setPreferredSize(new Dimension(200, 20));
        jLDomain.setMaximumSize(new Dimension(200,20));
        jLDomain.setMinimumSize(new Dimension(200,20));
		
//        jTDomain = new JTextField();
        jTDomain.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        jTDomain.setAlignmentY(Component.CENTER_ALIGNMENT);
        jTDomain.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 12));
        jTDomain.setForeground(new Color(112,112,112));
        jTDomain.setPreferredSize(new Dimension(250,20));
        jTDomain.setMaximumSize(new Dimension(250,20));
        jTDomain.setMinimumSize(new Dimension(250,20));
        
        jPDomain.add(jLDomain);
        jPDomain.add(jTDomain);
        
        JPanel jPPass = new JPanel();
        jPPass.setLayout(new BoxLayout(jPPass, BoxLayout.X_AXIS));
        jPPass.setBorder(new EmptyBorder(new Insets(0, 0, 20, 0)));
        
        JLabel jLPass = new JLabel("Enter your password: ");
        jLPass.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        jLPass.setAlignmentY(Component.CENTER_ALIGNMENT);
        jLPass.setPreferredSize(new Dimension(200, 20));
        jLPass.setMaximumSize(new Dimension(200,20));
        jLPass.setMinimumSize(new Dimension(200,20));
        
//        jPassword = new JPasswordField();
        jPassword.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        jPassword.setAlignmentY(Component.CENTER_ALIGNMENT);
        jPassword.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 12));
        jPassword.setForeground(new Color(112,112,112));
        jPassword.setPreferredSize(new Dimension(250,20));
        jPassword.setMaximumSize(new Dimension(250,20));
        jPassword.setMinimumSize(new Dimension(250,20));
        
        jPPass.add(jLPass);
        jPPass.add(jPassword);
        
//        JButton jBSubmit = new JButton("SUBMIT");
        jBSubmit.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        jBSubmit.setForeground(Color.white);
        jBSubmit.setBorderPainted(false);
        jBSubmit.setFocusPainted(false);
        jBSubmit.setBackground(Color.red);
        jBSubmit.setBorder(BorderFactory.createMatteBorder(10, 35, 10, 35, Color.red));
        jBSubmit.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
//	        	jBSubmit.setBackground(Color.red);
	        }
	        @Override
	        public void mouseEntered(MouseEvent e) {
	        	jBSubmit.setCursor(new Cursor(Cursor.HAND_CURSOR));
	        }
	    });
        
        jPForm.add(jPDomain); 
        jPForm.add(jPPass);
        jPForm.add(jBSubmit);
        
        panel.add(jPForm);
       
        return panel;
	}
	
	public void showFormUpload() {
		mainJPanel.remove(jPDomainForm);
		jPUploadForm = createJPanelUpload();
		mainJPanel.add(jPUploadForm);
		
		mainJPanel.revalidate();
		mainJPanel.repaint();
	}
	
	public String getPath() {
		String path = "";
 		if (jTDirectPath.getText() != "") {
			path = jTDirectPath.getText();
		}
 		return path;
	}
	
	private JPanel createJPanelUpload() {
		
		JPanel panel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
        
        JPanel jPForm = new JPanel();
        jPForm.setLayout(new GridLayout(3, 1));
        jPForm.setBorder(new EmptyBorder(75, 100, 100, 125));
        
        JLabel jLNotice = new JLabel("Upload your project here (contains file index)", SwingConstants.CENTER);
        jLNotice.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        
        JPanel jPUpload = new JPanel();
//        jPUpload.setLayout(new BoxLayout(jPUpload, BoxLayout.X_AXIS));
        jPUpload.setBorder(new EmptyBorder(new Insets(0, 50, 20, 50)));
        
        jTDirectPath = new JTextField();
        jTDirectPath.setEditable(false);
        jTDirectPath.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        jTDirectPath.setAlignmentY(Component.CENTER_ALIGNMENT);
        jTDirectPath.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 12));
        jTDirectPath.setForeground(new Color(112,112,112));
        jTDirectPath.setPreferredSize(new Dimension(250,20));
        jTDirectPath.setMaximumSize(new Dimension(250,20));
        jTDirectPath.setMinimumSize(new Dimension(250,20));
        
        JButton jBChoose = new JButton("Choose Folder");
        jBChoose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				chooseFile();
			}
		});
        
        jPUpload.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPUpload.add(jTDirectPath);
        jPUpload.add(jBChoose);
        
        JPanel jPButton = new JPanel();
        
        this.jBUpload.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        this.jBUpload.setForeground(Color.white);
        this.jBUpload.setBorderPainted(false);
        this.jBUpload.setFocusPainted(false);
        this.jBUpload.setBackground(Color.red);
        this.jBUpload.setBorder(BorderFactory.createMatteBorder(10, 35, 10, 35, Color.red));
        
        this.jBUpload.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
//	        	jBSubmit.setBackground(Color.red);
	        }
	        @Override
	        public void mouseEntered(MouseEvent e) {
	        	 jBUpload.setCursor(new Cursor(Cursor.HAND_CURSOR));
	        }
	    });
        
        jPButton.add( this.jBUpload);
        
        jPForm.add(jLNotice);
        jPForm.add(jPUpload);
        jPForm.add(jPButton);
        
        panel.add(jPForm);

        return panel;
	}
	
	public void chooseFile() {
		System.out.println("chossse");
	    chooser = new JFileChooser(); 
	    chooser.setCurrentDirectory(new java.io.File("f:\\"));
	    chooser.setDialogTitle(choosertitle);
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);  
	    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
	      this.jTDirectPath.setText(chooser.getSelectedFile().getPath());
	      File srcDir = new File(chooser.getSelectedFile().getPath());
	      countFiles(srcDir);
	    }
	    else {
	      System.out.println("No Selection ");
	    }
    }
	
	public void countFiles(File srcDir) {
		int length;
		File[] files = srcDir.listFiles();
		length = files.length;
		this.sum += length;
		for (int i = length-1; i >= 0; i--) 
		{ 
			if (files[i].isDirectory()) {
				countFiles(files[i]);
			}
		} 
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JTextField jT = new JTextField();
		JPasswordField jP = new JPasswordField();
		JButton jB = new JButton("SUBMIT");
		JButton jBUpload = new JButton("UPLOAD");
		NewSite newSite = new NewSite("abc@gmail.com", jT, jP, jB, jBUpload);
		newSite.setVisible(true);
	}
}
