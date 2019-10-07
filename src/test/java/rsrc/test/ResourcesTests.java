package rsrc.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import rsrc.Resources.ReaderRunnable;

import static java.util.Arrays.asList;

public class ResourcesTests {

    @Test public void test_loadFile_test0() {
        // Assert
        Character[] a = {'0', '1', '2', '3', '4', '5'};
        ArrayList<Character> expected = new ArrayList<>(asList(a));

        String path = "src\\test\\resources\\test0";
        ArrayList<Character> actual = new ArrayList<>(0);
        ReaderRunnable action = readData -> actual.add((char) readData);

        // Act
        rsrc.Resources.loadFile(path, action);

        // Assert
        Assert.assertEquals(actual, expected);
    }

    @Test public void test_loadFile_test1() {
        // Assert
        Character[] a = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        ArrayList<Character> expected = new ArrayList<>(asList(a));

        String path = "src\\test\\resources\\test1";
        ArrayList<Character> actual = new ArrayList<>(0);
        ReaderRunnable action = readData -> actual.add((char) readData);

        // Act
        rsrc.Resources.loadFile(path, action);

        // Assert
        Assert.assertEquals(actual, expected);
    }

    @Test public void test_loadFile_test2() {
        // Assert
        Character[] a = {'H', 'e', 'l', 'l', 'o', ',', ' ', 'W', 'o', 'r', 'l', 'd', '!'};
        ArrayList<Character> expected = new ArrayList<>(asList(a));

        String path = "src\\test\\resources\\test2";
        ArrayList<Character> actual = new ArrayList<>(0);
        ReaderRunnable action = readData -> actual.add((char) readData);

        // Act
        rsrc.Resources.loadFile(path, action);

        // Assert
        Assert.assertEquals(actual, expected);
    }

    @Test public void test_loadFile_test3() {
        // Assert
        Character[] a = {'a', ' ', 'b', ' ', 'c', ' ', 'd', ' ', 'e', ' ', 'f', ' ', 'g', '\n', 'a', ' ', 'b', ' ', 'c', ' ', 'd', ' ', 'e', ' ', 'f', ' ', 'g', '\n', '\t', 'a', ' ', 'b', ' ', 'c', ' ', 'd', ' ', 'e', ' ', 'f', ' ', 'g'};
        ArrayList<Character> expected = new ArrayList<>(asList(a));

        String path = "src\\test\\resources\\test3";
        ArrayList<Character> actual = new ArrayList<>(0);
        ReaderRunnable action = readData -> actual.add((char) readData);

        // Act
        rsrc.Resources.loadFile(path, action);

        // Assert
        Assert.assertEquals(actual, expected);
    }

    @Test public void test_loadGenericFile_test3() {
        // Assert
        Character[] a = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'a', 'b', 'c', 'd', 'e', 'f', 'g'};
        ArrayList<Character> expected = new ArrayList<>(asList(a));

        String path = "src\\test\\resources\\test3";
        ArrayList<Character> actual = new ArrayList<>(0);

        // Act
        rsrc.Resources.loadGenericFile(path, actual);

        // Assert
        Assert.assertEquals(actual, expected);
    }

}
