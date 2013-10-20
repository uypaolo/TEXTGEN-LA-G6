package View;

import Model.*;

import java.awt.Color;
import java.util.ArrayList;

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
	
	public void resetTables(){
		resetDetails();
		resetFeatures();
		resetConcept();
	}
	
	private void resetFeatures(){
		DefaultTableModel m = (DefaultTableModel) this.fTable.getModel();
		m.getDataVector().removeAllElements();
		m.fireTableDataChanged();
	}
	
	private void resetConcept(){
		DefaultTableModel m = (DefaultTableModel) this.cTable.getModel();
		m.getDataVector().removeAllElements();
		m.fireTableDataChanged();
	}
	
	private void resetDetails(){
		DefaultTableModel m = (DefaultTableModel) this.dTable.getModel();
		m.getDataVector().removeAllElements();
		m.fireTableDataChanged();
	}
	
	private void setFeatures(ArrayList<FeatureModel> fList){		
		
		if(fList.size()<1){
			resetFeatures();
		}
		else{
			DefaultTableModel m = (DefaultTableModel) this.fTable.getModel();
			m.getDataVector().removeAllElements();
			for(FeatureModel f: fList){		
				m.addRow(new String[]{f.getName(), f.getValue()});
			}
			
			m.fireTableDataChanged();
		}
	}
	
	private void setConcept(ConceptModel c){		
		
		if(c.getName().equals("")){
			resetConcept();
		}
		else{
			DefaultTableModel m = (DefaultTableModel) this.cTable.getModel();
			m.getDataVector().removeAllElements();	
			m.addRow(c.getName().split("-"));
			
			m.fireTableDataChanged();
		}
	}
	
	private void setDetails(ConstModel c){		
		
		DefaultTableModel m = (DefaultTableModel) this.dTable.getModel();
		m.getDataVector().removeAllElements();	
		m.addRow(new String[]{"Label", c.getLabel()});
		m.addRow(new String[]{"Features Count", ""+c.getFeatures().getFeatureList().size()});
		m.addRow(new String[]{"Subconst Count", ""+c.getSubconst().getConstList().size()});
		
		m.fireTableDataChanged();
	}
	
	public void setConst(constContainer cn){
		if(c != null){
			this.c.setForeground(Color.BLACK);
		}
		this.c = cn;
		this.c.setForeground(Color.RED);
		setDetails(cn.getDetails());
		setFeatures(cn.getDetails().getFeatures().getFeatureList());
		setConcept(cn.getDetails().getConcept());
		
	}
}
