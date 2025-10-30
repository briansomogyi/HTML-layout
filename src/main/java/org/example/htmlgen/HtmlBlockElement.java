package org.example.htmlgen;

/**
 * Moștenire & Polimorfism: O implementare 'Composite' pentru elemente HTML
 * de tip "block" (ex: <div>, <body>, <html>).
 * <p>
 * Aceste elemente își vor randa copiii pe linii noi, cu indentare.
 */
public class HtmlBlockElement extends BaseHtmlElement {

    public HtmlBlockElement(String tagName, String attributes) {
        super(tagName, attributes);
    }

    /**
     * Implementarea polimorfică a randării pentru elementele 'block'.
     */
    @Override
    public String generateHtml(int indentationLevel) {
        String indent = getIndentation(indentationLevel);
        StringBuilder html = new StringBuilder();

        html.append(indent).append("<").append(tagName).append(attributes).append(">\n");

        for (IHtmlNode child : children) {
            html.append(child.generateHtml(indentationLevel + 1));
        }

        html.append(indent).append("</").append(tagName).append(">\n");
        return html.toString();
    }
}
