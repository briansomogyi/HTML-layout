package org.example.htmlgen;

import java.util.ArrayList;
import java.util.List;

/**
 * O clasă de bază abstractă pentru toate elementele HTML care pot avea copii.
 * Implementează logica comună (gestionarea copiilor, atributelor).
 * <p>
 * Abstractizare: Definește un "Element HTML" generic.
 * Encapsulare: Protejează lista de copii și logica de indentare.
 */
public abstract class BaseHtmlElement implements IHtmlNode {

    // protected pentru a fi accesibile de subclase
    protected String tagName;
    protected String attributes;
    protected List<IHtmlNode> children = new ArrayList<>();

    public BaseHtmlElement(String tagName, String attributes) {
        this.tagName = tagName;
        this.attributes = (attributes != null && !attributes.isEmpty()) ? " " + attributes : "";
    }

    // Metodă "final" pentru că subclasele nu ar trebui să suprascrie CUM
    // se adaugă un copil, ci doar CUM se randează.
    public final void addChild(IHtmlNode node) {
        children.add(node);
    }

    public final void removeChild(IHtmlNode node) {
        children.remove(node);
    }

    protected String getIndentation(int indentationLevel) {
        return "\t".repeat(Math.max(0, indentationLevel));
    }

    /**
     * Metodă abstractă - forțează subclasele (ex: Block vs Inline)
     * să implementeze propria logică de randare.
     * Acesta este punctul cheie pentru Polimorfism.
     */
    @Override
    public abstract String generateHtml(int indentationLevel);
}
