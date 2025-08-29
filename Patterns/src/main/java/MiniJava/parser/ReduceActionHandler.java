package MiniJava.parser;

import MiniJava.scanner.token.Token;
import MiniJava.Log.Log;
import MiniJava.codeGenerator.CodeGenerator;

public class ReduceActionHandler implements ActionHandler {

    @Override
    public void handle(Parser parser, Token lookAhead, Action currentAction) throws Exception {
        Rule rule = parser.getRules().get(currentAction.number);

        for (int i = 0; i < rule.RHS.size(); i++) {
            parser.getParsStack().pop();
        }

        Log.print(parser.getParsStack().peek() + "\t" + rule.LHS);

        parser.getParsStack().push(parser.getParseTable().getGotoTable(parser.getParsStack().peek(), rule.LHS));
        Log.print(parser.getParsStack().peek() + "");

        CodeGenerator cg = parser.getCodeGenerator();
        try {
            cg.semanticFunction(rule.semanticAction, lookAhead);
        } catch (Exception e) {
            Log.print("Code Generator Error");
        }
    }
}
