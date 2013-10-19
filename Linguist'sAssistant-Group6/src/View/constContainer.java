package View;

import Model.*;
import org.jdesktop.swingx.JXTaskPane; 

public class constContainer extends JXTaskPane{
	
	private String src;
	private constContainer parent;
	private ConstModel details;
	
	public constContainer(ConstModel con, constContainer p){
		this.src = "";
		this.details = con;
		this.parent = p;
		this.setTitle(con.getLabel());
	}
	
	public ConstModel getDetails(){
		return this.details;
	}
	
	public constContainer getParentPane(){
		return this.parent;
	}
	
	public void setSrc(String s){
		this.src = s;
	}
	
	public String setSrc(){
		return this.src;
	}
}
