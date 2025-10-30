package org.example.htmlgen;

/**
 * Clasa client (Client).
 * Demonstrează utilizarea pattern-ului Composite pentru a construi o structură HTML.
 * Similar cu Main.java din exemplul tău.
 */
public class Main {
    public static void main(String[] args) {

        // --- Construim arborele HTML ---

        // Rădăcina <html>
        HtmlElement html = new HtmlElement("html");

        // <head>
        HtmlElement head = new HtmlElement("head");
        HtmlElement title = new HtmlElement("title");
        title.addChild(new HtmlTextNode("Pagina Mea de Export"));
        head.addChild(title);
        html.addChild(head);

        // <body>
        HtmlElement body = new HtmlElement("body");

        // <h1>
        HtmlElement h1 = new HtmlElement("h1");
        h1.addChild(new HtmlTextNode("Raport Măsurători"));
        body.addChild(h1);

        // <div> pentru conținut
        HtmlElement div = new HtmlElement("div", "id='container'");

        // <p> (Paragraf simplu - exemplu convertor Word)
        HtmlElement p1 = new HtmlElement("p");
        p1.addChild(new HtmlTextNode("Acesta este un paragraf extras dintr-un document."));
        div.addChild(p1);

        // <p> (Paragraf cu <b> și <br> - exemplu complex)
        HtmlElement p2 = new HtmlElement("p");
        p2.addChild(new HtmlTextNode("Datele evidențiate sunt "));

        HtmlElement bTag = new HtmlElement("b"); // <b>
        bTag.addChild(new HtmlTextNode("foarte importante")); // <b>text</b>
        p2.addChild(bTag); // <p>...<b>...</b>

        p2.addChild(new HtmlTextNode("."));
        p2.addChild(new HtmlSelfClosingTag("br", "")); // <br>
        p2.addChild(new HtmlTextNode("Vă rugăm să le analizați."));
        div.addChild(p2);

        // <img> (Tag self-closing - exemplu imagini Word)
        div.addChild(new HtmlSelfClosingTag("img", "src='grafic.png' alt='Grafic Măsurători'"));

        body.addChild(div);
        html.addChild(body);

        // --- Generăm și afișăm HTML-ul final ---
        // Acesta este "exportul" în consolă.
        // Pentru a salva într-un fișier, ai folosi un FileWriter.
        String finalHtml = html.generateHtml(0); // Începem de la nivelul 0

        System.out.println("--- Început Export HTML ---");
        System.out.println(finalHtml);
        System.out.println("--- Sfârșit Export HTML ---");
    }
}