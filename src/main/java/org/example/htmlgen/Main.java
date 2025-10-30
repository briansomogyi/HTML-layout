package org.example.htmlgen;

/**
 * Clasa client (Client).
 * Acum construiește arborele folosind clasele concrete corecte
 * (HtmlBlockElement vs HtmlInlineElement).
 */
public class Main {
    public static void main(String[] args) {

        // --- Construim arborele HTML ---
        // Clientul decide tipul de element:

        // Rădăcina <html> (block)
        BaseHtmlElement html = new HtmlBlockElement("html", "");

        // <head> (block)
        BaseHtmlElement head = new HtmlBlockElement("head", "");
        BaseHtmlElement title = new HtmlInlineElement("title", ""); // <title> e inline
        title.addChild(new HtmlTextNode("Pagina Mea de Export"));
        head.addChild(title);
        html.addChild(head);

        // <body> (block)
        BaseHtmlElement body = new HtmlBlockElement("body", "");

        // <h1> (inline)
        BaseHtmlElement h1 = new HtmlInlineElement("h1", "");
        h1.addChild(new HtmlTextNode("Raport Măsurători"));
        body.addChild(h1);

        // <div> (block)
        BaseHtmlElement div = new HtmlBlockElement("div", "id='container'");

        // <p> (inline)
        BaseHtmlElement p1 = new HtmlInlineElement("p", "");
        p1.addChild(new HtmlTextNode("Acesta este un paragraf extras dintr-un document."));
        div.addChild(p1);

        // <p> (inline)
        BaseHtmlElement p2 = new HtmlInlineElement("p", "");
        p2.addChild(new HtmlTextNode("Datele evidențiate sunt "));

        BaseHtmlElement bTag = new HtmlInlineElement("b", ""); // <b> (inline)
        bTag.addChild(new HtmlTextNode("foarte importante"));
        p2.addChild(bTag);

        p2.addChild(new HtmlTextNode("."));
        p2.addChild(new HtmlSelfClosingTag("br", "")); // <br>
        p2.addChild(new HtmlTextNode("Vă rugăm să le analizați."));
        div.addChild(p2);

        // <img> (Tag self-closing - exemplu imagini Word)
        div.addChild(new HtmlSelfClosingTag("img", "src='grafic.png' alt='Grafic Măsurători'"));

        body.addChild(div);
        html.addChild(body);

        // --- Generăm și afișăm HTML-ul final ---
        // Aici se întâmplă magia Polimorfismului!
        // Când apelăm html.generateHtml(0), fiecare copil va apela
        // PROPRIA sa versiune a metodei.
        String finalHtml = html.generateHtml(0);

        System.out.println("--- Început Export HTML ---");
        System.out.println(finalHtml);
        System.out.println("--- Sfârșit Export HTML ---");
    }
}
