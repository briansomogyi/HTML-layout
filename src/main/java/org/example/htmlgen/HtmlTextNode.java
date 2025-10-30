package org.example.htmlgen;

/**
 * Clasa Leaf (Frunză) - Reprezintă conținut text pur.
 */
public class HtmlTextNode implements IHtmlNode {

    private final String text;

    public HtmlTextNode(String text) {
        // Într-o aplicație reală, am face 'escaping' la caracterele
        // speciale HTML (ex: "<" devine "&lt;") chiar aici.
        this.text = text;
    }

    @Override
    public String generateHtml(int indentationLevel) {
        if (indentationLevel < 0) {
            // Modul inline
            return text;
        }
        // Modul block
        String indent = "\t".repeat(Math.max(0, indentationLevel));
        return indent + text + "\n";
    }
}
