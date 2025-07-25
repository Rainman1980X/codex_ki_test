package xmlframework;

import org.w3c.dom.Element;

interface DomNode {
    void appendChild(Element child);
    String getTextContent();
    void setTextContent(String text);
    DomNode getParent();
    void remove();
}
