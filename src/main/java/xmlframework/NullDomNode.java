package xmlframework;

import org.w3c.dom.Element;

final class NullDomNode implements DomNode {
    static final NullDomNode INSTANCE = new NullDomNode();

    private NullDomNode() {}

    @Override
    public void appendChild(Element child) {
        throw new IllegalArgumentException("Node not found");
    }

    @Override
    public String getTextContent() {
        return null;
    }

    @Override
    public void setTextContent(String text) {
        throw new IllegalArgumentException("Node not found");
    }

    @Override
    public DomNode getParent() {
        return this;
    }

    @Override
    public void remove() {
        throw new IllegalArgumentException("Node not found");
    }
}
