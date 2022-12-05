package hu.domparse.ivljqo;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomModifyIVLJQO {

	public static void main(String[] args) {
		
		try {
			//fájl helyének megadása
			String path = "XMLIVLJQO.xml";
			File xmlFile = new File (path);
			//
			DocumentBuilder db;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
            db = dbf.newDocumentBuilder();
            Document doc = db.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Módosítás
            updateElement(doc);

            // Törlés
            deleteElement(doc);

            // Element hozzáadása
            addElement(doc);
            addElement2(doc);

            // Módosított fájl létrehozása
            writeFile(doc);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void writeFile(Document doc)
    throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
        doc.getDocumentElement().normalize();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource ds = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("Updated_XMLIVLJQO.xml"));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(ds, result);
        System.out.println("XML módosítva");
    }

     //új elem hozzáadása az étteremhez
    private static void addElement(Document doc) {
    	NodeList etterem1 = doc.getElementsByTagName("etterem");
        Element etterem = null;
        
        //minden darabra
        for (int i = 0; i < etterem1.getLength(); i++) {
            etterem = (Element) etterem1.item(i); 
            Element id = doc.createElement("kapacitas");
            id.appendChild(doc.createTextNode("500"));
            etterem.appendChild(id);
        }
    }
    
    private static void addElement2(Document doc) {
    	NodeList vevo = doc.getElementsByTagName("vevo");
        Element vevoad = null;
        
        //minden darabra
        for (int i = 0; i < vevo.getLength(); i++) {
           vevoad = (Element) vevo.item(i); 
            Element id = doc.createElement("kor");
            id.appendChild(doc.createTextNode("22"));
            vevoad.appendChild(id);
        }
    }

    //Egy elem törlése
    private static void deleteElement(Document doc) {
    	NodeList etterem = doc.getElementsByTagName("fizetes");
        Element fizetes = null;
        //minden elementre
        for (int i = 0; i < etterem.getLength(); i++) {
            fizetes = (Element) etterem.item(i);
            Node kartya = fizetes.getElementsByTagName("kartya").item(0);
            fizetes.removeChild(kartya);
        }
    }
    
    
  //Egy ügyfél nevének megváltoztatása (elsõ elem használatával)
    private static void updateElement(Document doc) {
        NodeList etterem = doc.getElementsByTagName("vevo");
        Element vevo = null;
        // minden elementre
        for (int i = 0; i < etterem.getLength(); i++) {
            vevo = (Element) etterem.item(i);
            Node nev = vevo.getElementsByTagName("nev").item(0).getFirstChild();
            //megfelelõ ügyfél megkeresése és adat megváltoztatása
            if (vevo.getAttribute("id").contentEquals("1")) {
            	nev.setNodeValue("Ádám");
            }
            
        }
    }
}