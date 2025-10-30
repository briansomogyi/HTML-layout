package org.example.htmlgen;

/**
 * Clasa Leaf (Frunză) - Reprezintă conținut text pur.
 * Similar cu clasa File, în sensul că este un nod terminal.
 */
public class HtmlTextNode implements IHtmlNode {

    private final String text;

    public HtmlTextNode(String text) {
        // Într-o aplicație reală, am face escape la caracterele HTML aici
        // (ex: "<" devine "&lt;")
        this.text = text;
    }

    @Override
    public String generateHtml(int indentationLevel) {
        if (indentationLevel < 0) {
            // Mod inline (ex: textul din interiorul unui <p>)
            return text;
        }
        // Mod block (rar folosit pentru text pur, dar pentru consistență)
        String indent = "\t".repeat(Math.max(0, indentationLevel));
        return indent + text + "\n";
    }
}