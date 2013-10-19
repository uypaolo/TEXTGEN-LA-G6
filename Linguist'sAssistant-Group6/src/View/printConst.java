package View;

import java.util.ArrayList;

import Model.*;

public class printConst {
	
	private String features;
	private String details;
	private String concepts;
	
	public String[] print(ConstModel topConst){
		this.features = "";
		this.details = "";	
		this.concepts = "";
		display(topConst, 0);
		String[] s = {details, features, concepts};
		return s;
	}
	
	private void display(ConstModel currConst, int nest){
		ArrayList<FeatureModel> fList;
		
		System.out.println("Label: "+currConst.getLabel());
		System.out.println("Concept: "+currConst.getConcept().getName());
		
		details = details.concat("Label: "+currConst.getLabel()+"\n");
		details = details.concat("Concept: "+currConst.getConcept().getName()+"\n");
		
		concepts = concepts.concat("Concept: "+currConst.getConcept().getName()+"\n");
		
		fList = currConst.getFeatures().getFeatureList();
		System.out.println("Feratures: "+fList.size());
		
		details = details.concat("Features Count: "+fList.size()+"\n");
		
		for(int i=0; i<fList.size(); i++){
			System.out.println("Feature #"+(i+1));
			System.out.println("Name: "+fList.get(i).getName());
			System.out.println("Value: "+fList.get(i).getValue());
			System.out.println();
			
			features = features.concat("Feature #"+(i+1)+"\n");
			features = features.concat("Name: "+fList.get(i).getName()+"\n");
			features = features.concat("Value: "+fList.get(i).getValue()+"\n\n");
		}
		
		System.out.println("Subconst: "+currConst.getSubconst().getConstList().size());			
		details = details.concat("Subconst Count: "+currConst.getSubconst().getConstList().size()+"\n");	
	}	
}