package View;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Model.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

public class EditPanel extends JPanel{
	
	private constContainer c;
	
	private JButton addBlock;
	private JButton removeBlock;
	private JButton moveUp;
	private JButton moveDown;
	
	private JButton addFeature;
	private JButton removeFeature;
	
	private JTable features;
	
	private JPanel bPanel;
	private JPanel fPanel;
	private JLabel noFeature;
	private JTabbedPane tPane;
	
	public EditPanel(){
		this.setLayout(new MigLayout("", "[grow]", "[grow]"));
		init();
	}
	
	public void init(){
		this.noFeature = new JLabel("No Feature");
		this.noFeature.setVisible(false);
		
		this.bPanel = new JPanel();
		this.bPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		this.fPanel = new JPanel();
		this.fPanel.setLayout(new MigLayout());
		
		DefaultTableModel m = new DefaultTableModel(new Object[]{"Name", "Value"},0){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		//m.addRow(rowData);
		this.features = new JTable(m);
		
		this.moveUp = new JButton("Move Up");
		this.moveUp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				moveUp();
			}
			
		});
		this.moveUp.setEnabled(false);
		
		this.moveDown = new JButton("Move Down");
		this.moveDown.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				moveDown();
			}
			
		});
		this.moveDown.setEnabled(false);
		
		this.addBlock = new JButton("Add Block");
		this.addBlock.setEnabled(false);
		
		this.removeBlock = new JButton("Remove Block");
		this.removeBlock.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				removeBlock();
			}
			
		});
		this.removeBlock.setEnabled(false);
		
		this.addFeature = new JButton("Add Feature");
		this.addFeature.setEnabled(false);
		
		this.removeFeature = new JButton("Remove Feature");
		this.removeFeature.setEnabled(false);
		
		this.bPanel.add(this.addBlock, "pushx, center, wrap");
		this.bPanel.add(this.removeBlock, "pushx, center, wrap");
		this.bPanel.add(this.moveUp, "pushx, center, wrap");
		this.bPanel.add(this.moveDown, "pushx, center, wrap");
		
		this.fPanel.add(this.noFeature, "pushx, center, wrap, span");
		this.fPanel.add(new JScrollPane(this.features), "center, wrap, span");
		this.fPanel.add(this.addFeature, "pushx");
		this.fPanel.add(this.removeFeature, "pushx, wrap");
		
		this.tPane = new JTabbedPane();
		this.tPane.add("Block",this.bPanel);
		this.tPane.add("Feature",this.fPanel);
		
		this.add(this.tPane);
	}
	
	public void activateButton(){
		this.addBlock.setEnabled(true);
		this.removeBlock.setEnabled(true);
		this.addFeature.setEnabled(true);
		this.removeFeature.setEnabled(true);
		this.moveUp.setEnabled(true);
		this.moveDown.setEnabled(true);
	}
	
	public void deactivateButton(){
		this.addBlock.setEnabled(false);
		this.removeBlock.setEnabled(false);
		this.addFeature.setEnabled(false);
		this.removeFeature.setEnabled(false);
		this.moveUp.setEnabled(false);
		this.moveDown.setEnabled(false);
	}
	
	private void resetTable(){
		DefaultTableModel m = (DefaultTableModel) this.features.getModel();
		m.getDataVector().removeAllElements();
		m.fireTableDataChanged();
	}
	
	public void resetPanel(){
		deactivateButton();
		resetTable();
	}
	
	private void setFeatures(ArrayList<FeatureModel> fList){
		
		
		if(fList.size()<1){
			this.noFeature.setText("No Feature");
			this.noFeature.setVisible(true);
		}
		else{
			/*this.noFeature.setText("Features: "+fList.size());
			this.noFeature.setVisible(true);*/
			this.noFeature.setVisible(false);
		}
		
		
		DefaultTableModel m = (DefaultTableModel) this.features.getModel();
		m.getDataVector().removeAllElements();
		for(FeatureModel f: fList){		
			m.addRow(new String[]{f.getName(), f.getValue()});
		}
		
		m.fireTableDataChanged();
	}
	
	public void setConst(constContainer cn){
		this.c = cn;
		setFeatures(c.getDetails().getFeatures().getFeatureList());
	}
	
	private void moveUp(){
		Container p = c.getParent();
		int i;
		for(i=0; p.getComponent(i)!=c; i++);
		if(i!=0){
			p.remove(c);
			p.add(c, i-1);
			p.revalidate();
			p.repaint();
			ConstModel cm = c.getDetails();
			ConstModel pm = cm.getParent();
			pm.getSubconst().getConstList().remove(cm);
			pm.getSubconst().getConstList().add(i-1, cm);
		}
	}
	
	private void moveDown(){
		Container p = c.getParent();
		int i;
		for(i=0; p.getComponent(i)!=c; i++);
		if(i<p.getComponentCount()-1){
			p.remove(c);
			p.add(c, i+1);
			p.revalidate();
			p.repaint();
			ConstModel cm = c.getDetails();
			ConstModel pm = cm.getParent();
			pm.getSubconst().getConstList().remove(cm);
			pm.getSubconst().getConstList().add(i+1, cm);
		}
	}

	private void removeBlock(){
		Container p = c.getParent();
		p.remove(c);
		p.revalidate();
		p.repaint();
		ConstModel cm = c.getDetails();
		ConstModel pm = cm.getParent();
		pm.getSubconst().getConstList().remove(cm);
		resetPanel();
		Frame.vp.resetPanels();
	}
}
