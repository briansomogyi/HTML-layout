package org.example.htmlgen;

/**
 * Clasa client (Client).
 * Demonstrează cum se construiește arborele HTML folosind clasele
 * specifice (Block vs Inline), bazate pe pattern-ul Composite.
 */
public class Main {
    public static void main(String[] args) {

        // --- Construim arborele HTML ---
        // Clientul (acest cod) are responsabilitatea de a alege
        // implementarea corectă (Block vs Inline) pentru fiecare tag.

        BaseHtmlElement html = new HtmlBlockElement("html", ""); // <html>

        BaseHtmlElement head = new HtmlBlockElement("head", ""); // <head>
        BaseHtmlElement title = new HtmlInlineElement("title", ""); // <title>
        title.addChild(new HtmlTextNode("Pagina Mea de Export"));
        head.addChild(title);
        html.addChild(head);

        BaseHtmlElement body = new HtmlBlockElement("body", ""); // <body>

        BaseHtmlElement h1 = new HtmlInlineElement("h1", ""); // <h1>
        h1.addChild(new HtmlTextNode("Raport Măsurători"));
        body.addChild(h1);

        BaseHtmlElement div = new HtmlBlockElement("div", "id='container'"); // <div>

        BaseHtmlElement p1 = new HtmlInlineElement("p", ""); // <p>
        p1.addChild(new HtmlTextNode("Acesta este un paragraf extras dintr-un document."));
        div.addChild(p1);

        BaseHtmlElement p2 = new HtmlInlineElement("p", ""); // <p>
        p2.addChild(new HtmlTextNode("Datele evidențiate sunt "));

        BaseHtmlElement bTag = new HtmlInlineElement("b", ""); // <b>
        bTag.addChild(new HtmlTextNode("foarte importante"));
        p2.addChild(bTag);

        p2.addChild(new HtmlTextNode("."));
        p2.addChild(new HtmlSelfClosingTag("br", "")); // <br>
        p2.addChild(new HtmlTextNode("Vă rugăm să le analizați."));
        div.addChild(p2);

        // <img>
        div.addChild(new HtmlSelfClosingTag("img", "src='grafic.png' alt='Grafic Măsurători'"));

        body.addChild(div);
        html.addChild(body);

        // --- Generăm și afișăm HTML-ul final ---
        // Apelul html.generateHtml(0) pornește procesul recursiv
        // de randare polimorfică.
        String finalHtml = html.generateHtml(0);

        System.out.println("--- Început Export HTML ---");
        System.out.println(finalHtml);
        System.out.println("--- Sfârșit Export HTML ---");
    }
}
