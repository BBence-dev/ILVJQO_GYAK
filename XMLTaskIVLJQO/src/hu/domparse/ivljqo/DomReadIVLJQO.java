package hu.domparse.ivljqo;

import java.io.File;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


public class DomReadIVLJQO {
	public static void main (String [] args) {
		
	
		try {
			//fájl helyének megadása
			File xml = new File("XMLIVLJQO.xml");
			//
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	
			//Dokumentum fa felépítése
			DocumentBuilder db = dbf.newDocumentBuilder();
			//xml beolvasás
			Document doc = db.parse(xml);
	
			XPath xPath =  XPathFactory.newInstance().newXPath();
			
			//Minden adat kiírása
			//összeszedi az elemeket a fából
			
			String etterem = "/adatok/ettermek/etterem";
			NodeList nlEtterem = (NodeList) xPath.compile(etterem).evaluate( doc, XPathConstants.NODESET);
			String rendeles = "/adatok/rendelesadatok/rendeles";
			NodeList nlrendelesa = (NodeList) xPath.compile(rendeles).evaluate( doc, XPathConstants.NODESET);
			String vevo = "/adatok/vevok/vevo";
			NodeList nlVevo = (NodeList) xPath.compile(vevo).evaluate( doc, XPathConstants.NODESET);
			String etlap = "/adatok/etlapok/etlap";
			NodeList nlEtlap = (NodeList) xPath.compile(etlap).evaluate( doc, XPathConstants.NODESET);
			String lakcim = "/adatok/lakcimek/lakcim";
			NodeList nlLakcim = (NodeList) xPath.compile(lakcim).evaluate( doc, XPathConstants.NODESET);
			
			//lista elemeire ciklus
			for (int i = 0; i < nlVevo.getLength(); i++) {
				Node n1 = nlVevo.item(i);
				
				if(n1.getNodeType() == Node.ELEMENT_NODE) {
					Element el1 = (Element) n1;
					
					//vevö adatainak kiírása
					System.out.println((i+1) + ". |Vevö neve: " + el1.getElementsByTagName("nev").item(0).getTextContent() + " |Kartya: " + el1.getElementsByTagName("kartya").item(0).getTextContent()
							+ " |Készpénz: " + el1.getElementsByTagName("keszpenz").item(0).getTextContent()+ " |Telefonszáma: " + el1.getElementsByTagName("telefonszam").item(0).getTextContent());
					
				}
			}
			
			for (int i = 0; i < nlLakcim.getLength(); i++) {
				Node n5 = nlLakcim.item(i);
				
				if(n5.getNodeType() == Node.ELEMENT_NODE) {
					Element el5 = (Element) n5;
					
					//vevö adatainak kiírása
					System.out.println((i+1) + ". |Vevö lakcime: " + " |Irsz: " +el5.getElementsByTagName("iranyitoszam").item(0).getTextContent()+ " |Város: " + el5.getElementsByTagName("varos").item(0).getTextContent()
							+ " |Utca: "+ el5.getElementsByTagName("utca").item(0).getTextContent()+ " |Házszám: "+ el5.getElementsByTagName("hazszam").item(0).getTextContent());
					
				}
			}
			
			//étterem adatainak kiírása
			for (int j = 0; j < nlEtterem.getLength(); j++) {
				Node n2 = nlEtterem.item(j);
				
				if(n2.getNodeType() == Node.ELEMENT_NODE) {
					Element el2 = (Element) n2;
					System.out.println((j+1) + ". |Étterem neve: " + el2.getElementsByTagName("nev").item(0).getTextContent()+ " |Weblap: " + el2.getElementsByTagName("weblap").item(0).getTextContent()+ 
							" |Telefonszam: " + el2.getElementsByTagName("telefonszam").item(0).getTextContent());
				}
			}
			for (int i = 0; i < nlrendelesa.getLength(); i++) {
				Node n3 = nlrendelesa.item(i);
				
				if(n3.getNodeType() == Node.ELEMENT_NODE) {
					Element el3 = (Element) n3;
					
					//rendelés adatainak kiírása
					System.out.println((i+1) + ". |Rendelés tól: " + el3.getElementsByTagName("tol").item(0).getTextContent()+ ". |Rendelés ig: " + el3.getElementsByTagName("ig").item(0).getTextContent());
					
				}
			}
			for (int i = 0; i < nlEtlap.getLength(); i++) {
				Node n4 = nlEtlap.item(i);
				
				if(n4.getNodeType() == Node.ELEMENT_NODE) {
					Element el4 = (Element) n4;
					
					//rendelés adatainak kiírása
					System.out.println((i+1) + ". |Étel név: " + el4.getElementsByTagName("nev").item(0).getTextContent()+ ". |Étel darab száma: " + el4.getElementsByTagName("darab").item(0).getTextContent()
					 + ". |Összeg: " + el4.getElementsByTagName("osszeg").item(0).getTextContent());
					
				}
			}
			//kivételkezelés
		} catch (Exception e) {
			System.out.println("Hiba történt: " +e.getMessage());
		}
	}
}