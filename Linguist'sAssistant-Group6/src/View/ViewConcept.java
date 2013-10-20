package View;

import java.awt.Color;
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

public class ViewConcept extends JPanel{
	private JButton add;
	private JComboBox <String> cBox;
	private JLabel filter;
	private JTable tList; 
	private JScrollPane sPane;
	
	public ViewConcept(final JFrame frame, String check){
		filter = new JLabel("Filter:");
		filter.setBounds(455,0,20,20);
		
		cBox = new JComboBox<String>();
		cBox.setBounds(455, 0, 20, 20);
		cBox.addItem("ADJ");
		cBox.addItem("ADVS");
		cBox.addItem("CONJUNC");
		cBox.addItem("FEM NOUNS");
		cBox.addItem("MASC NOUNS");
		cBox.addItem("N");
		cBox.addItem("PRTC");
		cBox.addItem("PLACE NAMES");
		cBox.addItem("REL");
		cBox.addItem("V");
		
		cBox.setBounds(455, 0, 20, 20);
		cBox.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {
		    	UpdateTable(cBox.getSelectedItem().toString(), tList);
		    }
		});
		
		try{
			String host = "jdbc:mysql://localhost:3306/LA", uName = "root", pWord = "nitsujgarcia";
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
				
		add = new JButton("Add Concept");
		add.setBounds(455,0,20,20);
		add.setToolTipText("Add Concept");
		add.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				if(e.getSource()== add)
				{	
					frame.setContentPane(new AddConcept(frame));
					frame.validate();
					frame.repaint();
				}
			}
		});
		
		add(add);
		add(filter);
		add(cBox);
		add(sPane);
		setBackground(Color.RED);
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
		
		if(check.equals("ADJ")){
			sql = "SELECT name, definition FROM adj"; 
		}
		else if(check.equals("ADVS")){
			sql = "SELECT name, definition FROM ADVS"; 
		}
		else if(check.equals("CONJUNC")){
			sql = "SELECT name, definition FROM CONJUNCTIONS"; 
		}
		else if(check.equals("FEM NOUNS")){
			sql = "SELECT name, definition FROM FEMININENOUNS"; 
		}
		else if(check.equals("MASC NOUNS")){
			sql = "SELECT name, definition FROM MASCULINENOUNS"; 
		}
		else if(check.equals("N")){
			sql = "SELECT name, definition FROM NOUNS"; 
		}
		else if(check.equals("PRTC")){
			sql = "SELECT name, definition FROM PARTICLES"; 
		}
		else if(check.equals("PLACE NAMES")){
			sql = "SELECT name, definition FROM PLACENAMES"; 
		}
		else if(check.equals("REL")){
			sql = "SELECT name, definition FROM RELATIONS"; 
		}
		else if(check.equals("V")){
			sql = "SELECT name, definition FROM VERBS"; 
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
