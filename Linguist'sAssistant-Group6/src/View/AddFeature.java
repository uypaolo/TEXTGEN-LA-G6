package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddFeature extends JPanel implements ItemListener, ActionListener{
	private JTextField featName, featValue, featValueName;
	private JLabel name, value, valuename, application;
	private JCheckBox CL, NP, VP, ADJP, ADVP, N, V, ADJ, ADV;
	private JButton addFeature, cancel;
	
	public AddFeature(final JFrame frame){
		CL = new JCheckBox("CL");
		CL.setMnemonic(KeyEvent.VK_C);
		CL.setSelected(false);
		
		NP = new JCheckBox("NP");
		NP.setMnemonic(KeyEvent.VK_C);
		NP.setSelected(false);
		
		VP = new JCheckBox("VP");
		VP.setMnemonic(KeyEvent.VK_C);
		VP.setSelected(false);
		
		ADJP = new JCheckBox("ADJP");
		ADJP.setMnemonic(KeyEvent.VK_C);
		ADJP.setSelected(false);
		
		ADVP = new JCheckBox("ADVP");
		ADVP.setMnemonic(KeyEvent.VK_C);
		ADVP.setSelected(false);
		
		N = new JCheckBox("N");
		N.setMnemonic(KeyEvent.VK_C);
		N.setSelected(false);
		
		ADJ = new JCheckBox("ADJ");
		ADJ.setMnemonic(KeyEvent.VK_C);
		ADJ.setSelected(false);
		
		V = new JCheckBox("V");
		V.setMnemonic(KeyEvent.VK_C);
		V.setSelected(false);
		
		ADV = new JCheckBox("ADV");
		ADV.setMnemonic(KeyEvent.VK_C);
		ADV.setSelected(false);
		
		CL.addItemListener(this);
		NP.addItemListener(this);
		VP.addItemListener(this);
		ADJP.addItemListener(this);
		
		ADVP.addItemListener(this);
		N.addItemListener(this);
		ADJ.addItemListener(this);
		V.addItemListener(this);
		ADV.addItemListener(this);
		
		JPanel checkPanel = new JPanel(new GridLayout(0, 2));
        checkPanel.add(CL);
        checkPanel.add(NP);
        checkPanel.add(VP);
        checkPanel.add(ADJP);
        
        checkPanel.add(ADVP);
        checkPanel.add(N);
        checkPanel.add(ADJ);
        checkPanel.add(V);
        checkPanel.add(ADV);

        add(checkPanel, BorderLayout.LINE_START);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        checkPanel.setBounds(265, 150, 150, 150);
		
		name = new JLabel("Name: ");
		name.setBounds(175, 60, 84, 14);
		
		featName = new JTextField(10);
		featName.setBounds(215, 60, 250, 20);
		featName.requestFocus();
		featName.setBackground(Color.white);
		
		value = new JLabel("Value: ");
		value.setBounds(175, 90, 84, 14);
		
		featValue = new JTextField(10);
		featValue.setBounds(215, 90, 250, 20);
		featValue.requestFocus();
		featValue.setBackground(Color.white);
		
		valuename = new JLabel("Value Name: ");
		valuename.setBounds(140, 120, 84, 14);
		
		featValueName = new JTextField(10);
		featValueName.setBounds(215, 120, 250, 20);
		featValueName.requestFocus();
		featValueName.setBackground(Color.white);
		
		application = new JLabel("Application: ");
		application.setBounds(140, 150, 84, 14);
		
		addFeature = new JButton("Add Feature");
		addFeature.setBounds(265,350,150,20);
		addFeature.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource() == addFeature)
				{	
					try{
	     				String host = "jdbc:mysql://localhost:3306/LA", uName = "root", pWord = "nitsujgarcia";
	     				Connection con = DriverManager.getConnection(host, uName, pWord);
						String sql = null;
	     				Statement stmt = con.createStatement();

	     					if(CL.isSelected()){
	    	     				sql = "INSERT INTO feature (idfeature,name,value,valuename,application) " +
	    	     						"VALUES (null, '" + featName.getText() + "',' " + featValue.getText() + "', '" + featValueName.getText() +"', 'CL')";
	    	     				stmt.executeUpdate(sql);
	     					}
	     					if(NP.isSelected()){
	    	     				sql = "INSERT INTO feature (idfeature,name,value,valuename,application) " +
	    	     						"VALUES (null, '" + featName.getText() + "',' " + featValue.getText() + "', '" + featValueName.getText() +"', 'NP')";
	    	     				stmt.executeUpdate(sql);
	     					}
	     					if(VP.isSelected()){
	    	     				sql = "INSERT INTO feature (idfeature,name,value,valuename,application) " +
	    	     						"VALUES (null, '" + featName.getText() + "',' " + featValue.getText() + "', '" + featValueName.getText() +"', 'VP')";
	    	     				stmt.executeUpdate(sql);
	     					}
	     					if(ADJP.isSelected()){
	    	     				sql = "INSERT INTO feature (idfeature,name,value,valuename,application) " +
	    	     						"VALUES (null, '" + featName.getText() + "',' " + featValue.getText() + "', '" + featValueName.getText() +"', 'ADJP')";
	    	     				stmt.executeUpdate(sql);
	     					}
	     					if(ADVP.isSelected()){
	    	     				sql = "INSERT INTO feature (idfeature,name,value,valuename,application) " +
	    	     						"VALUES (null, '" + featName.getText() + "',' " + featValue.getText() + "', '" + featValueName.getText() +"', 'ADVP')";
	    	     				stmt.executeUpdate(sql);
	     					}
	     					if(N.isSelected()){
	    	     				sql = "INSERT INTO feature (idfeature,name,value,valuename,application) " +
	    	     						"VALUES (null, '" + featName.getText() + "',' " + featValue.getText() + "', '" + featValueName.getText() +"', 'N')";
	    	     				stmt.executeUpdate(sql);
	     					}
	     					if(ADJ.isSelected()){
	    	     				sql = "INSERT INTO feature (idfeature,name,value,valuename,application) " +
	    	     						"VALUES (null, '" + featName.getText() + "',' " + featValue.getText() + "', '" + featValueName.getText() +"', 'ADJ')";
	    	     				stmt.executeUpdate(sql);
	     					}
	     					if(V.isSelected()){
	    	     				sql = "INSERT INTO feature (idfeature,name,value,valuename,application) " +
	    	     						"VALUES (null, '" + featName.getText() + "',' " + featValue.getText() + "', '" + featValueName.getText() +"', 'V')";
	    	     				stmt.executeUpdate(sql);
	     					}
	     					if(ADV.isSelected()){
	    	     				sql = "INSERT INTO feature (idfeature,name,value,valuename,application) " +
	    	     						"VALUES (null, '" + featName.getText() + "',' " + featValue.getText() + "', '" + featValueName.getText() +"', 'ADV')";
	    	     				stmt.executeUpdate(sql);
	     					}
	     				
	     				stmt.close();
						con.close();
						
	     			}catch(SQLException err){
	     				JOptionPane.showMessageDialog(null, err.getMessage());
	     				err.printStackTrace();
	     				}
					JOptionPane.showMessageDialog(null, "Feature added!");
					frame.setContentPane(new ViewFeature(frame, "ALL"));
					frame.revalidate();
					frame.repaint();
				}
			}
		}); 
		
		cancel = new JButton("Cancel");
		cancel.setBounds(265,380,150,20);
		cancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				if(e.getSource()== cancel)
				{
					frame.setContentPane(new ViewFeature(frame, "ALL"));
					frame.validate();
					frame.repaint();
				}
			}
		});
		
		add(addFeature);
		add(cancel);
		add(name);
		add(featName);
		add(value);
		add(featValue);
		add(valuename);
		add(featValueName);
		add(application);
		setLayout(null);
		setVisible(true);
		setBackground(Color.MAGENTA);
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}
