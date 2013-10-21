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

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	
	private JComboBox<String> cBox;
	private JLabel cLabel;
	
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
			String host = "jdbc:mysql://localhost:3306/LA", uName = "root", pWord = "password";
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
		this.cLabel = new JLabel("Filter: ");
		
		this.cBox = new JComboBox<String>();
		this.cBox.addItem("ADJ");
		this.cBox.addItem("ADVS");
		this.cBox.addItem("CONJUNC");
		this.cBox.addItem("FEM NOUNS");
		this.cBox.addItem("MASC NOUNS");
		this.cBox.addItem("N");
		this.cBox.addItem("PRTC");
		this.cBox.addItem("PLACE NAMES");
		this.cBox.addItem("REL");
		this.cBox.addItem("V");
		
		DefaultTableModel m = new DefaultTableModel(new String[]{"Name", "Mapping"}, 0);
		this.oTable = new JTable(m);
		
		m = new DefaultTableModel(new String[]{"Stems", "Features", "Glosses"}, 0);
		this.lTable = new JTable(m);
		
		this.lPane = new JScrollPane(this.lTable);
		this.oPane = new JScrollPane(this.oTable);
		
		this.sPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		//this.sPane.setLayout(new MigLayout());
		this.sPane.add(this.oPane);
		this.sPane.add(this.lPane);	
		this.sPane.setResizeWeight(.5d);
		
		this.cont = new JPanel();
		this.cont.setLayout(new MigLayout());
		this.cont.add(this.sPane, "push, grow");
		this.cont.setPreferredSize(new Dimension(500, 500));
		this.cont.setBackground(Color.YELLOW);
		
		this.tools = new JPanel();
		this.tools.setLayout(new MigLayout());
		this.tools.setPreferredSize(new Dimension(200, 500));
		this.tools.setBackground(Color.BLUE);
		
		this.tools.add(this.cLabel, "split 2, span 2, center");
		this.tools.add(this.cBox, "wrap, pushx");
		
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
