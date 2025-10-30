package org.example.htmlgen;

/**
 * Interfața Component (Component).
 * Definește operația comună pentru toate nodurile arborelui HTML.
 * Similar cu IFileSystemComponent din exemplul tău.
 * Acesta este exemplul perfect de Abstractizare.
 */
public interface IHtmlNode {
    /**
     * Generează recursiv string-ul HTML pentru acest nod și copiii săi.
     *
     * @param indentationLevel Nivelul curent de indentare (pentru pretty-printing).
     * @return String-ul HTML generat.
     */
    String generateHtml(int indentationLevel);
}
