package MiniJava;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import MiniJava.errorHandler.ErrorHandler;
import MiniJava.parser.Parser;

public class CompilerFacade {

    private Parser parser;

    public CompilerFacade() {
        this.parser = new Parser();
    }

    public void compile(String filePath) {
        try {
            parser.startParse(new Scanner(new File(filePath)));
        } catch (FileNotFoundException e) {
            ErrorHandler.printError(e.getMessage());
        }
    }
}
