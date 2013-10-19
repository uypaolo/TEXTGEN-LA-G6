package View;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class EditPanel extends JPanel{
	
	private JButton block;
	
	public EditPanel(){
		this.setLayout(new MigLayout());
		init();
	}
	
	public void init(){
		this.block = new JButton("Add Block");
		this.block.setEnabled(false);
		
		this.add(this.block, "pushx, center, wrap");
	}
	
	public void activateButton(){
		this.block.setEnabled(true);
	}

	public void setConst(constContainer c) {
		// TODO Auto-generated method stub
		
	}

	public void resetPanel() {
		// TODO Auto-generated method stub
		
	}
}
