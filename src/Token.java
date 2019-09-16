public class Token {

    public static void parse(String statement) {
        StringBuilder string = new StringBuilder();
        for(char token : statement.toCharArray()) {

            string.append(token);
        }
    }

}
