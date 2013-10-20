package View;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;

public class ViewPanel extends JPanel{
	
	private constContainer c;
	
	private JPanel details;
	private JPanel features;
	private JPanel concepts;
	
	private JTabbedPane tPane;
	private JScrollPane fPane;
	private JScrollPane dPane;
	private JScrollPane cPane;
	
	public ViewPanel(){
		this.c = null;
		this.setLayout(new MigLayout());
		init();
	}
	
	public void init(){
		this.tPane = new JTabbedPane();
		//this.tPane.setLayout(new MigLayout());
		
		this.details = new JPanel();
		this.details.setLayout(new MigLayout());
		
		this.features = new JPanel();
		this.features.setLayout(new MigLayout());
		
		this.concepts = new JPanel();
		this.concepts.setLayout(new MigLayout());
		
		this.fPane = new JScrollPane(this.features);
		this.dPane = new JScrollPane(this.details);
		this.cPane = new JScrollPane(this.concepts);
		
		this.tPane.add("Details", this.dPane);
		this.tPane.add("Features", this.fPane);
		this.tPane.add("Concepts", this.cPane);
		
		this.add(tPane, "push, grow");
	}
	
	public void resetPanels(){

	}
	
	
	public void setConst(constContainer cn){
		if(c != null){
			this.c.setForeground(Color.BLACK);
		}
		this.c = cn;
		this.c.setForeground(Color.RED);
	}
}
