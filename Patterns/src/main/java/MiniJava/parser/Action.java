package MiniJava.parser;

public class Action {
    public act action;
    public int number;

    public Action(act action, int number) {
        this.action = action;
        this.number = number;
    }

    @Override
    public String toString() {
        switch (action) {
            case accept: return "acc";
            case shift: return "s" + number;
            case reduce: return "r" + number;
        }
        return action.toString() + number;
    }

    public static Action fromString(String s) {
        if (s.equals("acc")) return new Action(act.accept, 0);
        char type = s.charAt(0);
        int number = Integer.parseInt(s.substring(1));
        return new Action(type == 'r' ? act.reduce : act.shift, number);
    }
}

enum act { shift, reduce, accept }
