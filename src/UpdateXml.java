

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class UpdateXml {
	public static void main(String[] args) {
		try {
			String filepath = "C:\\Users\\webonise\\Desktop\\Pix4D.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			Node options = doc.getElementsByTagName("options").item(0);
			NodeList subNodes = options.getChildNodes();

			for (int i = 0; i < subNodes.getLength(); i++) {
				Node node = subNodes.item(i);
				if ("ortho".equals(node.getNodeName())) {
					NodeList ortho = node.getChildNodes();
					for (int j = 0; j < ortho.getLength(); j++) {
						Node orthoSubNode = ortho.item(j);
//						updateNode(orthoSubNode, nodeName, value);
						if ("dsmMethod".equals(orthoSubNode.getNodeName())) {
							orthoSubNode.setTextContent("dsmMethod Modified by Ashu1123S3S");
						}
					}
				}
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);
			
			System.out.println("Done");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}
	
	public static boolean updateNode(Node node, String nodeName, String value){
		
		return true;
	}

}
