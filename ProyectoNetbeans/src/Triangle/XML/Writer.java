/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.XML;

import Triangle.AbstractSyntaxTrees.Program;
import java.io.File;
import java.io.FileOutputStream;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Fabricio Ceciliano
 */
public class Writer {
    private String fileName;
    private File file;
    private FileOutputStream fileWriter;

    public Writer(String fileName) {
        this.fileName = fileName.replaceFirst("[.][^.]+$", "") + ".xml";
        file = new File(this.fileName);
    }

    // Draw the AST representing a complete program.
    public void write(Program ast) {
        // Prepare the file to write
        System.out.println("holaaaaaaaa");
        try {
            fileWriter = new FileOutputStream(file);

            //HTML header
            fileWriter.write(("<?xml version=\"1.0\" standalone=\"yes\"?>\n").getBytes());

            WriterVisitor layout = new WriterVisitor(fileWriter);
            ast.visit(layout, null);

            fileWriter.close();
            System.out.println("XML file generated at " + this.fileName);

        } catch (IOException e) {
            System.err.println("Error while creating XML file for print the AST");
            e.printStackTrace();
        }
    }

}
