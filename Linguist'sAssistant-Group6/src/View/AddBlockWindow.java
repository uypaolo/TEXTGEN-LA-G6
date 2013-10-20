package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AddBlockWindow extends JPanel{
	
	private JLabel pos;
	private JLabel concept;
	private JTextField featName, featValue, featValueName;
	private JLabel name, value, valuename, application;
		
	public AddBlockWindow(final JFrame frame){
		final JButton add = new JButton("Add Block");
		final JComboBox <String> cBox = new JComboBox<String>();
		
		cBox.addItem("CL");
		cBox.addItem("NP");
		cBox.addItem("VP");
		cBox.addItem("ADJP");
		cBox.addItem("ADVP");
		cBox.addItem("N");
		cBox.addItem("ADJ");
		cBox.addItem("V");
		cBox.addItem("ADV");

		cBox.setBounds(455, 0, 20, 20);
		
		
		add(cBox);
		add.setBounds(455,0,20,20);
		add(add);
		add.setToolTipText("Add Block");
		add.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				if(e.getSource()== add)
				{	
					frame.setContentPane(new AddFeature(frame));
					frame.validate();
					frame.repaint();
				}
			}
		});

		setBackground(Color.MAGENTA);
	
	}
	
}
