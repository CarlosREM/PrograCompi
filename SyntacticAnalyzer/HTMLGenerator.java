/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.SyntacticAnalyzer;
import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * @author Fabricio Ceciliano
 */
public class HTMLGenerator {
    protected String upperText = "<!DOCTYPE html><html><head><style>* {  font-family: \"Courier New\", Courier, FreeMono;  font-size: 1em;}.reservedWord {	color: black;	font-weight: bold;}.identifier, .operator, .separator {	color: black;}.literal {	color: DarkBlue;}comment {	color: green;}</style></head><body>";
    protected String lowerText = "</body></html>";
    protected StringBuilder html;
    protected String fileName;
    private boolean lineOpened = false;

    public HTMLGenerator(String fileName) {
        this.fileName = fileName.replaceFirst("[.][^.]+$", "") + ".html";
        html = new StringBuilder();
        html.append(upperText);

    }

    public void newLine(){
            if(!lineOpened){
                  html.append("<p>");
                  lineOpened = true;
            }
    }

    public void endLine(){
        if(lineOpened){
                  html.append("</p>");
                  lineOpened = false;
            }
    }

    public void add(Token tok) {
            if (tok.kind >= Token.getFirstReservedWord() && tok.kind <= Token.getLastReservedWord()){
                    addReservedWord(tok);
                    return;
            }

            if(tok.kind == Token.IDENTIFIER || tok.kind == Token.OPERATOR ||
                  (tok.kind >= 30 && tok.kind <= 41)){

                    addIdentifierOperatorSeparator(tok);
                    return;
            }

            if(tok.kind == Token.INTLITERAL || tok.kind == Token.CHARLITERAL){
                    addLiteral(tok);
                    return;
            }

            if(tok.kind == Token.EOT){
                    endLine();
                    closeHtml();
                    generateFile();
                    return;
            }

            add(tok.spelling);
    }

    public void add(String string){
            html.append(string);
    }

    private void addReservedWord(Token tok){
            html.append("<token class=reservedWord>" + tok.spelling + "</token>");
    }

    private void addIdentifierOperatorSeparator(Token tok){
            html.append("<token class=identifier>" + tok.spelling + "</token>");
    }

    private void addLiteral(Token tok){
            html.append("<token class=literal>" + tok.spelling + "</token>");
    }

    public void addComment(String comment){
            html.append("<comment>" + comment + "</comment>");
    }

    public void closeHtml(){
            html.append(lowerText);
    }

    public void generateFile(){
        try {
        File sourceFile = new File(fileName);
        FileOutputStream source = new FileOutputStream(sourceFile);
        source.write(html.toString().getBytes());
            source.close();
            System.out.println("HTML file generated at " + this.fileName);
        }
        catch (java.io.IOException s) {
            System.out.println("Couldn't generate HTML file ");
        }
    }
}
