package org.example.htmlgen;

/**
 * Moștenire & Polimorfism: O implementare specifică a BaseHtmlElement.
 * Această clasă ȘTIE cum să se randeze ca un element "block"
 * (ex: <div>, <body>).
 */
public class HtmlBlockElement extends BaseHtmlElement {

    public HtmlBlockElement(String tagName, String attributes) {
        super(tagName, attributes);
    }

    /**
     * Implementarea Polimorfică a metodei de randare PENTRU elementele block.
     * Logica de aici era în ramura "else" din vechiul "if".
     */
    @Override
    public String generateHtml(int indentationLevel) {
        String indent = getIndentation(indentationLevel);
        StringBuilder html = new StringBuilder();

        html.append(indent).append("<").append(tagName).append(attributes).append(">\n"); // Newline după deschidere

        for (IHtmlNode child : children) {
            // Apel polimorfic: nu știm/pasă ce tip e copilul (Block, Inline, Text),
            // el știe singur cum să se randeze.
            html.append(child.generateHtml(indentationLevel + 1)); // Indentare + 1
        }

        html.append(indent).append("</").append(tagName).append(">\n");
        return html.toString();
    }
}
