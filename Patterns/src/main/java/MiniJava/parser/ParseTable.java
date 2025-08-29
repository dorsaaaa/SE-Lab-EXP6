package MiniJava.parser;

import MiniJava.errorHandler.ErrorHandler;
import MiniJava.scanner.token.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mohammad hosein on 6/25/2015.
 */

public class ParseTable {
    private ArrayList<Map<Token, Action>> actionTable;
    private ArrayList<Map<NonTerminal, Integer>> gotoTable;

    public ParseTable(String jsonTable) throws Exception {
        jsonTable = jsonTable.substring(2, jsonTable.length() - 2);
        String[] rows = jsonTable.split("\\],\\[");

        Map<Integer, Token> terminals = new HashMap<>();
        Map<Integer, NonTerminal> nonTerminals = new HashMap<>();

        processHeader(rows[0], terminals, nonTerminals);

        actionTable = new ArrayList<>();
        gotoTable = new ArrayList<>();
        for (int i = 1; i < rows.length; i++) {
            processRow(rows[i], terminals, nonTerminals);
        }
    }

    private void processHeader(String headerRow, Map<Integer, Token> terminals,
            Map<Integer, NonTerminal> nonTerminals) {
        headerRow = headerRow.substring(1, headerRow.length() - 1);
        String[] columns = headerRow.split("\",\"");

        for (int i = 1; i < columns.length; i++) {
            if (columns[i].startsWith("Goto")) {
                String temp = columns[i].substring(5);
                try {
                    nonTerminals.put(i, NonTerminal.valueOf(temp));
                } catch (Exception e) {
                    ErrorHandler.printError(e.getMessage());
                }
            } else {
                terminals.put(i, new Token(Token.getTyepFormString(columns[i]), columns[i]));
            }
        }
    }

    private void processRow(String row, Map<Integer, Token> terminals, Map<Integer, NonTerminal> nonTerminals)
            throws Exception {
        row = row.substring(1, row.length() - 1);
        String[] columns = row.split("\",\"");

        actionTable.add(new HashMap<>());
        gotoTable.add(new HashMap<>());

        for (int j = 1; j < columns.length; j++) {
            if (!columns[j].equals("")) {
                if (columns[j].equals("acc")) {
                    actionTable.get(actionTable.size() - 1).put(terminals.get(j), new Action(act.accept, 0));
                } else if (terminals.containsKey(j)) {
                    Token t = terminals.get(j);
                    Action a = new Action(columns[j].charAt(0) == 'r' ? act.reduce : act.shift,
                            Integer.parseInt(columns[j].substring(1)));
                    actionTable.get(actionTable.size() - 1).put(t, a);
                } else if (nonTerminals.containsKey(j)) {
                    gotoTable.get(gotoTable.size() - 1).put(nonTerminals.get(j), Integer.parseInt(columns[j]));
                } else {
                    throw new Exception();
                }
            }
        }
    }

    public int getGotoTable(int currentState, NonTerminal variable) {
        return gotoTable.get(currentState).get(variable);
    }

    public Action getActionTable(int currentState, Token terminal) {
        return actionTable.get(currentState).get(terminal);
    }
}
