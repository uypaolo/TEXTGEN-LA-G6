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
	
	private JTextArea ft;
	private JTextArea dt;
	private JTextArea ct;
	
	public ViewPanel(){
		this.c = null;
		this.setLayout(new MigLayout());
		init();
	}
	
	public void init(){
		this.tPane = new JTabbedPane();
		//this.tPane.setLayout(new MigLayout());
		
		this.ft = new JTextArea();
		this.ft.setText("");
		this.ft.setEditable(false);
		
		this.dt = new JTextArea();
		this.dt.setText("");
		this.dt.setEditable(false);
		
		this.ct = new JTextArea();
		this.ct.setText("");
		this.ct.setEditable(false);
		
		this.details = new JPanel();
		this.details.setLayout(new MigLayout());
		this.details.add(this.dt);
		
		this.features = new JPanel();
		this.features.setLayout(new MigLayout());
		this.features.add(this.ft);
		
		this.concepts = new JPanel();
		this.concepts.setLayout(new MigLayout());
		this.concepts.add(this.ct);
		
		this.dt.setBackground(details.getBackground());
		this.ft.setBackground(features.getBackground());
		this.ct.setBackground(features.getBackground());
		
		this.fPane = new JScrollPane(this.features);
		this.dPane = new JScrollPane(this.details);
		this.cPane = new JScrollPane(this.concepts);
		
		this.tPane.add("Details", this.dPane);
		this.tPane.add("Features", this.fPane);
		this.tPane.add("Concepts", this.cPane);
		
		this.add(tPane, "push, grow");
	}
	
	public void resetPanels(){
		this.ft.setText("");
		this.ct.setText("");
		this.dt.setText("");
	}
	
	public void setDetails(String det){
		//this.dt.
		this.dt.setText(det);
	}
	
	public void setFeatures(String feat){
		if(feat.trim().equals("")){
			this.ft.setText("No Feature");
		}
		else{
			this.ft.setText(feat);
		}
	}
	
	public void setConcepts(String con){
		if(con.trim().equals("")){
			this.ct.setText("No Concept");
		}
		else{
			this.ct.setText(con);
		}
	}
	
	public void setConst(constContainer cn){
		if(c != null){
			this.c.setForeground(Color.BLACK);
		}
		this.c = cn;
		this.c.setForeground(Color.RED);
	}
}
