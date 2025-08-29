package MiniJava.parser;

public class Action {
    private static final int ACCEPT_NUMBER = 0;
    private static final char REDUCE_PREFIX = 'r';
    private static final char SHIFT_PREFIX = 's';

    public act action;
    public int number;

    public Action(act action, int number) {
        this.action = action;
        this.number = number;
    }

    @Override
    public String toString() {
        switch (action) {
            case accept :
                return "acc";
            case shift :
                return SHIFT_PREFIX + number + "";
            case reduce :
                return REDUCE_PREFIX + number + "";
        }
        return action.toString() + number;
    }

    public static Action fromString(String s) {
        if (s.equals("acc"))
            return new Action(act.accept, ACCEPT_NUMBER);
        char type = s.charAt(0);
        int number = Integer.parseInt(s.substring(1));
        return new Action(type == REDUCE_PREFIX ? act.reduce : act.shift, number);
    }
}

enum act {
    shift, reduce, accept
}
