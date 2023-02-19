package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.EmptyBorder;

import core.Website;

public class ListSide extends JFrame{
	private JPanel jPListSide;
	
	public ListSide(String email, JButton jBNewSite) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocation(50, 50);
		setTitle("Website Builder");
		setResizable(false);

        JPanel panel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        
        JPanel header  = new Header(email);
        panel.add(header);
        
        JPanel jPHead = new JPanel();
        jPHead.setLayout(new BorderLayout());
        jPHead.setBackground(Color.white);
        jPHead.setBorder(new EmptyBorder(50, 100, 50, 100));
        
        JPanel jPTitle = new JPanel();
        jPTitle.setLayout(new BoxLayout(jPTitle, BoxLayout.Y_AXIS));
        jPTitle.setBackground(Color.white);
        
        JLabel jLTitle = new JLabel("MY WEBSITES");
        jLTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        
        JLabel jLDesc = new JLabel("Select a website to manage, or create a new one from scratch.");
        jLDesc.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        
        jPTitle.add(jLTitle);
        jPTitle.add(jLDesc);
        
//        JButton jBNewSite = new JButton("Create new website");
        jBNewSite.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        jBNewSite.setForeground(Color.BLUE);
        jBNewSite.setContentAreaFilled(false);
        jBNewSite.setFocusPainted(false);
        jBNewSite.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
 
	        }
	        @Override
	        public void mouseEntered(MouseEvent e) {
	        	jBNewSite.setCursor(new Cursor(Cursor.HAND_CURSOR));
	        }
	    });
        
        jPHead.add(jPTitle, BorderLayout.LINE_START);
        jPHead.add(jBNewSite, BorderLayout.LINE_END);
        
        panel.add(jPHead);
        
        jPListSide = new JPanel();
        jPListSide.setBackground(new Color(240, 240, 240));
        WrapLayout flowLayout = new WrapLayout(0,10,10);
        jPListSide.setLayout( flowLayout);
        jPListSide.setBorder(new EmptyBorder(50, 100, 50, 100));
        jPListSide.setSize(new Dimension(1000, 0));
        
        JScrollPane jScrollPane = new JScrollPane(jPListSide);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setPreferredSize(new Dimension(1200, 450));
        jScrollPane.setComponentZOrder(jScrollPane.getVerticalScrollBar(), 0);
		jScrollPane.setComponentZOrder(jScrollPane.getViewport(), 1);
		jScrollPane.getVerticalScrollBar().setOpaque(false);
		
		jScrollPane.setLayout(new ScrollPaneLayout() {
		      @Override
		      public void layoutContainer(Container parent) {
		        JScrollPane jScrollPane = (JScrollPane) parent;

		        Rectangle availR = jScrollPane.getBounds();
		        availR.x = availR.y = 0;

		        Insets parentInsets = parent.getInsets();
		        availR.x = parentInsets.left;
		        availR.y = parentInsets.top;
		        availR.width -= parentInsets.left + parentInsets.right;
		        availR.height -= parentInsets.top + parentInsets.bottom;

		        Rectangle vsbR = new Rectangle();
		        vsbR.width = 9;
		        vsbR.height = availR.height;
		        vsbR.x = availR.x + availR.width - vsbR.width;
		        vsbR.y = availR.y;

		        if (viewport != null) {
		          viewport.setBounds(availR);
		        }
		        if (vsb != null) {
		          vsb.setVisible(true);
		          vsb.setBounds(vsbR);
		        }
		      }
		    });
		jScrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
        
        panel.add(jScrollPane);
        
        add(panel);
        pack();
	}
	
	public void setListSide(List<Website> list) {
		this.jPListSide.removeAll();
		
		for (Website website : list) {
        	JButton jBManage = new JButton("Manage Website");
			jPListSide.add(createPanelSide(jBManage, website.getName(), website.getUrl()));
		}
		this.jPListSide.revalidate();
		this.jPListSide.repaint();
	}
	
	public JPanel createPanelSide(JButton jBManage, String webName, String domain) {
		JPanel jPSide = new JPanel();
		jPSide.setLayout(new BoxLayout(jPSide, BoxLayout.Y_AXIS));
		jPSide.setBackground(Color.white);
		jPSide.setBorder(BorderFactory.createLineBorder(new Color(85, 82, 82)));
		jPSide.setPreferredSize(new Dimension(235, 100));
		
		JLabel jLSideName = new JLabel(webName);
		jLSideName.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		jLSideName.setBorder(new EmptyBorder(5, 10, 5, 10));
		
		JLabel jLDomain = new JLabel(domain);
		jLDomain.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 13));
		jLDomain.setForeground(new Color(108, 102, 255));
		jLDomain.setBorder(new EmptyBorder(0, 10, 5, 10));
		jLDomain.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	        	URL url;
				try {
					url = new URL(domain);
					openWebpage(url);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	
	        }
	        @Override
	        public void mouseEntered(MouseEvent e) {
	        	jLDomain.setCursor(new Cursor(Cursor.HAND_CURSOR));
	        }
	    });
		
		jBManage.setBorder(new EmptyBorder(0, 10, 5, 10));
		jBManage.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		jBManage.setForeground(Color.BLUE);
		jBManage.setContentAreaFilled(false);
		jBManage.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
 
	        }
	        @Override
	        public void mouseEntered(MouseEvent e) {
	        	jBManage.setCursor(new Cursor(Cursor.HAND_CURSOR));
	        }
	    });
		
		jPSide.add(jLSideName);
		jPSide.add(jLDomain);
		jPSide.add(Box.createVerticalStrut(20));
		jPSide.add(jBManage);
		
		return jPSide;
	}
	
	public static boolean openWebpage(URI uri) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}

	public static boolean openWebpage(URL url) {
	    try {
	        return openWebpage(url.toURI());
	    } catch (URISyntaxException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		new ListSide();
	}

}
