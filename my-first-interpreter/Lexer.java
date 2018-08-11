import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

class Lexer {
    private static String[] keywords = {
        "IF", "THEN", "WHILE", "DO", "OD", "PRINT"
    };
    private static String[] operators = {
        ":=", "-"
    };
    private String fname;

    public Lexer(String sourceFileName) {
        System.out.println("The keywords of this language are:");
        for(String k : keywords) {
            System.out.println(k);
        }
        fname = sourceFileName;
    }

    public void scan() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(fname)));
            scanner.useDelimiter(";\\s*");
            System.out.println();
            System.out.println("Statements in " + fname + " are:");
            while (scanner.hasNext()) {
                String statement = scanner.next().trim();
                if (statement.startsWith("//")) {
                    statement = removeComment(statement);

                }
                System.out.println(statement);
                System.out.println("Its tokens are:");
                printTokens(splitByWhitespace(statement));
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    // pre: statement.startsWith("//")
    private String removeComment(String statement) {
        StringBuilder sb = new StringBuilder();
        int i;
        for (i = statement.indexOf("//") + 2; i < statement.length(); i++) {
            if (statement.charAt(i) == '\n') {
                i++;            // skip '\n'
                break;
            }
        }
        for (; i < statement.length(); i++) {
            sb.append(statement.charAt(i));
        }
        return sb.toString();
    }

    private String[] splitByWhitespace(String statement) {
        return statement.split("\\s+");
    }

    private String[] splitByOperator(String s) {
        String regexp = genRegexpFromOperators();
        return s.split(regexp);
    }

    private String genRegexpFromOperators() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < operators.length; i++) {
            String op = operators[i];
            if (i == operators.length - 1)
                sb.append(op);
            else
                sb.append(op + "|");
        }
        return sb.toString();
    }

    private void printTokens(String[] tokens) {
        System.out.print("{ ");
        for (int i = 0; i < tokens.length; i++) {
            if (i == tokens.length - 1)
                System.out.print(tokens[i]);
            else
                System.out.print(tokens[i] + ", ");
        }
        if (tokens.length > 0)
            System.out.println(" }");
        else
            System.out.println("}");
    }

    public static void main(String[] args) {
        Lexer lexer = new Lexer(args[0]);
        lexer.scan();
    }
}
