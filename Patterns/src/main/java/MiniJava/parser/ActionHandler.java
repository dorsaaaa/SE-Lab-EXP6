package MiniJava.parser;

import MiniJava.scanner.token.Token;

public interface ActionHandler {
    void handle(Parser parser, Token lookAhead, Action currentAction) throws Exception;
}
