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
	   
	   
      try {
    	//f�jl hely�nek megad�sa
         File xml = new File("XMLIVLJQO.xml");
         //Dokumentum fa fel�p�t�se
         DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder;

         dBuilder = dbF.newDocumentBuilder();
         //xml beolvas�s
         Document doc = dBuilder.parse(xml);
         doc.getDocumentElement().normalize();
         //Utvonal
         XPath xPath =  XPathFactory.newInstance().newXPath();
         
         //Egy Vev� Lek�rdez�s
         System.out.println("---------------------------------------------------------");
         System.out.println("1.Lek�rdez�s: Lek�rj�k azt a vev�t akinek az 1 id-t adtuk");
         String expression = "/adatok/vevok/vevo[@id = '1']";
         NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
            doc, XPathConstants.NODESET);
         
         for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            System.out.println("\nJelenlegi Element :" + nNode.getNodeName());
            //Vev� adatainka k�irat�sa
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               System.out.println("Vev� id : " 
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
               System.out.println("telefonsz�m : " 
                       + eElement
                       .getElementsByTagName("telefonszam")
                       .item(0)
                       .getTextContent());
            }
         }
         System.out.println("---------------------------------------------------------");
         System.out.println("2.Lek�rdez�s: Lek�rj�k az �tterem �sszes adat�t");
         //�tterem �sszes elem�nek kiirat�sa
         String expression2 = "/adatok/ettermek/etterem";
         NodeList nodeList2 = (NodeList) xPath.compile(expression2).evaluate(
            doc, XPathConstants.NODESET);
         
         for (int i = 0; i < nodeList2.getLength(); i++) {
            Node nNode2 = nodeList2.item(i);
            System.out.println("\nJelenlegi Element :" + nNode2.getNodeName());
          //�ttrem adatainka k�irat�sa
            if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode2;
               System.out.println("Etterem id : " 
                  + eElement.getAttribute("etteremid"));
               System.out.println("nev : " 
                  + eElement
                  .getElementsByTagName("nev")
                  .item(0)
                  .getTextContent());
               System.out.println("telefonsz�m : " 
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
        
         System.out.println("---------------------------------------------------------");
         System.out.println("3.Lek�rdez�s: Megn�z�k hogy mennyi volt a bev�tel");
         NodeList nlVevo = doc.getElementsByTagName("vevo");
         //Kifizettet k�szp�nzek �sszege
          int osszeg=0;
         for (int i = 0; i < nlVevo.getLength(); i++) {
        	  int t[] = new int[nlVevo.getLength()];
				Node n1 = nlVevo.item(i);
				
				if(n1.getNodeType() == Node.ELEMENT_NODE) {
					Element el1 = (Element) n1;
					
					System.out.println("\n"+(i+1) + ". vev� k�szp�nz: " + el1.getElementsByTagName("keszpenz").item(0).getTextContent());
					t[i]=Integer.parseInt(el1.getElementsByTagName("keszpenz").item(0).getTextContent());
					
				}osszeg=osszeg+t[i];
				
			}  
         
         System.out.println("\nKifizette k�szp�nz �sszeg: "+osszeg);
         
         System.out.println("---------------------------------------------------------");
         System.out.println("4.Lek�rdez�s: Meg n�zz�k mennyi volt az �tlaga a bev�telnek");
       //K�szp�nz �tlaga
        float atl;
        int osszeg2=0;
         for (int i = 0; i < nlVevo.getLength(); i++) {
    	        	 
    	        	 int t[] = new int[nlVevo.getLength()];
    					Node n1 = nlVevo.item(i);
    					
    					if(n1.getNodeType() == Node.ELEMENT_NODE) {
    						Element el1 = (Element) n1;
    						t[i]=Integer.parseInt(el1.getElementsByTagName("keszpenz").item(0).getTextContent());
    					}osszeg2=osszeg2+t[i];
    					
    				}
             		atl=osszeg/nlVevo.getLength();
             		
            
         		System.out.println("\nKifizette k�szp�nz �tlaga: "+atl);
         		System.out.println("---------------------------------------------------------");
                System.out.println("5.Lek�rdez�s: Meg keress�k a legnyobb �rt�ket");
         //Legnagyobb K�szp�nz
         		int max=0;
             		 for (int i = 0; i < nlVevo.getLength(); i++) {
        	        	 
        	        	 int t[] = new int[nlVevo.getLength()];
        					Node n1 = nlVevo.item(i);
        					
        					if(n1.getNodeType() == Node.ELEMENT_NODE) {
        						Element el1 = (Element) n1;
        						t[i]=Integer.parseInt(el1.getElementsByTagName("keszpenz").item(0).getTextContent());
        					}
        					
        					if(max<t[i]) {
        						max=t[i];
        					}
        				}
               
			System.out.println("\nKifizette k�szp�nz Max: "+max);
			
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