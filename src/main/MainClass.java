package main;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        //Schedule a job for the event-dispatching thread:
        //adding TrayIcon.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

	}
	
	 private static void createAndShowGUI() {
	        //Check the SystemTray support
	        if (!SystemTray.isSupported()) {
	            System.out.println("SystemTray is not supported");
	            return;
	        }
	        final PopupMenu popup = new PopupMenu();
	        final TrayIcon trayIcon =
	                new TrayIcon(createImage("/resource/battery.png", "tray icon"));
	        final SystemTray tray = SystemTray.getSystemTray();
	        
	        // Create a popup menu components
	        MenuItem aboutItem = new MenuItem("About");
	        MenuItem exitItem = new MenuItem("Exit");
	        //Add components to popup menu
	        popup.add(aboutItem);
	        popup.addSeparator();
	        popup.add(exitItem);
	        
	        trayIcon.setPopupMenu(popup);
	        
	        try {
	            tray.add(trayIcon);
	            
	        } catch (AWTException e) {
	            System.out.println("TrayIcon could not be added.");
	            return;
	        }
	        
	        trayIcon.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                JOptionPane.showMessageDialog(null,runWithPrivileges());
	            }
	        });
	        
	        aboutItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                JOptionPane.showMessageDialog(null,
	                        "This dialog box is run from the About menu item");
	            }
	        });
	        
	        exitItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                tray.remove(trayIcon);
	                System.exit(0);
	            }
	        });
	    }
	    
	//Obtain the image URL
	    protected static Image createImage(String path, String description) {
	        URL imageURL = MainClass.class.getResource(path);
	        
	        if (imageURL == null) {
	            System.err.println("Resource not found: " + path);
	            return null;
	        } else {
	            return (new ImageIcon(imageURL, description)).getImage();
	            
	        }
	    }
	    
		public static String runWithPrivileges() {
			InputStreamReader input;
			OutputStreamWriter output;
			final String commBash = "/bin/bash";
			final String commInput = "/usr/bin/sudo -S /bin/cpupower frequency-set -g powersave 2>&1"; 
			final String msgResult = "PowerSafe enabled";
			
			try {
				// Create the process and start it.
				Process pb = new ProcessBuilder(new String[] { commBash, "-c", commInput }).start();
				output = new OutputStreamWriter(pb.getOutputStream());
				input = new InputStreamReader(pb.getInputStream());

				int bytes = 0;
				char buffer[] = new char[1024];
				while ((bytes = input.read(buffer, 0, 1024)) != -1) {
					if (bytes == 0)
						continue;
					// Output the data to console, for debug purposes
					String result = String.valueOf(buffer, 0, bytes);
					// Check for password request
					if (result.contains("[sudo] password")) {
						// Here you can request the password to user using JOPtionPane or
						// System.console().readPassword();
						char password[] = new char[] { '1', '2', '3' };
						output.write(password);
						output.write('\n');
						output.flush();
						Arrays.fill(password, '\0');
					}
				}

			} catch (IOException ex) {
				ex.printStackTrace();
				return "TrayIcon could not be added." + ex;
			}
			return msgResult;
		}
}
