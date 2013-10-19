package Model;

public class ConstModel {
	private ConstModel parent;
	private String label;
	private SubconstModel subconst;
	private FeaturesModel features;
	private ConceptModel concept;
	private TranslationModel translation;
	private int subCount;
	
	public ConstModel(ConstModel p){
		setParent(p);
		setLabel("");
		setSubconst(new SubconstModel());
		setFeatures(new FeaturesModel());
		setConcept(new ConceptModel());
		setTranslation(new TranslationModel());
		setSubCount(0);
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}

	public FeaturesModel getFeatures() {
		return features;
	}

	public void setFeatures(FeaturesModel features) {
		this.features = features;
	}

	public ConceptModel getConcept() {
		return concept;
	}

	public void setConcept(ConceptModel concept) {
		this.concept = concept;
	}

	public TranslationModel getTranslation() {
		return translation;
	}

	public void setTranslation(TranslationModel translation) {
		this.translation = translation;
	}

	public int getSubCount() {
		return subCount;
	}

	public void setSubCount(int subCount) {
		this.subCount = subCount;
	}

	public SubconstModel getSubconst() {
		return subconst;
	}

	public void setSubconst(SubconstModel subconst) {
		this.subconst = subconst;
	}

	public ConstModel getParent() {
		return parent;
	}

	public void setParent(ConstModel parent) {
		this.parent = parent;
	}

}
