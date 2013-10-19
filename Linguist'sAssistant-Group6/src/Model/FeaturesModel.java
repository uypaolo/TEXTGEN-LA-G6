package Model;

import java.util.ArrayList;

public class FeaturesModel {
	private ArrayList<FeatureModel> featureList;
	
	public FeaturesModel(){
		setFeatureList(new ArrayList<FeatureModel>());
	}

	public ArrayList<FeatureModel> getFeatureList() {
		return featureList;
	}

	public void setFeatureList(ArrayList<FeatureModel> featureList) {
		this.featureList = featureList;
	}
}
