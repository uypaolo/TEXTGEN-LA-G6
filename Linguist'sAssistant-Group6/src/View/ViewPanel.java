package View;

import Model.*;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

public class ViewPanel extends JPanel{
	
	private constContainer c;
	
	private JLabel noFeatures;
	private JLabel noConcept;
	
	private JPanel fPanel;
	private JPanel cPanel;
	
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
		this.fPanel = new JPanel();
		this.fPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		this.cPanel = new JPanel();
		this.cPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		this.noFeatures = new JLabel("No Feature");
		this.noFeatures.setVisible(true);
		
		this.noConcept = new JLabel("No Concept");
		this.noConcept.setVisible(true);
		
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
		//this.fTable.setTableHeader(null);
		
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
		
		//this.fPanel.add(this.noFeatures, "pushx, wrap");
		//this.cPanel.add(this.noConcept, "pushx, wrap");
		
		this.fPanel.add(this.fPane);
		this.cPanel.add(this.cPane);
		
		this.tPane.add("Details", this.dPane);
		this.tPane.add("Features", this.fPanel);
		this.tPane.add("Concepts", this.cPanel);
		
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
			//this.noFeatures.setVisible(true);
			if(!this.fPanel.getComponent(0).equals(this.noFeatures)){
				this.fPanel.add(this.noFeatures, "pushx, center, wrap", 0);
				this.fPanel.revalidate();
				this.fPanel.repaint();
			}
		}
		else{
			if(this.fPanel.getComponent(0).equals(this.noFeatures)){
				this.fPanel.remove(this.noFeatures);
				this.fPanel.revalidate();
				this.fPanel.repaint();
			}
			//this.noConcept.setVisible(false);
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
			//this.noConcept.setVisible(true);
			if(!this.cPanel.getComponent(0).equals(this.noConcept)){
				this.cPanel.add(this.noConcept, "pushx,center, wrap",0);
				this.cPanel.revalidate();
				this.cPanel.repaint();
			}
		}
		else{
			if(this.cPanel.getComponent(0).equals(this.noConcept)){
				this.cPanel.remove(this.noConcept);
				this.cPanel.revalidate();
				this.cPanel.repaint();
			}
			//this.noConcept.setVisible(false);
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
