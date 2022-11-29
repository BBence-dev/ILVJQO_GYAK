package hu.domparse.ivljqo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class DomQueryIVLJQO {

   public static void main(String[] args) {
	   
	   int szam;
	   Scanner beolvas = new Scanner(System.in);
	   
      try {
    	//fájl helyének megadása
         File xml = new File("XMLIVLJQO.xml");
         //Dokumentum fa felépítése
         DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder;

         dBuilder = dbF.newDocumentBuilder();
         //xml beolvasás
         Document doc = dBuilder.parse(xml);
         doc.getDocumentElement().normalize();
         //Utvonal
         XPath xPath =  XPathFactory.newInstance().newXPath();
         
         System.out.println("Adjon meg egy id számot");
         szam = beolvas.nextInt();
         //Egy Vevö Lekérdezés
         String expression = "/etterem/vevo[@id = "+szam+"]";
         NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
            doc, XPathConstants.NODESET);
         
         for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            System.out.println("\nJelenlegi Element :" + nNode.getNodeName());
            //Vevö adatainka kíiratása
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               System.out.println("Vevö id : " 
                  + eElement.getAttribute("id"));
               System.out.println("nev : " 
                  + eElement
                  .getElementsByTagName("nev")
                  .item(0)
                  .getTextContent());
               System.out.println("keszpenz : " 
                  + eElement
                  .getElementsByTagName("keszpenz")
                  .item(0)
                  .getTextContent());
               System.out.println("kartya : " 
                  + eElement
                  .getElementsByTagName("kartya")
                  .item(0)
                  .getTextContent());
               System.out.println("telefonszám : " 
                       + eElement
                       .getElementsByTagName("telefonszam")
                       .item(0)
                       .getTextContent());
               System.out.println("irányitószám : " 
                       + eElement
                       .getElementsByTagName("iranyitoszam")
                       .item(0)
                       .getTextContent());
               System.out.println("varos : " 
                  + eElement
                  .getElementsByTagName("varos")
                  .item(0)
                  .getTextContent());
               System.out.println("utca : " 
                       + eElement
                       .getElementsByTagName("utca")
                       .item(0)
                       .getTextContent());
               System.out.println("házszam : " 
                       + eElement
                       .getElementsByTagName("hazszam")
                       .item(0)
                       .getTextContent());
            }
         }
         //Egy Étterem lekérdezés
         String expression2 = "/etterem/etterem[@etteremid = "+szam+"]";
         NodeList nodeList2 = (NodeList) xPath.compile(expression2).evaluate(
            doc, XPathConstants.NODESET);
         
         for (int i = 0; i < nodeList2.getLength(); i++) {
            Node nNode2 = nodeList2.item(i);
            System.out.println("\nJelenlegi Element :" + nNode2.getNodeName());
          //Éttrem adatainka kíiratása
            if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode2;
               System.out.println("Etterem id : " 
                  + eElement.getAttribute("etteremid"));
               System.out.println("nev : " 
                  + eElement
                  .getElementsByTagName("nev")
                  .item(0)
                  .getTextContent());
               System.out.println("telefonszám : " 
                       + eElement
                       .getElementsByTagName("telefonszam")
                       .item(0)
                       .getTextContent());
               System.out.println("Weblap : " 
                       + eElement
                       .getElementsByTagName("weblap")
                       .item(0)
                       .getTextContent());
            }
         }
        //Készpénzek összegzése
         NodeList nlVevo = doc.getElementsByTagName("vevo");
        int osszeg=0;
         for (int i = 0; i < nlVevo.getLength(); i++) {
        	 
        	 int t[] = new int[nlVevo.getLength()];
				Node n1 = nlVevo.item(i);
				
				if(n1.getNodeType() == Node.ELEMENT_NODE) {
					Element el1 = (Element) n1;
					
					System.out.println("\n"+(i+1) + ". vevö készpénz: " + el1.getElementsByTagName("keszpenz").item(0).getTextContent());
					t[i]=Integer.parseInt(el1.getElementsByTagName("keszpenz").item(0).getTextContent());
					
				}osszeg=osszeg+t[i];
					
			}
			System.out.println("\nKifizette készpénz Összeg: "+osszeg);
      } catch (ParserConfigurationException e) {
         e.printStackTrace();
      } catch (SAXException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (XPathExpressionException e) {
         e.printStackTrace();
      }
   }
}
