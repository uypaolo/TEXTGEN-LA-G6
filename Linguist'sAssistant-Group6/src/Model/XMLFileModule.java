package Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLFileModule {
	
	private File file;
	private DocumentBuilderFactory dbFactory;
	private DocumentBuilder dBuilder;
	private Document doc;
	private ConstModel constModel;
	
	public ConstModel read(String source){
		constModel = new ConstModel(null);
		
		try{
			file = new File(source);
			dbFactory = DocumentBuilderFactory.newInstance();			
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			
			//System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
			constModel = toConst(null, doc.getElementsByTagName("const").item(0), 0);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return constModel;
	}
	
	private ConstModel toConst(ConstModel p, Node currNode, int nest){
		NodeList tempList, subConst;
		Node tempNode;
		
		ArrayList<ConstModel> constList = new ArrayList<ConstModel>();
		ArrayList<ConstModel> subList;
		ConstModel tempConst;
		ConceptModel tempConcept;
		SubconstModel tempSub;
	
		String tag;
		
		if(currNode.getNodeType() == Node.ELEMENT_NODE){
			subList = new ArrayList<ConstModel>();
			
			
			tempConst = new ConstModel(p);
				
			tempList = currNode.getChildNodes();
			
			//System.out.println(tempList.getLength());
			for(int j=0; j<tempList.getLength();j++){
				
				tempNode = tempList.item(j);
				tag = tempNode.getNodeName().trim();
					
				//System.out.println(tag);
					
				if(tag.equals("#text")){
					//System.out.println("\t"+tempNode.getNodeName());
				}
				else if(tag.equals("label")){
					//System.out.println(tempNode.getTextContent());
					tempConst.setLabel(tempNode.getTextContent().trim());
				}
				else if(tag.equals("concept")){
					tempConcept = new ConceptModel();
					//System.out.println(tempNode.getTextContent().trim());
					tempConcept.setName(tempNode.getTextContent().trim());
					tempConst.setConcept(tempConcept);
				}
				else if(tag.equals("features")){
					
					tempConst.setFeatures(getFeatureList(tempNode.getChildNodes()));
				}
				else if(tag.equals("subconst")){
					tempConst.setSubCount(tempConst.getSubCount()+1);
					subConst = tempNode.getChildNodes();
					
					//System.out.println(tempConst.getLabel()+"     "+subConst.getLength()+"   "+tempConst.getSubCount()+"      "+nest);
					for(int k=0; k<subConst.getLength(); k++){
						//System.out.println("----------------------------"+k+"   "+subConst.item(k).getNodeName().trim());
						if(!subConst.item(k).getNodeName().trim().equals("#text")){
							subList.add(toConst(tempConst, subConst.item(k), nest+1));
							//System.out.println("*************"+subList.size());
						}
					}
					
					
				}
				
			}
			tempSub = new SubconstModel();
			tempSub.setConstList(subList);
			tempConst.setSubconst(tempSub);
			
			constList.add(tempConst);
			return tempConst;
		}			
		//linkConst(constList, constList.get(0), 1);
		return null;
	}
	
	private FeaturesModel getFeatureList(NodeList nodes){
		Node featNode;
		ArrayList<FeatureModel> List;
		FeaturesModel tempFList = new FeaturesModel();
		FeatureModel feature;
		
		tempFList = new FeaturesModel();
		List = new ArrayList<FeatureModel>();
		Element el;
		for(int k=0; k<nodes.getLength(); k++){
			featNode = nodes.item(k);						
			if(!featNode.getNodeName().trim().equals("#text")){
				feature = new FeatureModel();
				el = (Element)featNode;
				//System.out.println("---------------------------"+el.getElementsByTagName("name").item(0).getTextContent().trim());
				//System.out.println("---------------------------"+el.getElementsByTagName("value").item(0).getTextContent().trim());
				feature.setName(el.getElementsByTagName("name").item(0).getTextContent().trim());
				feature.setValue(el.getElementsByTagName("value").item(0).getTextContent().trim());
				List.add(feature);
			}
		}
		
		tempFList.setFeatureList(List);
		
		return tempFList;
	}
	
	public void write(ConstModel model, String dest){
		
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			
			doc.appendChild(getElements(model, doc));
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			
			StreamResult result = new StreamResult(new File(dest));
			
			//StreamResult result = new StreamResult(System.out);
			 
			transformer.transform(source, result);
			
	 
			System.out.println("File saved!");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private Element getElements(ConstModel currConst, Document doc){
		Element root = doc.createElement("const");
		
		ArrayList<FeatureModel> fList;
		ArrayList<ConstModel> cList;
		
		Element label = doc.createElement("label");
		label.appendChild(doc.createTextNode(currConst.getLabel()));
		root.appendChild(label);
		
		if(!currConst.getConcept().getName().trim().equals("")){
			Element concept = doc.createElement("concept");
			concept.appendChild(doc.createTextNode(currConst.getConcept().getName()));
			root.appendChild(concept);
		}
	
		fList = currConst.getFeatures().getFeatureList();
		
		if(fList.size()>0){
			Element features = doc.createElement("features");
			root.appendChild(features);
		
			for(int i=0; i<fList.size(); i++){
				Element feature = doc.createElement("feature");
				features.appendChild(feature);
				
				Element name = doc.createElement("name");
				name.appendChild(doc.createTextNode(fList.get(i).getName()));
				feature.appendChild(name);
				
				Element value = doc.createElement("value");
				value.appendChild(doc.createTextNode(fList.get(i).getValue()));
				feature.appendChild(value);
			}
		}
		
		cList = currConst.getSubconst().getConstList();

		Element subconst;

		for(int j=0; j<cList.size(); j++){
			subconst = doc.createElement("subconst");
			root.appendChild(subconst);
			
			subconst.appendChild(getElements(cList.get(j), doc));
		}
		
		return root;
	}
}
