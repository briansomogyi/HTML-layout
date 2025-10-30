package org.example.htmlgen;

/**
 * Clasa Leaf (Frunză) - Reprezintă tag-uri self-closing (ex: <img>, <br>, <hr>).
 * De asemenea, un nod terminal, ca și clasa File.
 */
public class HtmlSelfClosingTag implements IHtmlNode {

    private final String tagName;
    private final String attributes;

    public HtmlSelfClosingTag(String tagName, String attributes) {
        this.tagName = tagName;
        this.attributes = (attributes != null && !attributes.isEmpty()) ? " " + attributes : "";
    }

    @Override
    public String generateHtml(int indentationLevel) {
        String tag = "<" + tagName + attributes + ">";

        if (indentationLevel < 0) {
            // Mod inline (ex: un <br> în interiorul unui <p>)
            return tag;
        }
        // Mod block (ex: un <hr> direct în <body>)
        String indent = "\t".repeat(Math.max(0, indentationLevel));
        return indent + tag + "\n";
    }
}
