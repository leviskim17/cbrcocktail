package nus.iss.cbr.cocktail.xml2linear.xml;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class XMLParser {
	
	public void parse(String filePath) {
		try {
			File xmlFile = new File(filePath);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xmlFile);
			doc.getDocumentElement().normalize();
			 
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			System.out.println("-----------------------");
			
			NodeList recipeLst = doc.getElementsByTagName("recipe");
			for (int recipeIdx = 0; recipeIdx < recipeLst.getLength(); recipeIdx++) {
				Node recipeNode = recipeLst.item(recipeIdx);
				if (recipeNode.getNodeType() == Node.ELEMENT_NODE) {
					Element recipeElmt = (Element) recipeNode;
					System.out.println("title : " + getElementValue("title", recipeElmt));
					
					NodeList ingrediantLst = recipeElmt.getElementsByTagName("ingredients").item(0).getChildNodes();
					for (int ingrediantIdx = 0; ingrediantIdx < ingrediantLst.getLength(); ingrediantIdx++) {
						Node ingrediantNode = ingrediantLst.item(ingrediantIdx);
						if (ingrediantNode.getNodeType() == Node.ELEMENT_NODE) {
							Element ingrediantElmt = (Element)ingrediantNode;
							
							System.out.println("	food : " + getAttributeValue("food", ingrediantElmt));
							System.out.println("	unit : " + getAttributeValue("unit", ingrediantElmt));
							System.out.println("	quantity : " + getAttributeValue("quantity", ingrediantElmt));
							System.out.println("	ingredient : " + getElementValue(ingrediantElmt));
						}
					}
					
					NodeList stepLst = recipeElmt.getElementsByTagName("preparation").item(0).getChildNodes();
					for (int stepIdx = 0; stepIdx < stepLst.getLength(); stepIdx++) {
						Node stepNode = stepLst.item(stepIdx);
						if (stepNode.getNodeType() == Node.ELEMENT_NODE) {
							Element stepElmt = (Element)stepNode;
							System.out.println("	step : " + getElementValue(stepElmt));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generateType1(String xmlFilePath, String txtFilePath) {
		try {
			File xmlFile = new File(xmlFilePath);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			BufferedWriter out = new BufferedWriter(new FileWriter(txtFilePath));
			String buffer = "";
			
			buffer += "#CocktailPlainTextCaseBase";
			out.write(buffer); 
			out.newLine();
			
			buffer += "#caseId:title:food(repetitive):unit(repetitive):quantity(repetitive):ingredient(repetitive):step(repetitive)";
			out.write(buffer); 
			out.newLine();
			out.newLine();

			NodeList recipeLst = doc.getElementsByTagName("recipe");
			for (int recipeIdx = 0; recipeIdx < recipeLst.getLength(); recipeIdx++) {
				buffer = "case" + (recipeIdx + 1) + ":";
				
				Node recipeNode = recipeLst.item(recipeIdx);
				if (recipeNode.getNodeType() == Node.ELEMENT_NODE) {
					Element recipeElmt = (Element) recipeNode;
					//System.out.println("title : " + getElementValue("title", recipeElmt));
					buffer += getElementValue("title", recipeElmt) + ":";
					
					NodeList ingrediantLst = recipeElmt.getElementsByTagName("ingredients").item(0).getChildNodes();
					for (int ingrediantIdx = 0; ingrediantIdx < ingrediantLst.getLength(); ingrediantIdx++) {
						Node ingrediantNode = ingrediantLst.item(ingrediantIdx);
						if (ingrediantNode.getNodeType() == Node.ELEMENT_NODE) {
							Element ingrediantElmt = (Element)ingrediantNode;
							
							buffer += getAttributeValue("food", ingrediantElmt) + ":";
							buffer += getAttributeValue("unit", ingrediantElmt) + ":";
							buffer += getAttributeValue("quantity", ingrediantElmt) + ":";
							buffer += getElementValue(ingrediantElmt) + ":";
							
							//System.out.println("	food : " + getAttributeValue("food", ingrediantElmt));
							//System.out.println("	unit : " + getAttributeValue("unit", ingrediantElmt));
							//System.out.println("	quantity : " + getAttributeValue("quantity", ingrediantElmt));
							//System.out.println("	ingredient : " + getElementValue(ingrediantElmt));
						}
					}
					
					NodeList stepLst = recipeElmt.getElementsByTagName("preparation").item(0).getChildNodes();
					for (int stepIdx = 0; stepIdx < stepLst.getLength(); stepIdx++) {
						Node stepNode = stepLst.item(stepIdx);
						if (stepNode.getNodeType() == Node.ELEMENT_NODE) {
							Element stepElmt = (Element)stepNode;
							
							buffer += getElementValue(stepElmt) + ":";
							//System.out.println("	step : " + getElementValue(stepElmt));
						}
					}
				}
				
				out.write(buffer); 
				out.newLine();
			}
			
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generateType2(String xmlFilePath, String txtFilePath) {
		try {
			File xmlFile = new File(xmlFilePath);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			BufferedWriter out = new BufferedWriter(new FileWriter(txtFilePath));
			String buffer = "";
			
			buffer += "#CocktailPlainTextCaseBase";
			out.write(buffer); 
			out.newLine();
			
			buffer += "#caseId;title,ingredient(united),step(united)";
			out.write(buffer); 
			out.newLine();
			out.newLine();

			NodeList recipeLst = doc.getElementsByTagName("recipe");
			for (int recipeIdx = 0; recipeIdx < recipeLst.getLength(); recipeIdx++) {
				buffer = "case" + (recipeIdx + 1) + ":";
				
				Node recipeNode = recipeLst.item(recipeIdx);
				if (recipeNode.getNodeType() == Node.ELEMENT_NODE) {
					Element recipeElmt = (Element) recipeNode;
					//System.out.println("title : " + getElementValue("title", recipeElmt));
					buffer += "/title:" + getElementValue("title", recipeElmt);
					
					NodeList ingrediantLst = recipeElmt.getElementsByTagName("ingredients").item(0).getChildNodes();
					for (int ingrediantIdx = 0; ingrediantIdx < ingrediantLst.getLength(); ingrediantIdx++) {
						Node ingrediantNode = ingrediantLst.item(ingrediantIdx);
						if (ingrediantNode.getNodeType() == Node.ELEMENT_NODE) {
							Element ingrediantElmt = (Element)ingrediantNode;
							
							buffer += "/food:" + getAttributeValue("food", ingrediantElmt);
							buffer += "/quantity:" + getAttributeValue("quantity", ingrediantElmt);
							buffer += "/unit:" + getAttributeValue("unit", ingrediantElmt);
						}
					}
					
					buffer += "/step:";
					
					NodeList stepLst = recipeElmt.getElementsByTagName("preparation").item(0).getChildNodes();
					for (int stepIdx = 0; stepIdx < stepLst.getLength(); stepIdx++) {
						Node stepNode = stepLst.item(stepIdx);
						if (stepNode.getNodeType() == Node.ELEMENT_NODE) {
							Element stepElmt = (Element)stepNode;
							
							buffer += getElementValue(stepElmt) + " ";
						}
					}
					
					buffer += "/";
				}
				
				out.write(buffer); 
				out.newLine();
			}
			
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generateType3(String xmlFilePath, String txtFilePath) {
		try {
			File xmlFile = new File(xmlFilePath);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			BufferedWriter out = new BufferedWriter(new FileWriter(txtFilePath));
			String buffer = "";
			
			buffer += "#CocktailPlainTextCaseBase";
			out.write(buffer); 
			out.newLine();
			
			buffer += "#caseId;title,ingredient(united),step(united)";
			out.write(buffer); 
			out.newLine();
			out.newLine();

			NodeList recipeLst = doc.getElementsByTagName("recipe");
			for (int recipeIdx = 0; recipeIdx < recipeLst.getLength(); recipeIdx++) {
				buffer = "case" + (recipeIdx + 1) + ":";
				
				Node recipeNode = recipeLst.item(recipeIdx);
				if (recipeNode.getNodeType() == Node.ELEMENT_NODE) {
					Element recipeElmt = (Element) recipeNode;
					//System.out.println("title : " + getElementValue("title", recipeElmt));
					buffer += getElementValue("title", recipeElmt) + ":";
					
					NodeList ingrediantLst = recipeElmt.getElementsByTagName("ingredients").item(0).getChildNodes();
					for (int ingrediantIdx = 0; ingrediantIdx < ingrediantLst.getLength(); ingrediantIdx++) {
						Node ingrediantNode = ingrediantLst.item(ingrediantIdx);
						if (ingrediantNode.getNodeType() == Node.ELEMENT_NODE) {
							Element ingrediantElmt = (Element)ingrediantNode;
							
							if(ingrediantIdx == ingrediantLst.getLength() - 2) {
								buffer += getAttributeValue("food", ingrediantElmt);
							} else {
								buffer += getAttributeValue("food", ingrediantElmt) + ",";
							}
						}
					}
					
					buffer += ":";
					
					NodeList stepLst = recipeElmt.getElementsByTagName("preparation").item(0).getChildNodes();
					for (int stepIdx = 0; stepIdx < stepLst.getLength(); stepIdx++) {
						Node stepNode = stepLst.item(stepIdx);
						if (stepNode.getNodeType() == Node.ELEMENT_NODE) {
							Element stepElmt = (Element)stepNode;
							
							buffer += getElementValue(stepElmt) + " ";
						}
					}
					
					buffer += "/";
				}
				
				out.write(buffer); 
				out.newLine();
			}
			
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String getElementValue(Element element) {
		NodeList nodeList = element.getChildNodes();
		Node node = (Node)nodeList.item(0);
		
		return node.getNodeValue();
	}
			 
	private static String getElementValue(String tag, Element element) {
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node)nodeList.item(0);
		return node.getNodeValue();
	}
	
	private static String getAttributeValue(String tag, Element element) {
		String value = element.getAttribute(tag);	
		return value;
	}
}
