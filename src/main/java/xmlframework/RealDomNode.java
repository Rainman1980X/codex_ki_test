package xmlframework;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

class RealDomNode implements DomNode {
    private final Node node;

    RealDomNode(Node node) {
        this.node = node;
    }

    @Override
    public void appendChild(Element child) {
        node.appendChild(child);
    }

    @Override
    public String getTextContent() {
        return node.getTextContent();
    }

    @Override
    public void setTextContent(String text) {
        node.setTextContent(text);
    }

    @Override
    public DomNode getParent() {
        Node parent = node.getParentNode();
        return parent == null ? NullDomNode.INSTANCE : new RealDomNode(parent);
    }

    @Override
    public void remove() {
        Node parent = node.getParentNode();
        if (parent != null) {
            parent.removeChild(node);
        }
    }

    Node unwrap() {
        return node;
    }
}
