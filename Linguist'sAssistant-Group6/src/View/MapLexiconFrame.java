package View;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

public class MapLexiconFrame extends JFrame{
	private JSplitPane sPane;
	
	private JPanel cont;
	private JPanel tools;
	
	private JPanel lex;
	private JPanel Ont;
	
	private JScrollPane lPane;
	private JScrollPane oPane;
	
	private JTable lTable;
	private JTable oTable;
	
	private JTable tList; 
	private JScrollPane scrollPane;
	
	public MapLexiconFrame(){
		this.setLayout(new MigLayout());
		init();
		setFrame();
		
		try{
			String host = "jdbc:mysql://localhost:3306/LA", uName = "root", pWord = "daniellel";
			Connection con = DriverManager.getConnection(host,uName,pWord);
			
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			String sql = null;
			
			rs = getResultSet(stmt);
			
			this.tList = new JTable(buildTableModel(rs));
			this.tList.getTableHeader().setReorderingAllowed(false);
			this.scrollPane = new JScrollPane(tList);
			this.scrollPane.setBounds(0, 0, 600, 460);
			
			this.scrollPane.setVerticalScrollBarPolicy ( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
			
			con.close();
			stmt.close();
		}
		catch(SQLException err)
		{
				JOptionPane.showMessageDialog(null, err.getMessage());
				err.printStackTrace();
		}
	}
	
	private void init(){
		
		
		this.lPane = new JScrollPane(new JTextArea("llllllllllllllllllllllllllllllllllllllllllll\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nllllllllllllllllllllllllllllllllll"));
		this.oPane = new JScrollPane(new JTextArea("llllllllllllllllllllllllllllllllllllllllllll\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nllllllllllllllllllllllllllllllllll"));
		
		this.sPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		//this.sPane.setLayout(new MigLayout());
		this.sPane.add(this.lPane);
		this.sPane.add(this.oPane);
		
		this.cont = new JPanel();
		this.cont.setLayout(new MigLayout());
		this.cont.add(this.sPane, "push, grow");
		this.cont.setPreferredSize(new Dimension(500, 500));
		this.cont.setBackground(Color.YELLOW);
		
		this.tools = new JPanel();
		this.tools.setLayout(new MigLayout());
		this.tools.setPreferredSize(new Dimension(200, 500));
		this.tools.setBackground(Color.BLUE);
		
		this.add(cont, "push, grow");
		this.add(tools, "growy");
	}
	
	private void setFrame(){
		this.setTitle("Map Lexicon");
		//this.setSize(750, 545);//640
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setBackground(Color.DARK_GRAY);
		this.pack();
	}
	
	public ResultSet getResultSet(Statement stmt){
		String sql = null;
		ResultSet rs = null;
		
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
