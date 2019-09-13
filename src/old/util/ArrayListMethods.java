package old.util;

import java.util.ArrayList;

public class ArrayListMethods {

    public static <E> int getNumberOfInstances(ArrayList<E> arrayList, E instance) {

        int instances = 0;

        for(E element : arrayList)
            if(element.equals(instance)) instances++;

        return instances;
    }

    public static String tokenArrayToString(ArrayList<old.token.Token> arrayList) {

        StringBuilder builder = new StringBuilder();

        for(old.token.Token token : arrayList)
            builder.append(token.getSymbol());

        return builder.toString();

    }

    public static ArrayList<old.token.Token> removeTypes(ArrayList<old.token.Token> arrayList, old.token.Token.Type type) {
        arrayList.removeIf(e -> e.getType() == type);
        return arrayList;
    }

}
