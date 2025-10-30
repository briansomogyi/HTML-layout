package org.example.htmlgen;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasă de bază abstractă pentru elementele HTML care pot conține noduri copil.
 * Gestionează logica comună pentru atribute și managementul copiilor.
 * <p>
 * Abstractizare: Definește un "Element HTML" generic.
 * Encapsulare: Protejează lista de copii și logica de indentare.
 */
public abstract class BaseHtmlElement implements IHtmlNode {

    protected String tagName;
    protected String attributes;
    protected List<IHtmlNode> children = new ArrayList<>();

    public BaseHtmlElement(String tagName, String attributes) {
        this.tagName = tagName;
        this.attributes = (attributes != null && !attributes.isEmpty()) ? " " + attributes : "";
    }

    /**
     * Adaugă un nod copil (Component, Composite sau Leaf) la acest element.
     * Metoda este 'final' pentru a asigura consistența.
     */
    public final void addChild(IHtmlNode node) {
        children.add(node);
    }

    /**
     * Elimină un nod copil.
     */
    public final void removeChild(IHtmlNode node) {
        children.remove(node);
    }

    /**
     * Metodă utilitară pentru a genera un string de indentare.
     */
    protected String getIndentation(int indentationLevel) {
        return "\t".repeat(Math.max(0, indentationLevel));
    }

    /**
     * Forțează subclasele concrete să definească propria logică de randare HTML.
     * Acesta este punctul central al polimorfismului în acest design.
     */
    @Override
    public abstract String generateHtml(int indentationLevel);
}
