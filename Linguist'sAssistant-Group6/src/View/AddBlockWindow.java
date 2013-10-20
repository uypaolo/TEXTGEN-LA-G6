package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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


import net.miginfocom.swing.MigLayout;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Model.*;


public class AddBlockWindow extends JFrame{



	private constContainer clicked;
	private ConstModel cn;
	
	private JPanel main;
	private ConstModel newBlock;
	
	private JTextField name, con;
	private JLabel pos, lname, lcon, concept;
	private JButton add;
	private JComboBox <String> cBox;
		
	public AddBlockWindow(){
		this.addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				setConstModel();
				createBlock();
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});;
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		main = new JPanel();
		main.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		pos = new JLabel("Parts of Speech: ");
		//pos.setBounds(100, 20, 150, 14);
		
		cBox = new JComboBox<String>();
		//cBox.setBounds(200, 20, 50, 20);
		cBox.addItem("CL");
		cBox.addItem("NP");
		cBox.addItem("VP");
		cBox.addItem("ADJP");
		cBox.addItem("ADVP");
		cBox.addItem("N");
		cBox.addItem("ADJ");
		cBox.addItem("V");
		cBox.addItem("ADV");
		
		cBox.setSelectedIndex(0);
		//concept = new JLabel("Concept: ");
		//concept.setBounds(150, 45, 150, 30);
		JPanel p = new JPanel();
		//p.setBounds(50, 60, 300, 100);
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Concept");
		p.setBorder(title);
		p.setLayout(new MigLayout());
		
		
		name = new JTextField(20);
		//name.setBounds(75, 20, 200, 20);
		name.requestFocus();
		name.setText("");
		name.setBackground(Color.white);
		
		lname = new JLabel("Name: ");
		lname.setBounds(20, 20, 84, 14);
		
		con = new JTextField(20);
		//con.setBounds(75, 50, 200, 20);
		con.requestFocus();
		con.setText("");
		con.setBackground(Color.white);
		
		lcon = new JLabel("Sense: ");
		//lcon.setBounds(20, 50, 84, 14);
		
		
		add = new JButton("Add Block");
		//add.setBounds(125,170,150,20);
		add.setToolTipText("Add Block");
		add.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				if(e.getSource()== add)
				{	
					
					//newBlock = new ConstModel();
					//clicked.getDetails().getSubconst().getConstList().
					dispose();
				}
			}
		});

		setBackground(Color.MAGENTA);
		
		main.add(pos, "split 1, span2, center");
		main.add(cBox, "wrap, pushx");
				
		p.add(lname, "wrap");
		p.add(name, "wrap");
		
		p.add(lcon, "wrap");
		p.add(con, "wrap");
		
		//p.add(concept);
		
		
		
		main.add(p, "wrap, span, center");
		
		main.add(add, "span, center");
		//main.setLayout(null);
		//main.setVisible(true);
		//main.setBounds(0,0,400,300);
		
		this.add(main, "grow, push");
		setFrame();
	}
	
	private void setFrame(){
		
		setVisible(true);
		this.setPreferredSize(new Dimension(400,300));
		setResizable(false);
		pack();
	}
	
	public void setConstContainer(constContainer c){
		this.clicked = c;
	}
	
	private void setConstModel(){
		this.cn = new ConstModel(this.clicked.getDetails());
		this.cn.setLabel(cBox.getSelectedItem().toString());
		if(!name.getText().equals("") && !con.getText().equals("")){
			ConceptModel cm = new ConceptModel();
			cm.setName(name.getText().equals("")+"-"+con.getText().equals(""));
			this.cn.setConcept(cm);
		}
	}
	
	private void createBlock(){
		ConstModel m = this.clicked.getDetails();
		m.getSubconst().getConstList().add(this.cn);
		
		constContainer cc = new constContainer(cn, this.clicked);
		cc.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Frame.setDetails((constContainer)arg0.getSource());
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		this.clicked.add(cc);
		this.clicked.getParent().revalidate();
		this.clicked.getParent().repaint();
	}
}
