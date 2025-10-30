package org.example.htmlgen;

/**
 * Moștenire & Polimorfism: O implementare 'Composite' pentru elemente HTML
 * de tip "inline-container" (ex: <p>, <h1>, <b>).
 * <p>
 * Aceste elemente își vor randa copiii pe aceeași linie cu ele,
 * fără indentare sau noi linii între ei.
 */
public class HtmlInlineElement extends BaseHtmlElement {

    public HtmlInlineElement(String tagName, String attributes) {
        super(tagName, attributes);
    }

    /**
     * Implementarea polimorfică a randării pentru elementele 'inline'.
     * Gestionează două cazuri:
     * 1. Nivel < 0: Părintele este tot 'inline', deci se randează fără indentare.
     * 2. Nivel >= 0: Părintele este 'block', deci se randează cu indentare,
     * dar copiii săi sunt forțați în modul 'inline' (nivel -1).
     */
    @Override
    public String generateHtml(int indentationLevel) {
        if (indentationLevel < 0) {
            // Cazul 1: Randat de un părinte inline
            StringBuilder html = new StringBuilder();
            html.append("<").append(tagName).append(attributes).append(">");
            for (IHtmlNode child : children) {
                html.append(child.generateHtml(-1)); // Propagăm modul inline
            }
            html.append("</").append(tagName).append(">");
            return html.toString();
        }

        // Cazul 2: Randat de un părinte block
        String indent = getIndentation(indentationLevel);
        StringBuilder html = new StringBuilder();

        html.append(indent).append("<").append(tagName).append(attributes).append(">");

        for (IHtmlNode child : children) {
            html.append(child.generateHtml(-1)); // Forțăm copiii în modul inline
        }

        html.append("</").append(tagName).append(">\n");
        return html.toString();
    }
}
