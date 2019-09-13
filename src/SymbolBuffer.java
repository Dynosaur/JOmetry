public class SymbolBuffer {

    private char[] characterArray;

    private int currentPosition = -1;
    private int nextPosition = 0;
    private int previousPosition = -2;

    public SymbolBuffer(String symbol) {
        characterArray = symbol.toCharArray();
    }

    public char next() {
        currentPosition++;
        nextPosition++;
        previousPosition++;
        return characterArray[currentPosition];
    }

    public char previous() {
        currentPosition--;
        nextPosition--;
        previousPosition--;
        return characterArray[nextPosition];
    }


}
