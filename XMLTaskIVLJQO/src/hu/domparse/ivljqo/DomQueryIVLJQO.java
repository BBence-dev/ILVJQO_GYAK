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
         //Vevö Lekérdezés
         String expression = "/etterem/vevo[@id = "+szam+"]";
         NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
            doc, XPathConstants.NODESET);
         
         for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            System.out.println("\nJelenlegi Element :" + nNode.getNodeName());
            
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
         //Étterem lekérdezés
         String expression2 = "/etterem/etterem[@etteremid = "+szam+"]";
         NodeList nodeList2 = (NodeList) xPath.compile(expression2).evaluate(
            doc, XPathConstants.NODESET);
         
         for (int i = 0; i < nodeList2.getLength(); i++) {
            Node nNode2 = nodeList2.item(i);
            System.out.println("\nJelenlegi Element :" + nNode2.getNodeName());
            
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
