package swing_label;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class swing_demo extends JFrame implements ActionListener {
	//method for creating JFrame,JLabel,JTextFiled and JButton
	JFrame jfrm;
	JTextField srcText;
	JTextField destText;
	String so;
	String de="";
	JFileChooser chooser1,chooser2;
	
	public void createGui()
	{
		// Create a new JFrame container.
		jfrm = new JFrame("An Event Example");
		
		// Give the frame an initial size.
		jfrm.setSize(1000, 500);
		jfrm.setTitle("copyFiles");;
		// Terminate the program when the user closes the application.
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfrm.setLayout(null);
		JLabel jlab1 = new JLabel("Source");
		JLabel jlab2 = new JLabel("Destination");
		JButton loginButton = new JButton("Copy");
		JButton browseButton1 = new JButton("Browse1");
		JButton browseButton2 = new JButton("Browse2");
		JButton create = new JButton("Prepare");
		JButton showDetails = new JButton("ShowLatestFiles");
		srcText = new JTextField();
	    destText = new JTextField();
       
      // 
       
		 /* This method specifies the location and size
         * of component. setBounds(x, y, width, height)
         * here (x,y) are coordinates from the top left 
         * corner and remaining two arguments are the width
         * and height of the component.
         */
		jlab1.setBounds(10,20,80,25);
		jlab2.setBounds(10,50,80,25);
		///////////////////////////
		srcText.setBounds(100,20,165,25);
		destText.setBounds(100,50,165,25);
		///////////////////////////
		loginButton.setBounds(250, 180, 80, 25);
		browseButton1.setBounds(600,20,180,25);
		browseButton2.setBounds(600,50,180,25);
		showDetails.setBounds(300,70,150,25);
		create.setBounds(50,180,80,25);
		
		///////////////////////////
		// Add the label to the content pane.
		jfrm.add(jlab1);
		jfrm.add(jlab2);
		jfrm.add(loginButton);
		jfrm.add(browseButton1);
		jfrm.add(browseButton2);
		jfrm.add(showDetails);
		jfrm.add(create);
		jfrm.add(srcText);
		jfrm.add(destText);
		// Display the frame.
		loginButton.addActionListener(this); 
		browseButton1.addActionListener(this); 
		browseButton2.addActionListener(this); 
		showDetails.addActionListener(this); 
		create.addActionListener(this); 
		jfrm.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		swing_demo sd= new swing_demo();
		
		sd.createGui();
		JOptionPane.showMessageDialog(sd.jfrm, "~  Click on ShowLatestFiles button after choosing source and destination directories to show the latest files in source"
		+"\n\n~   Before Clicking on the Copy button ,click on the Prepare button so as to create the same directory structure in destination(if not present already) as that in source");
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		swing_demo sd= new swing_demo();
		String s = e.getActionCommand(); 
		 if (s.equals("Copy")) { 
	            // set the text of the label to the text of the field 
			
			 try {
				create valuee = new create(so,de);
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 
	     } 
		 else if (s.equals("Browse1")) { 
	            // set the text of the label to the text of the field 
			 chooser1 = new JFileChooser(); 
			 chooser1.setCurrentDirectory(new java.io.File("."));
			    chooser1.setDialogTitle("choosertitle");
			    chooser1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    chooser1.setAcceptAllFileFilterUsed(false);

			    if (chooser1.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			    	
			      System.out.println("getCurrentDirectory(): " + chooser1.getCurrentDirectory());
			      System.out.println("getSelectedFile() in browse1: " + chooser1.getSelectedFile());
			     System.out.println( "the absolute path in browse1"+chooser1.getSelectedFile().getAbsolutePath());
			      File f1= new File(chooser1.getSelectedFile().getAbsolutePath());
			      System.out.println("the name of the file  in browse1 "+f1.getName());
			      srcText.setText(chooser1.getSelectedFile().getAbsolutePath());
			    } else {
			      System.out.println("No Selection ");
			    }
			    
	     } 
		 else if (s.equals("Browse2")) { 
	            // set the text of the label to the text of the field 
			 chooser2 = new JFileChooser(); 
			 chooser2.setCurrentDirectory(new java.io.File("."));
			    chooser2.setDialogTitle("choosertitle");
			    chooser2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    chooser2.setAcceptAllFileFilterUsed(false);

			    if (chooser2.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			      
			      System.out.println("getSelectedFile()in browse2 : " + chooser1.getSelectedFile());
			     System.out.println( "the absolute path in browse2 "+chooser1.getSelectedFile().getAbsolutePath());
			      File f2= new File(chooser2.getSelectedFile().getAbsolutePath());
			      System.out.println("the name of the file in browse2 "+f2.getName());
			      destText.setText(chooser2.getSelectedFile().getAbsolutePath());
			    } else {
			      System.out.println("No Selection ");
			    }
			    
	     } 
		 else if(s.equals("ShowLatestFiles"))
		 {
			 try {
				compare a = new compare(srcText.getText(),destText.getText());
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 
		 }
		 else if(s.equals("Prepare"))
		 {
			 String textS=srcText.getText();
			 String textd=destText.getText();  
			
			 System.out.println("buttomn");
			 System.out.println("source  "+textS);
			 System.out.println("dest  "+textd);
			 //source and destination names are passed
			 so=srcText.getText();
			 de=destText.getText();
			 createEntireSubdirectory value = new createEntireSubdirectory(so,de);
		 
		 }
		 
		 
	}


	

}
