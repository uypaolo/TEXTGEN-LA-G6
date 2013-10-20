package View;

import java.awt.BorderLayout;
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Model.ConstModel;

public class AddBlockWindow extends JPanel{
	
	private constContainer clicked;
	private ConstModel newBlock;
	
	private JTextField name, con;
	private JLabel pos, lname, lcon, concept;
	private JButton add;
	private JComboBox <String> cBox;
		
	public AddBlockWindow(final JFrame frame){
		
		pos = new JLabel("Parts of Speech: ");
		pos.setBounds(100, 20, 150, 14);
		
		cBox = new JComboBox<String>();
		cBox.setBounds(200, 20, 50, 20);
		cBox.addItem("CL");
		cBox.addItem("NP");
		cBox.addItem("VP");
		cBox.addItem("ADJP");
		cBox.addItem("ADVP");
		cBox.addItem("N");
		cBox.addItem("ADJ");
		cBox.addItem("V");
		cBox.addItem("ADV");
		
		//concept = new JLabel("Concept: ");
		//concept.setBounds(150, 45, 150, 30);
		JPanel p = new JPanel();
		p.setBounds(50, 60, 300, 100);
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Concept");
		p.setBorder(title);
		p.setLayout(null);
		
		
		name = new JTextField(10);
		name.setBounds(75, 20, 200, 20);
		name.requestFocus();
		name.setBackground(Color.white);
		
		lname = new JLabel("Name: ");
		lname.setBounds(20, 20, 84, 14);
		
		con = new JTextField(10);
		con.setBounds(75, 50, 200, 20);
		con.requestFocus();
		con.setBackground(Color.white);
		
		lcon = new JLabel("Sense: ");
		lcon.setBounds(20, 50, 84, 14);
		
		
		add = new JButton("Add Block");
		add.setBounds(125,170,150,20);
		add.setToolTipText("Add Block");
		add.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				if(e.getSource()== add)
				{	
					
					//newBlock = new ConstModel();
					//clicked.getDetails().getSubconst().getConstList().
				}
			}
		});

		setBackground(Color.MAGENTA);
		
		add(pos);
		add(cBox);
		p.add(name);
		p.add(lname);
		//p.add(concept);
		
		
		p.add(con);
		p.add(lcon);
		add(p);
		
		add(add);
		setLayout(null);
		setVisible(true);
	}	
	
	public void setConstContainer(constContainer c){
		this.clicked = c;
	}
	
}
