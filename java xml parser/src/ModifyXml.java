import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class ModifyXml {
	public static void main(String[] args) {
		try {
			// load xml file
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("employee.xml"));
			// update nodes that we need to change
			XPath xPath = XPathFactory.newInstance().newXPath();
			Node firstName = (Node) xPath.compile("/employee/firstName").evaluate(document, XPathConstants.NODE);
			firstName.setTextContent("Mr.John");

			Node category = (Node) xPath.compile("/employee/age/@category").evaluate(document, XPathConstants.NODE);
			category.setTextContent("SNER");

			Node age = (Node) xPath.compile("/employee/age").evaluate(document, XPathConstants.NODE);
			age.setTextContent("62");

			Node department = (Node) xPath.compile("/employee/department").evaluate(document, XPathConstants.NODE);
			department.setTextContent("RETIER");
			// save updated results to file
			Transformer tf = TransformerFactory.newInstance().newTransformer();
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.setOutputProperty(OutputKeys.METHOD, "xml");
			tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			DOMSource domSource = new DOMSource(document);
			StreamResult sr = new StreamResult(new File("update.xml"));
			tf.transform(domSource, sr);

		} catch (ParserConfigurationException e) {
			System.out.println("Bad parser configuration");
			e.printStackTrace();
		} catch (SAXException e) {
			System.out.println("SAX error loading the file.");
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			System.out.println("Bad XPath Expression");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Error reading the file.");
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
