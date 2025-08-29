package MiniJava.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import MiniJava.Log.Log;
import MiniJava.codeGenerator.CodeGenerator;
import MiniJava.errorHandler.ErrorHandler;
import MiniJava.scanner.lexicalAnalyzer;
import MiniJava.scanner.token.Token;

public class Parser {

    private ArrayList<Rule> rules;
    private Stack<Integer> parsStack;
    private ParseTable parseTable;
    private lexicalAnalyzer lexicalAnalyzer;
    private CodeGenerator cg;
    private boolean finish;

    private Map<act, ActionHandler> actionHandlers;

    private Token lookAhead;

    public Parser() {
        parsStack = new Stack<>();
        parsStack.push(0);

        try {
            parseTable = new ParseTable(Files.readAllLines(Paths.get("src/main/resources/parseTable")).get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }

        rules = new ArrayList<>();
        try {
            for (String stringRule : Files.readAllLines(Paths.get("src/main/resources/Rules"))) {
                rules.add(new Rule(stringRule));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        cg = new CodeGenerator();
        finish = false;

        // Initialize handlers
        actionHandlers = new HashMap<>();
        actionHandlers.put(act.shift, new ShiftActionHandler());
        actionHandlers.put(act.reduce, new ReduceActionHandler());
        actionHandlers.put(act.accept, new AcceptActionHandler());
    }

    public void startParse(java.util.Scanner sc) {
        lexicalAnalyzer = new lexicalAnalyzer(sc);
        lookAhead = lexicalAnalyzer.getNextToken();

        while (!finish) {
            try {
                Log.print(lookAhead.toString() + "\t" + parsStack.peek());

                Action currentAction = parseTable.getActionTable(parsStack.peek(), lookAhead);
                Log.print(currentAction.toString());

                // Polymorphic handler instead of switch-case
                ActionHandler handler = actionHandlers.get(currentAction.action);
                if (handler != null) {
                    handler.handle(this, lookAhead, currentAction);
                } else {
                    throw new IllegalStateException("No handler for action: " + currentAction.action);
                }

                Log.print("");

            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }

        if (!ErrorHandler.hasError) {
            cg.printMemory();
        }
    }

    // Getter/Setter
    public Stack<Integer> getParsStack() {
        return parsStack;
    }

    public ArrayList<Rule> getRules() {
        return rules;
    }

    public ParseTable getParseTable() {
        return parseTable;
    }

    public lexicalAnalyzer getLexicalAnalyzer() {
        return lexicalAnalyzer;
    }

    public CodeGenerator getCodeGenerator() {
        return cg;
    }

    public void setLookAhead(Token token) {
        this.lookAhead = token;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public Token getLookAhead() {
        return lookAhead;
    }
}
