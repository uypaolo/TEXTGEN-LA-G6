package View;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;

public class MapLexiconFrame extends JFrame{
	private JSplitPane sPane;
	
	private JPanel cont;
	private JPanel tools;
	
	private JPanel lex;
	private JPanel Ont;
	
	private JScrollPane lPane;
	private JScrollPane oPane;
	
	private JTable lTable;
	private JTable oTable;
	
	public MapLexiconFrame(){
		this.setLayout(new MigLayout());
		init();
		setFrame();
	}
	
	private void init(){
		this.lPane = new JScrollPane(new JTextArea("llllllllllllllllllllllllllllllllllllllllllll\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nllllllllllllllllllllllllllllllllll"));
		this.oPane = new JScrollPane(new JTextArea("llllllllllllllllllllllllllllllllllllllllllll\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nllllllllllllllllllllllllllllllllll"));
		
		this.sPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		//this.sPane.setLayout(new MigLayout());
		this.sPane.add(this.lPane);
		this.sPane.add(this.oPane);
		
		this.cont = new JPanel();
		this.cont.setLayout(new MigLayout());
		this.cont.add(this.sPane, "push, grow");
		this.cont.setPreferredSize(new Dimension(500, 500));
		this.cont.setBackground(Color.YELLOW);
		
		this.tools = new JPanel();
		this.tools.setLayout(new MigLayout());
		this.tools.setPreferredSize(new Dimension(200, 500));
		this.tools.setBackground(Color.BLUE);
		
		this.add(cont, "push, grow");
		this.add(tools, "growy");
	}
	
	private void setFrame(){
		this.setTitle("Map Lexicon");
		//this.setSize(750, 545);//640
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setBackground(Color.DARK_GRAY);
		this.pack();
	}

}
