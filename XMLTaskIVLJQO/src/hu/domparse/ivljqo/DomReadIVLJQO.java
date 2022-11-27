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
			//Dokumentum fa fel�p�t�se
			DocumentBuilder db = dbf.newDocumentBuilder();
			//xml beolvas�s
			Document doc = db.parse(xml);
			//Gy�k�r beolvas�sa pr�bak�nt
			System.out.println(doc.getDocumentElement().getNodeName());
			
			//Minden adat ki�r�sa
			//�sszeszedi az elemeket a f�b�l
			NodeList nlVevo = doc.getElementsByTagName("vevo");
			NodeList nlEtterem = doc.getElementsByTagName("etterem");
			System.out.println("Vev�k sz�ma: " + nlVevo.getLength());
			System.out.println("�tterem sz�ma: " + nlEtterem.getLength());
			
			//lista elemeire ciklus
			for (int i = 0; i < nlVevo.getLength(); i++) {
				Node n1 = nlVevo.item(i);
				
				if(n1.getNodeType() == Node.ELEMENT_NODE) {
					Element el1 = (Element) n1;
					
					//vev� adatainak ki�r�sa
					System.out.println((i+1) + ". vev� neve: " + el1.getElementsByTagName("nev").item(0).getTextContent());
					System.out.println((i+1) + ". vev� fizet�se: " + el1.getElementsByTagName("kartya").item(0).getTextContent());
					System.out.println((i+1) + ". vev� k�szp�nz: " + el1.getElementsByTagName("keszpenz").item(0).getTextContent());
					System.out.println((i+1) + ". vev� telefonsz�ma: " + el1.getElementsByTagName("telefonszam").item(0).getTextContent());
					System.out.println((i+1) + ". vev� lakc�me: " + el1.getElementsByTagName("iranyitoszam").item(0).getTextContent() + ". "
							+ el1.getElementsByTagName("varos").item(0).getTextContent() 
								+ ", " + el1.getElementsByTagName("utca").item(0).getTextContent() + 
									" utca " + el1.getElementsByTagName("hazszam").item(0).getTextContent());
					
				}
			}
			
			//�tterem adatainak ki�r�sa
			for (int i = 0; i < nlEtterem.getLength(); i++) {
				Node n2 = nlEtterem.item(i);
				
				if(n2.getNodeType() == Node.ELEMENT_NODE) {
					Element el2 = (Element) n2;
					System.out.println((i+1) + ". etterem neve: " + el2.getElementsByTagName("nev").item(0).getTextContent()
							+ " �s weblap: " + el2.getElementsByTagName("weblap").item(0).getTextContent());
				}
			}
			//kiv�telkezel�s
		} catch (Exception e) {
			System.out.println("Hiba t�rt�nt: " +e.getMessage());
		}
	}
}