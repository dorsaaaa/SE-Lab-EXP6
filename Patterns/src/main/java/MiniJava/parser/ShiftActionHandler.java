package MiniJava.parser;

import MiniJava.scanner.token.Token;

public class ShiftActionHandler implements ActionHandler {

    @Override
    public void handle(Parser parser, Token lookAhead, Action currentAction) {
        parser.getParsStack().push(currentAction.number);
        parser.setLookAhead(parser.getLexicalAnalyzer().getNextToken());
    }
}
