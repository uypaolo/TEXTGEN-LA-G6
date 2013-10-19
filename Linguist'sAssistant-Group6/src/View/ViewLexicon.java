package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class ViewLexicon extends JPanel{
	private JButton add;
	private JComboBox <String> cBox;
	private JLabel filter;
	
	public ViewLexicon(final JFrame frame){
		cBox = new JComboBox<String>();
		cBox.setBounds(455, 0, 20, 20);
		cBox.addItem("ALL");
		cBox.addItem("CL");
		cBox.addItem("NP");
		cBox.addItem("VP");
		cBox.addItem("ADJP");
		cBox.addItem("ADVP");
		cBox.addItem("N");
		cBox.addItem("ADJ");
		cBox.addItem("V");
		cBox.addItem("ADV");
		
		filter = new JLabel("Filter:");
		filter.setBounds(455,0,20,20);
		
		add = new JButton("Add Lexicon");
		add.setBounds(455,0,20,20);
		add.setToolTipText("Add Feature");
		add.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				if(e.getSource()== add)
				{	
					frame.setContentPane(new AddLexicon(frame));
					frame.validate();
					frame.repaint();
				}
			}
		});
		
		add(add);
		add(filter);
		add(cBox);
		setBackground(Color.RED);
	}
	
	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {
			ResultSetMetaData metaData = rs.getMetaData();
			
		    // names of columns
		    Vector<String> columnNames = new Vector<String>();
		    int columnCount = metaData.getColumnCount();
		    for (int column = 1; column <= columnCount; column++) {
		        columnNames.add(metaData.getColumnName(column));
		    }

		    // data of the table
		    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		    while (rs.next()) {
		        Vector<Object> vector = new Vector<Object>();
		        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		            vector.add(rs.getObject(columnIndex));
		        }
		        data.add(vector);
		    }

		    return new DefaultTableModel(data, columnNames);

	}
}
