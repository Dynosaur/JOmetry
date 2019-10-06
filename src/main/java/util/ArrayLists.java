package util;

import java.util.ArrayList;

public class ArrayLists {

    public static char[] toCharacterArray(ArrayList<Character> list) {
        Character[] characterArray = list.toArray(new Character[0]);
        char[] charArray = new char[characterArray.length];
        for(int i = 0; i < characterArray.length; i++) {
            charArray[i] = characterArray[i];
        }
        return charArray;
    }

}
