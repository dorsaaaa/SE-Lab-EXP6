package MiniJava.parser;

import MiniJava.scanner.token.Token;

public class AcceptActionHandler implements ActionHandler {

    @Override
    public void handle(Parser parser, Token lookAhead, Action currentAction) {
        parser.setFinish(true);
    }
}
