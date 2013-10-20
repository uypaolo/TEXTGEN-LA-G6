package View;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

public class ViewPanel extends JPanel{
	
	private constContainer c;
	
	private JTable dTable;
	private JTable fTable;
	private JTable cTable;
	
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
		DefaultTableModel m = new DefaultTableModel(new Object[]{"Name", "Value"},0){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		this.dTable = new JTable(m);
		this.dTable.setTableHeader(null);
		
		m = new DefaultTableModel(new Object[]{"Name", "Value"},0){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		this.fTable = new JTable(m);
		this.fTable.setTableHeader(null);
		
		m = new DefaultTableModel(new Object[]{"Name", "Value"},0){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		this.cTable = new JTable(m);
		this.cTable.setTableHeader(null);
		
		this.fPane = new JScrollPane(this.fTable);
		this.dPane = new JScrollPane(this.dTable);
		this.cPane = new JScrollPane(this.cTable);
		
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
