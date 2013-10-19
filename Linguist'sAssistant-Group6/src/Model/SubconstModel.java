package Model;

import java.util.ArrayList;

public class SubconstModel {
	private ArrayList<ConstModel> ConstList;
	
	public SubconstModel(){
		setConstList(new ArrayList<ConstModel>());
	}

	public ArrayList<ConstModel> getConstList() {
		return ConstList;
	}

	public void setConstList(ArrayList<ConstModel> constList) {
		ConstList = constList;
	}
}
