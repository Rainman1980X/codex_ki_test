package xmlframework;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XmlEditor {
    private final Document document;
    private final XPath xpath = XPathFactory.newInstance().newXPath();

    private XmlEditor(Document document) {
        this.document = document;
    }

    public static XmlEditor newDocument(String rootName) throws Exception {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.newDocument();
        Element root = doc.createElement(rootName);
        doc.appendChild(root);
        return new XmlEditor(doc);
    }

    public static XmlEditor fromFile(Path path) throws Exception {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(Files.newInputStream(path));
        return new XmlEditor(doc);
    }

    public void save(Path path) throws IOException, TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(document), new StreamResult(Files.newOutputStream(path)));
    }

    public <T> void addElement(String parentXPath, String name, T value) throws XPathExpressionException {
        Node parent = (Node) xpath.evaluate(parentXPath, document, XPathConstants.NODE);
        if (parent == null) {
            throw new IllegalArgumentException("Parent not found: " + parentXPath);
        }
        Element element = document.createElement(name);
        element.setTextContent(TypeConverter.toString(value));
        parent.appendChild(element);
    }

    public <T> T getValue(String elementXPath, Class<T> type) throws XPathExpressionException {
        Node node = (Node) xpath.evaluate(elementXPath, document, XPathConstants.NODE);
        if (node == null) {
            return null;
        }
        return TypeConverter.fromString(node.getTextContent(), type);
    }

    public <T> void updateValue(String elementXPath, T value) throws XPathExpressionException {
        Node node = (Node) xpath.evaluate(elementXPath, document, XPathConstants.NODE);
        if (node == null) {
            throw new IllegalArgumentException("Element not found: " + elementXPath);
        }
        node.setTextContent(TypeConverter.toString(value));
    }

    public void delete(String elementXPath) throws XPathExpressionException {
        Node node = (Node) xpath.evaluate(elementXPath, document, XPathConstants.NODE);
        if (node == null) {
            throw new IllegalArgumentException("Element not found: " + elementXPath);
        }
        Node parent = node.getParentNode();
        if (parent != null) {
            parent.removeChild(node);
        }
    }
}
