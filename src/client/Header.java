package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class Header extends JPanel{
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    
	public Header(String email) {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(1200, 50));
		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
//        setBorder(BorderFactory.createLineBorder(new Color(223, 174	, 174)));
        setBackground(Color.white);
        
        final JMenuBar menuBar = new JMenuBar(); // Tao cac menu
        menuBar.setBackground(Color.white);
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        final JMenu aboutMenu = new JMenu("About");
        final JMenu linkMenu = new JMenu("Hellp");
        
        fileMenu.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        editMenu.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        aboutMenu.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        linkMenu.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(aboutMenu);
        menuBar.add(linkMenu); 
        menuBar.setBorder(new EmptyBorder(0, 20, 0, 0));
        
        JLabel jPEmail = new JLabel(email);
        jPEmail.setBorder(new EmptyBorder(0, 0, 0, 20));
        jPEmail.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        
        headerLabel = new JLabel("WEB SERVER",  SwingConstants.CENTER);
        headerLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        
        add(menuBar, BorderLayout.LINE_START);
        add(headerLabel, BorderLayout.CENTER);
        add(jPEmail, BorderLayout.LINE_END);
	}
	
	class MenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            statusLabel.setText(e.getActionCommand() + " JMenuItem clicked.");
        }
    }

}
