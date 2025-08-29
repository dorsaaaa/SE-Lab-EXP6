package MiniJava;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import MiniJava.errorHandler.ErrorHandler;
import MiniJava.parser.Parser;


public class CompilerFacade {


    public boolean compileFile(String filePath) {
        Parser parser = new Parser();
        try {
            Scanner sc = new Scanner(new File(filePath));
            parser.startParse(sc);
            sc.close();
            return !ErrorHandler.hasError;
        } catch (FileNotFoundException e) {
            ErrorHandler.printError(e.getMessage());
            return false;
        }
    }


    public boolean compileFromScanner(Scanner sc) {
        Parser parser = new Parser();
        parser.startParse(sc);
        return !ErrorHandler.hasError;
    }
}
