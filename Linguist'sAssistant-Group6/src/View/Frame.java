package View;

import Model.*;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import org.jdesktop.swingx.JXTaskPane;

import net.miginfocom.swing.MigLayout;


public class Frame extends JFrame{
	static boolean dwOpen;
	
	private boolean edit;
	
	private JMenuBar menubar;
	private JMenu file;
	private JMenu tools;
	private JMenu viewlexicon;
	private JMenuItem exit;
	private JMenuItem imp;
	private JMenuItem exp;
	private JMenuItem viewfeature;
	private JMenuItem maplexicon;
	private JMenuItem lexicon;
	
	
	private XMLFileModule xml;
	private ConstModel currXML;
	
	private JFileChooser fileChooser;
	private String source;
	private String dest;
	
	private JScrollPane mainScrollPane, gPane;	
	private JPanel mainArea;
	private JPanel rightPanel;
	private JPanel bottomPanel;
	private JPanel bottomRightPanel;
	
	private constContainer currCont;
	
	private ButtonGroup bGroup;
	private JRadioButton rEdit;
	private JRadioButton rView;
	private JButton generate, dButton, nVerse, pVerse;
	private JLabel mode, gLabel, vCount;
	
	static ViewPanel vp;
	private EditPanel ep;
	private printConst p;
	
	private CardLayout cl;
	
	private JTextArea gArea;
	
	public Frame(){
		dwOpen = false;
		this.p = new printConst();
		this.edit = false;
		this.setLayout(new MigLayout());
		initMenuBar();
		initBottomPanel();
		initRightPanel();
		initBottomRightPanel();
		initArea();
		setFrame();
	}
	
	private void setFrame(){
		this.setTitle("Linguist's Assistant");
		//this.setSize(750, 545);//640
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setBackground(Color.DARK_GRAY);
		this.pack();
	}
	
	public void initMenuBar(){
		this.currXML = new ConstModel(null);

		this.fileChooser = new JFileChooser();
		this.fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
			public String getDescription() {
				return "Extensible Markup Language (*.xml)";
			}

			@Override
			public boolean accept(File arg0) {
				if (arg0.getName().endsWith(".xml"))
					return true;
				else
					return false;
			}
		});
		fileChooser.setFileFilter(null);
		this.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		this.source = "";
		this.dest = "";
		
		this.xml = new XMLFileModule();

		this.menubar = new JMenuBar();
		
        this.file = new JMenu("File");
        this.file.setMnemonic(KeyEvent.VK_F);
        
        this.tools = new JMenu("Tools");
        this.tools.setMnemonic(KeyEvent.VK_F);

        this.exit = new JMenuItem("Exit");
        this.exit.setMnemonic(KeyEvent.VK_E);
        this.exit.setToolTipText("Exit application");
        this.exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        
        this.imp = new JMenuItem("Import");
        this.imp.setMnemonic(KeyEvent.VK_E);
        this.imp.setToolTipText("Import XML");
        this.imp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	browseFile();
            	if(source!=""){
            		loadXML();
            		/*currXML = xml.read(source);
            		currCont = genPane(currXML, new constContainer(currXML, null), 0);
            		currCont.setCollapsed(false);
            		mainArea.removeAll();
            		mainArea.add(currCont, "pushx, growx, wrap");
            		mainArea.repaint();
            		printAll(getGraphics());*/
            	}
            }
        });
        
        this.exp = new JMenuItem("Export");
        this.exp.setMnemonic(KeyEvent.VK_E);
        this.exp.setToolTipText("Export XML");
        this.exp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	expFile();
            	if(dest != ""){
            		xml.write(currXML, dest);
            	}
            	
            }
        });
        
        this.viewlexicon = new JMenu("Ontology and Lexicon");
        this.viewlexicon.setMnemonic(KeyEvent.VK_E);
        this.viewlexicon.setToolTipText("Ontology and Lexicon");
        
        this.lexicon = new JMenuItem("Lexicon");
        this.lexicon.setMnemonic(KeyEvent.VK_E);
        this.lexicon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	viewLexicon();
            }
        });
        
        this.maplexicon = new JMenuItem("Map Lexicon");
        this.maplexicon.setMnemonic(KeyEvent.VK_E);
        this.maplexicon.setToolTipText("Map Lexicon to Ontology");
        this.maplexicon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	new MapLexiconFrame();
            }
        });
        
        this.viewfeature = new JMenuItem("View Features");
        this.viewfeature.setMnemonic(KeyEvent.VK_E);
        this.viewfeature.setToolTipText("View Features");
        this.viewfeature.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	viewFeature();
            }
        });
     
        this.file.add(this.imp);
        this.file.add(this.exp);
        this.file.addSeparator();
        this.file.add(this.exit);    
        
        this.tools.add(this.viewlexicon);
        this.tools.add(this.maplexicon);
        this.tools.addSeparator();
        this.tools.add(this.viewfeature);
        
        this.viewlexicon.add(this.lexicon);
      
        this.menubar.add(this.file);
        this.menubar.add(this.tools);
        
        this.setJMenuBar(this.menubar);
	}
	
	private void initBottomRightPanel(){
		this.bottomRightPanel = new JPanel();
		this.bottomRightPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		this.bottomRightPanel.setPreferredSize(new Dimension(125, 125));
		this.bottomRightPanel.setBackground(Color.RED);
		
		this.generate = new JButton("Generate");
		this.generate.setPreferredSize(new Dimension(120, 60));
		this.generate.setEnabled(false);
		
		this.bGroup = new ButtonGroup();
		this.rEdit = new JRadioButton("Edit");
		this.rEdit.setBackground(bottomRightPanel.getBackground());
		this.rEdit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cl.show(rightPanel, "edit");
				edit = true;
			}
			
		});
		
		this.rView = new JRadioButton("View");
		this.rView.setBackground(bottomRightPanel.getBackground());
		this.rView.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cl.show(rightPanel, "view");
				edit = false;
			}
		});
		
		this.bGroup.add(rView);
		this.bGroup.add(rEdit);
		this.rView.setSelected(true);
		
		this.mode = new JLabel("Mode");
		
		this.bottomRightPanel.add(this.mode, "pushx, span, center, wrap");
		this.bottomRightPanel.add(this.rView, "split 2, span 2, center");
		this.bottomRightPanel.add(this.rEdit, "wrap");
		this.bottomRightPanel.add(this.generate, "pushx, center, span, wrap");
		//this.bottomRightPanel.add(this.mode, "span, center, wrap");
	}
	
	private void initRightPanel(){
		this.cl = new CardLayout();
		
		this.rightPanel = new JPanel();
		this.rightPanel.setLayout(cl);
		this.rightPanel.setPreferredSize(new Dimension(250, 325));
		this.rightPanel.setBackground(Color.BLUE);
		//this.rightPanel.add(new ViewPanel());
		
		this.vp = new ViewPanel();
		this.vp.setBackground(this.rightPanel.getBackground());
		this.vp.setPreferredSize(rightPanel.getSize());
		
		this.ep = new EditPanel();
		this.ep.setBackground(this.rightPanel.getBackground());
		
		this.rightPanel.add(ep, "edit");
		this.rightPanel.add(vp, "view");
		
		cl.show(rightPanel, "view");
	}
	
	public void initBottomPanel(){
		this.bottomPanel = new JPanel();
		this.bottomPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		this.bottomPanel.setPreferredSize(new Dimension(600, 170));
		this.bottomPanel.setBackground(Color.GREEN);
		
		this.gArea = new JTextArea(3,60);
		this.gArea.setEditable(false);
		
		this.gPane = new JScrollPane(this.gArea);
		
		this.dButton = new JButton("Reference");
		this.dButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!dwOpen){
					dwOpen = true;
					DocumentsWindow dw = new DocumentsWindow();
					dw.addWindowListener(new WindowListener(){

						@Override
						public void windowActivated(WindowEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void windowClosed(WindowEvent arg0) {
							// TODO Auto-generated method stub
							dwOpen = false;
							source = ((DocumentsWindow)arg0.getSource()).getCurrFile();
							if(!source.equals("")){
								loadXML();
							}
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
						
					});
					//source = dw.getCurrFile();
					//loadXML();
				}
			}
			
		});
		
		this.nVerse = new JButton("Next");
		this.pVerse = new JButton("Prev");
		this.gLabel = new JLabel("Translation: ");
		this.vCount = new JLabel("0/0");
		
		this.bottomPanel.add(this.gLabel, "right, split 3, span 3");
		this.bottomPanel.add(this.gPane, "growx ,left, span, wrap");
		this.bottomPanel.add(this.nVerse,"right, split 2, span 2");
		this.bottomPanel.add(this.vCount, "center");
		this.bottomPanel.add(this.pVerse,"left, wrap");
		this.bottomPanel.add(this.dButton);
	}
	
	private void initArea(){	
		this.mainArea = new JPanel();
		this.mainArea.setLayout(new MigLayout());
		this.mainArea.setSize(new Dimension(800, 400));
		this.mainArea.setBackground(Color.MAGENTA);
		
		this.mainScrollPane = new JScrollPane(mainArea);
		this.mainScrollPane.setPreferredSize(mainArea.getSize());
	       
        this.add(mainScrollPane, "push, grow");
        this.add(rightPanel, "growy, wrap");
        this.add(bottomPanel,"growx");
        this.add(bottomRightPanel,"grow, wrap");
  	}
	
	private void browseFile(){                                         
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			if (fileChooser.getSelectedFile().getName().endsWith(".xml")) {
				System.out.println("Valid XML file.");
				this.source = fileChooser.getSelectedFile().getAbsolutePath();
			} else {
				System.err.println("Not an XML file.");
				JOptionPane.showMessageDialog(this,
						"The file you selected was not an XML file.",
						"File Extension Mismatch", JOptionPane.ERROR_MESSAGE);
			}
		}
    }
	
	private void expFile(){
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			if (fileChooser.getSelectedFile().getAbsolutePath().endsWith(".xml")) {			
				this.dest = fileChooser.getSelectedFile().getAbsolutePath();
			}
			else {
				this.dest = fileChooser.getSelectedFile().getAbsolutePath() + ".xml";
			}
		}
	}
	
	private void viewFeature(){
    	JFrame view = new JFrame("View Feature");
    	ViewFeature viewfeature = new ViewFeature(view, "ALL");
    	view.pack();
    	view.setVisible(true);
    	view.setBounds(0,0,660,500);
    	view.setResizable(false);
    	view.getContentPane().add(viewfeature);
	}
	
	private void viewLexicon(){
    	JFrame view = new JFrame("View Lexicon");
    	ViewLexicon viewlexicon = new ViewLexicon(view);
    	view.pack();
    	view.setVisible(true);
    	view.setBounds(0,0,800,500);
    	view.setResizable(false);
    	view.getContentPane().add(viewlexicon);
	}

	
	private constContainer genPane(ConstModel currConst, constContainer tp, int index){	
		String text = "<html>";
		String s = "";
		
		if(currConst.getConcept().getName()!=""){
			tp.setTitle(currConst.getLabel()+": "+currConst.getConcept().getName());
		}
		else{
			tp.setTitle(currConst.getLabel());
		}
		tp.setCollapsed(true);
		
		ArrayList<FeatureModel> fList = currConst.getFeatures().getFeatureList();
		
		for(int n=0; n<fList.size(); n++){
			text = text.concat(fList.get(n).getName()+": "+fList.get(n).getValue()+"<br>");
		}
		
		text = text.concat("</html>");
		System.out.println(text);
		
		tp.setToolTipText(text);
		
		if(tp.getParentPane()!=null){
			tp.addMouseListener(new MouseListener(){
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					setDetails((constContainer)arg0.getSource());
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
		}
		
		ArrayList<ConstModel> tempList = currConst.getSubconst().getConstList();
		
		for(int i=0; i<tempList.size(); i++){
			tp.add(genPane(tempList.get(i), new constContainer(tempList.get(i), tp), i));
		}
		
		return tp;
	}
	
	private void setDetails(constContainer c){
		this.vp.setConst(c);
		this.ep.setConst(c);
		this.ep.activateButton();
	}
	
	public void loadXML(){
		this.currXML = xml.read(source);
		this.currCont = genPane(currXML, new constContainer(currXML, null), 0);
		this.currCont.setCollapsed(false);
		this.currCont.setSrc(source);
		this.mainArea.removeAll();
		this.mainArea.add(currCont, "pushx, growx, wrap");
		this.revalidate();
		this.mainArea.repaint();
		this.vp.resetTables();
		this.ep.resetPanel();
		this.source = "";
		//mainArea.printAll(getGraphics());
		//printAll(getGraphics());
	}
}
