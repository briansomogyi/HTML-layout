package org.example.htmlgen;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa Composite (Composite).
 * Reprezintă un tag HTML care poate avea copii (ex: <div>, <p>, <b>).
 * Similar cu clasa Folder din exemplul tău.
 */
public class HtmlElement implements IHtmlNode {

    private final String tagName; // ex: "p", "div", "b"
    private final String attributes; // ex: "id='container'"
    private final List<IHtmlNode> children = new ArrayList<>();

    // Flag pentru a gestiona tag-uri care țin conținutul pe aceeași linie (ex: <p>, <b>)
    // spre deosebire de tag-uri container (ex: <div>, <body>)
    private boolean isInlineContainer = false;

    public HtmlElement(String tagName) {
        this(tagName, "");
    }

    public HtmlElement(String tagName, String attributes) {
        this.tagName = tagName;
        this.attributes = (attributes != null && !attributes.isEmpty()) ? " " + attributes : "";

        // Tag-urile care de obicei conțin text și alte tag-uri inline
        if (List.of("p", "h1", "h2", "h3", "b", "i", "span", "title").contains(tagName)) {
            this.isInlineContainer = true;
        }
    }

    // Similar cu metoda 'add' din Folder
    public void addChild(IHtmlNode node) {
        children.add(node);
    }

    public void removeChild(IHtmlNode node) {
        children.remove(node);
    }

    private String getIndentation(int indentationLevel) {
        return "\t".repeat(Math.max(0, indentationLevel));
    }

    @Override
    public String generateHtml(int indentationLevel) {
        // Modul "inline" (nivel -1) este folosit de părinte (ex: <p>)
        // pentru a spune copiilor săi (text, <b>) să nu adauge indentare sau newline.
        if (indentationLevel < 0) {
            StringBuilder html = new StringBuilder();
            html.append("<").append(tagName).append(attributes).append(">");
            for (IHtmlNode child : children) {
                html.append(child.generateHtml(-1)); // Propagăm modul inline
            }
            html.append("</").append(tagName).append(">");
            return html.toString();
        }

        // Modul "Block" (indentationLevel >= 0)
        String indent = getIndentation(indentationLevel);
        StringBuilder html = new StringBuilder();

        html.append(indent).append("<").append(tagName).append(attributes).append(">");

        if (isInlineContainer) {
            // Pentru <p>, <h1>, <b> etc.
            // Copiii sunt puși inline, fără newline după tag-ul de deschidere
            for (IHtmlNode child : children) {
                html.append(child.generateHtml(-1)); // Trecem copiii în modul inline
            }
            html.append("</").append(tagName).append(">\n"); // Închidem pe aceeași linie
        } else {
            // Pentru <div>, <body>, <html> etc.
            // Copiii sunt puși pe linii noi, indentați
            html.append("\n"); // Newline după tag-ul de deschidere
            for (IHtmlNode child : children) {
                // Apel recursiv, similar cu Folder.getContent()
                html.append(child.generateHtml(indentationLevel + 1));
            }
            html.append(indent).append("</").append(tagName).append(">\n");
        }
        return html.toString();
    }
}