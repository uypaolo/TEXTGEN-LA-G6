package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class AddConcept extends JPanel implements ItemListener, ActionListener{
	private JTextField Word, Definition;
	private JLabel cword, cdefinition, capplication;
	private JCheckBox ADJ, ADVS, CONJUNC, FEMNOUNS, MASCNOUNS, N, PRTC, PLACENAMES, REL, V;
	private JButton add, cancel;
	
	public AddConcept(final JFrame frame){
		ADJ = new JCheckBox("ADJ");
		ADJ.setMnemonic(KeyEvent.VK_C);
		ADJ.setSelected(false);
		
		ADVS = new JCheckBox("ADVS");
		ADVS.setMnemonic(KeyEvent.VK_C);
		ADVS.setSelected(false);
		
		CONJUNC = new JCheckBox("CONJUNC");
		CONJUNC.setMnemonic(KeyEvent.VK_C);
		CONJUNC.setSelected(false);
		
		FEMNOUNS = new JCheckBox("FEM NOUNS");
		FEMNOUNS.setMnemonic(KeyEvent.VK_C);
		FEMNOUNS.setSelected(false);
		
		MASCNOUNS = new JCheckBox("MASC NOUNS");
		MASCNOUNS.setMnemonic(KeyEvent.VK_C);
		MASCNOUNS.setSelected(false);
		
		N = new JCheckBox("N");
		N.setMnemonic(KeyEvent.VK_C);
		N.setSelected(false);
		
		PRTC = new JCheckBox("PRTC");
		PRTC.setMnemonic(KeyEvent.VK_C);
		PRTC.setSelected(false);
		
		V = new JCheckBox("V");
		V.setMnemonic(KeyEvent.VK_C);
		V.setSelected(false);
		
		REL = new JCheckBox("REL");
		REL.setMnemonic(KeyEvent.VK_C);
		REL.setSelected(false);
		
		PLACENAMES = new JCheckBox("PLACE NAMES");
		PLACENAMES.setMnemonic(KeyEvent.VK_C);
		PLACENAMES.setSelected(false);
		
		ADJ.addItemListener(this);
		ADVS.addItemListener(this);
		CONJUNC.addItemListener(this);
		FEMNOUNS.addItemListener(this);
		
		MASCNOUNS.addItemListener(this);
		N.addItemListener(this);
		PRTC.addItemListener(this);
		V.addItemListener(this);
		REL.addItemListener(this);
		PLACENAMES.addItemListener(this);
		
		JPanel checkPanel = new JPanel(new GridLayout(0, 2));
		checkPanel.add(ADJ);
		checkPanel.add(ADVS);
        checkPanel.add(CONJUNC);
        checkPanel.add(FEMNOUNS);
        checkPanel.add(MASCNOUNS);
        checkPanel.add(PRTC);
        checkPanel.add(N);
        checkPanel.add(REL);
        checkPanel.add(V);
        checkPanel.add(PLACENAMES);

        add(checkPanel, BorderLayout.LINE_START);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        checkPanel.setBounds(295, 130, 230, 150);
		
		cword = new JLabel("Word: ");
		cword.setBounds(245, 60, 84, 14);
		
		Word = new JTextField(10);
		Word.setBounds(285, 60, 250, 20);
		Word.requestFocus();
		Word.setBackground(Color.white);
		
		cdefinition = new JLabel("Definiton: ");
		cdefinition.setBounds(225, 90, 84, 14);
		
		Definition = new JTextField(10);
		Definition.setBounds(285, 90, 250, 20);
		Definition.requestFocus();
		Definition.setBackground(Color.white);
		
		capplication = new JLabel("Application: ");
		capplication.setBounds(212, 120, 84, 14);
		
		add = new JButton("Add Word");
		add.setBounds(330,340,150,20);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(330,370,150,20);
		cancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				if(e.getSource()== cancel)
				{
					frame.setContentPane(new ViewConcept(frame, "ADJ"));
					frame.validate();
					frame.repaint();
				}
			}
		});
		
		add(add);
		add(cancel);
		add(cword);
		add(Word);
		add(cdefinition);
		add(Definition);
		add(capplication);
		setLayout(null);
		setVisible(true);
		setBackground(Color.RED);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
