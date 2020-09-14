import org.w3c.dom.*;
import javax.xml.xpath.*;
import javax.xml.parsers.*;
import java.io.IOException;
import org.xml.sax.SAXException;

public class ReadXMLFile {
	public static void main(String[] args) {
		try {

			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setNamespaceAware(true);
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document document = builder.parse("employee.xml");

			// Create an XPath expression
			// XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = XPathFactory.newInstance().newXPath();
			NodeList employees = (NodeList) xpath.compile("/employee").evaluate(document, XPathConstants.NODESET);

			// Parse the results
			for (int i = 0; i < employees.getLength(); i++) {
				/*
				 * System.out.println(xpath.compile("/*").evaluate(employees.item(i))); prints
				 * everything in file
				 */
				// below print each node out indivdually
				System.out.println("firstName " + xpath.compile("./firstName").evaluate(employees.item(i)));
				System.out.println("lastName " + xpath.compile("./lastName").evaluate(employees.item(i)));
				System.out.println("age " + xpath.compile("./age").evaluate(employees.item(i)));
				System.out.println("\tcategory " + xpath.compile("./age/@category").evaluate(employees.item(i)));
				System.out.println("sex " + xpath.compile("./sex").evaluate(employees.item(i)));
				System.out.println("department " + xpath.compile("./department").evaluate(employees.item(i)));
			}
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
		}
	}

}
