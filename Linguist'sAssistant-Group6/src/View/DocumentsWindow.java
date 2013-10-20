package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

public class DocumentsWindow extends JFrame implements WindowListener{
	
	private JComboBox bookList;
	private JComboBox verseList;
	private JLabel bLabel;
	private JLabel vLabel;
	private JButton okButton;
	private JButton cancelButton;
	private String sVerse;
	private String sBook;
	private String currFile;
	private String[] vs;
	
	public DocumentsWindow(){
		this.currFile = "";
		this.setLayout(new MigLayout("", "[grow]", "[grow]"));
		init();
		setFrame();
		
	}
	
	private void init(){
		this.bookList = new JComboBox(getBooks());
		this.bookList.setSelectedIndex(0);
		this.sBook = bookList.getSelectedItem().toString();
		this.bookList.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                //enables the button when an item is selected
            	verseList.setModel(new JComboBox(getVerses(bookList.getSelectedItem().toString())).getModel());
            	sBook = bookList.getSelectedItem().toString();
            	sVerse = verseList.getSelectedItem().toString();
            }
		});
		
		
		this.verseList = new JComboBox(getVerses(this.bookList.getSelectedItem().toString()));
		this.verseList.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                //enables the button when an item is selected
            	sVerse = verseList.getSelectedItem().toString();
            }
		});
		this.verseList.setSelectedIndex(0);
		this.sVerse = verseList.getSelectedItem().toString();
		
		this.bLabel = new JLabel("Book: ");
		this.vLabel = new JLabel("Verse: ");
		
		this.okButton = new JButton("Ok");
		this.okButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				currFile = "src/Documents/"+sBook+"/"+sVerse+".xml";
				dispose();
				//Frame.lo
			}
			
		});
		this.cancelButton = new JButton("Cancel");
		this.cancelButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//currFile = "src/Documents/"+sBook+"/"+sVerse+".xml";
				dispose();
				//Frame.lo
			}
			
		});
		
		this.add(this.bLabel, "split 2, span 2, right");
		this.add(this.bookList, "wrap, growx");
		this.add(this.vLabel, "split 2, span 2, right");
		this.add(this.verseList, "wrap, growx");
		this.add(this.okButton, "split 2, span 2, center");
		this.add(this.cancelButton);
	}
	
	private String[] getVerses(String book){
		System.out.println(book);
		ArrayList<String> verses = new ArrayList<String>();
		
		File directory = new File("src/Documents/"+book+"/");
		 
        //System.out.println(directory.exists());
        //get all the files from a directory
        File[] fList = directory.listFiles();
        
 
        for (File file : fList){
            if (file.isFile()){
            	if(file.getName().endsWith(".xml") && file.getName().startsWith(book+"_")){
            		verses.add(file.getName().replace(".xml", ""));
            	}
            }
        }
		
		String[] s = new String[verses.size()];
		s = verses.toArray(s);
		
		this.vs = s;
		System.out.println(verses.size());
		return s;
	}
	
	private String[] getBooks(){
		ArrayList<String> books = new ArrayList<String>();
		
		File directory = new File("src/Documents/");
		 
        //System.out.println(directory.exists());
        //get all the files from a directory
        File[] fList = directory.listFiles();
        
 
        for (File file : fList){
            if (file.isDirectory()){
                books.add(file.getName());
            }
        }
        
        String[] s = new String[books.size()];
        s = books.toArray(s);
		
		return s;
	}
	
	private void setFrame(){
		this.setTitle("Document Browser");
		this.setSize(300, 150);//640
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//this.addWindowListener(this);
		this.setVisible(true);
		this.setBackground(Color.DARK_GRAY);
		//this.pack();
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public String getCurrFile(){
		return currFile;
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		Frame.dwOpen = false;
		System.out.println("closed");
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public String getVerse(){
		return this.sVerse;
	}
	
	public String getBook(){
		return this.sBook;
	}
	
	public String[] getVerses(){
		return this.vs;
	}
}
