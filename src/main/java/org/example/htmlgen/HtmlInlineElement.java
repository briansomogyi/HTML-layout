package org.example.htmlgen;

/**
 * Moștenire & Polimorfism: O altă implementare specifică.
 * Această clasă ȘTIE cum să se randeze ca un element "inline"
 * (ex: <p>, <h1>, <b>).
 */
public class HtmlInlineElement extends BaseHtmlElement {

    public HtmlInlineElement(String tagName, String attributes) {
        super(tagName, attributes);
    }

    /**
     * Implementarea Polimorfică a metodei de randare PENTRU elementele inline.
     * Logica de aici era în ramura "if (isInlineContainer)" din vechiul "if".
     */
    @Override
    public String generateHtml(int indentationLevel) {
        // Când un element inline este randat de un părinte inline (nivel -1),
        // rămâne complet pe aceeași linie, fără indentare.
        if (indentationLevel < 0) {
            StringBuilder html = new StringBuilder();
            html.append("<").append(tagName).append(attributes).append(">");
            for (IHtmlNode child : children) {
                html.append(child.generateHtml(-1)); // Propagăm modul inline
            }
            html.append("</").append(tagName).append(">");
            return html.toString();
        }

        // Când un element inline este randat de un părinte block (ex: <p> într-un <div>)
        // se pune pe linia lui, dar copiii lui sunt puși inline.
        String indent = getIndentation(indentationLevel);
        StringBuilder html = new StringBuilder();

        html.append(indent).append("<").append(tagName).append(attributes).append(">");

        // Copiii sunt puși inline (nivel -1), fără newline după tag-ul de deschidere
        for (IHtmlNode child : children) {
            html.append(child.generateHtml(-1)); // Trecem copiii în modul inline
        }

        html.append("</").append(tagName).append(">\n"); // Închidem și dăm newline
        return html.toString();
    }
}
