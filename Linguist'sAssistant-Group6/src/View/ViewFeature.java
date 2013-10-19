package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class ViewFeature extends JPanel{
	
	private JTable tList; 
	private JScrollPane sPane;
	private JLabel filter;
	
	
	public ViewFeature(final JFrame frame, String check){
		
		final JButton add = new JButton("Add Feature");
		final JComboBox <String> cBox = new JComboBox<String>();
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
		
		try{
			String host = "jdbc:mysql://localhost:3306/LA", uName = "root", pWord = "daniellel";
			Connection con = DriverManager.getConnection(host,uName,pWord);
			
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			String sql = null;
			
			rs = getResultSet(check, stmt);
			
			tList = new JTable(buildTableModel(rs));
			tList.getTableHeader().setReorderingAllowed(false);
			sPane = new JScrollPane(tList);
			sPane.setBounds(0, 0, 600, 460);
			
			sPane.setVerticalScrollBarPolicy ( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
			
			con.close();
			stmt.close();
		}
		catch(SQLException err)
		{
				JOptionPane.showMessageDialog(null, err.getMessage());
				err.printStackTrace();
		}
		
		add(sPane);	
		
		filter = new JLabel("Filter:");
		filter.setBounds(455,0,20,20);
		add(filter);
		
		cBox.setBounds(455, 0, 20, 20);
		cBox.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {
		    	UpdateTable(cBox.getSelectedItem().toString(), tList);
		    }
		});
		
		add(cBox);
		add.setBounds(455,0,20,20);
		add(add);
		add.setToolTipText("Add Feature");
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
	
	public void UpdateTable(String check, JTable tList){
		
		String host = "jdbc:mysql://localhost:3306/LA", uName = "root", pWord = "nitsujgarcia";
		Connection con;
		try {
			con = DriverManager.getConnection(host,uName,pWord);
	
			Statement stmt = con.createStatement();
			
			ResultSet rs = getResultSet(check, stmt);
			
			DefaultTableModel model = (DefaultTableModel)tList.getModel();
			model.getDataVector().removeAllElements();
			tList.setModel(buildTableModel(rs));
			//model = buildTableModel(rs);
			model.fireTableDataChanged();
			
			con.close();
			stmt.close();
		
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet getResultSet(String check, Statement stmt){
		String sql = null;
		ResultSet rs = null;
		
		if(check.equals("ALL")){
			sql = "SELECT name, value, valuename, application from feature";
		}
		else{
			sql = "SELECT name, value, valuename, application FROM feature WHERE application = '"+ check + "' ;";
		}
		
		
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
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
