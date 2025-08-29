package MiniJava;

public class Main {
    public static void main(String[] args) {
        CompilerFacade facade = new CompilerFacade();
        String path = "src/main/resources/code";
        boolean ok = facade.compileFile(path);
        if (!ok) {
            System.exit(1);
        }
    }
}
