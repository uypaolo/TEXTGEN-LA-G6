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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddLexicon extends JPanel implements ItemListener, ActionListener{
	private JTextField lexWord, lexDefinition;
	private JLabel word, definition, application;
	private JCheckBox CL, NP, VP, ADJP, ADVP, N, V, ADJ, ADV;
	private JButton addLexicon, cancel;
	
	public AddLexicon(final JFrame frame){
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
        checkPanel.setBounds(330, 130, 150, 150);
		
		word = new JLabel("Word: ");
		word.setBounds(245, 60, 84, 14);
		
		lexWord = new JTextField(10);
		lexWord.setBounds(285, 60, 250, 20);
		lexWord.requestFocus();
		lexWord.setBackground(Color.white);
		
		definition = new JLabel("Definiton: ");
		definition.setBounds(225, 90, 84, 14);
		
		lexDefinition = new JTextField(10);
		lexDefinition.setBounds(285, 90, 250, 20);
		lexDefinition.requestFocus();
		lexDefinition.setBackground(Color.white);
		
		application = new JLabel("Application: ");
		application.setBounds(212, 120, 84, 14);
		
		addLexicon = new JButton("Add Word");
		addLexicon.setBounds(330,340,150,20);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(330,370,150,20);
		cancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				if(e.getSource()== cancel)
				{
					frame.setContentPane(new ViewLexicon(frame));
					frame.validate();
					frame.repaint();
				}
			}
		});
		
		add(addLexicon);
		add(cancel);
		add(word);
		add(lexWord);
		add(definition);
		add(lexDefinition);
		add(application);
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
