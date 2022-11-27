package hu.domparse.ivljqo;

import java.io.File;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;



public class DomReadIVLJQO {
	public static void main (String [] args) {
		
		File xml = new File("XMLIVLJQO.xml");
		//
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	
		try {
			//Dokumentum fa felépítése
			DocumentBuilder db = dbf.newDocumentBuilder();
			//xml beolvasás
			Document doc = db.parse(xml);
			//Gyökér beolvasása próbaként
			System.out.println(doc.getDocumentElement().getNodeName());
			
			//Minden adat kiírása
			//összeszedi az elemeket a fából
			NodeList nlVevo = doc.getElementsByTagName("vevo");
			NodeList nlEtterem = doc.getElementsByTagName("etterem");
			System.out.println("Vevök száma: " + nlVevo.getLength());
			System.out.println("Étterem száma: " + nlEtterem.getLength());
			
			//lista elemeire ciklus
			for (int i = 0; i < nlVevo.getLength(); i++) {
				Node n1 = nlVevo.item(i);
				
				if(n1.getNodeType() == Node.ELEMENT_NODE) {
					Element el1 = (Element) n1;
					
					//vevö adatainak kiírása
					System.out.println((i+1) + ". vevö neve: " + el1.getElementsByTagName("nev").item(0).getTextContent());
					System.out.println((i+1) + ". vevö fizetése: " + el1.getElementsByTagName("kartya").item(0).getTextContent());
					System.out.println((i+1) + ". vevö készpénz: " + el1.getElementsByTagName("keszpenz").item(0).getTextContent());
					System.out.println((i+1) + ". vevö telefonszáma: " + el1.getElementsByTagName("telefonszam").item(0).getTextContent());
					System.out.println((i+1) + ". vevö lakcíme: " + el1.getElementsByTagName("iranyitoszam").item(0).getTextContent() + ". "
							+ el1.getElementsByTagName("varos").item(0).getTextContent() 
								+ ", " + el1.getElementsByTagName("utca").item(0).getTextContent() + 
									" utca " + el1.getElementsByTagName("hazszam").item(0).getTextContent());
					
				}
			}
			
			//étterem adatainak kiírása
			for (int i = 0; i < nlEtterem.getLength(); i++) {
				Node n2 = nlEtterem.item(i);
				
				if(n2.getNodeType() == Node.ELEMENT_NODE) {
					Element el2 = (Element) n2;
					System.out.println((i+1) + ". etterem neve: " + el2.getElementsByTagName("nev").item(0).getTextContent()
							+ " és weblap: " + el2.getElementsByTagName("weblap").item(0).getTextContent());
				}
			}
			//kivételkezelés
		} catch (Exception e) {
			System.out.println("Hiba történt: " +e.getMessage());
		}
	}
}