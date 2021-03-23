package lesson4;

import java.util.*;
import java.util.function.Supplier;
import java.util.function.Consumer;

public class Main {

    private static List<String> wordsList;

    public static void main(String[] args) {
        doTask1();
        doTask2();
        doTask3();
        doTask4();
    }


    /**
     * Создать коллекцию типа List. Наполнить ее значениями
     * и вывести значения в консоль при помощи ее метода forEach.
     */

    static void doTask1() {
        wordsList = List.of("camel", "wolf", "fox", "crocodile", "bear", "elephant");
        System.out.println("Values task 1: ");

        wordsList.forEach(value -> {
            System.out.println(value);
        });
    }

    /**
     * Создать утилитарный метод forItem. Метод принимает два параметра:
     * Коллекция Set<String> и консьюмер типа Consumer<String>.
     * Внутри метода проитерироваться по коллекции и для каждого элемента
     * выполнить метод консьюмера accept, который выводит значение элемента в консоль.
     */

    static void doTask2() {
        Set<String> wordsSet = new HashSet<String>(wordsList);
        System.out.println("\nValues task 2: ");
        forItem(wordsSet, integer -> {
            System.out.println(integer);
        });
    }

    static void forItem(Set<String> values, Consumer<String> action) {
        Iterator<String> iterator = values.iterator();
        while (iterator.hasNext()) {
            String val = iterator.next();
            action.accept(val);
        }
    }


    /**
     * Создать утилитарный метод doubleUp. Метод принимает два параметра:
     * значение типа int и консьюмер типа Supplier<Integer>. Внутри метода выполнить метод саплаера get,
     * который возвращает множитель и затем переданное значение на него умножается.
     * Фукнция возращает результат произведения.
     */

    static void doTask3() {
        doubleUp(15, getMultiplayer());
    }

    static Supplier<Integer> getMultiplayer() {
        return () -> 2;
    }

    static int doubleUp(int value, Supplier<Integer> multiplayer) {
        return value * multiplayer.get();
    }


    /**
     * Создать метод findAllChars. Метод принимает два параметра: String target и char toFind.
     * Первый параметр является входной строкой, а второй - символ, который необходимо найти в входящей строке.
     * Учесть что искомый символ может повторяется (напр.: 'ccch').
     * Необходимо найти все повторения и вернуть в виде конкатенированной строки обвернутый в Optional.
     * Если ни одного совпадения не найдено, тогда необходимо вернуть пустой Optional.
     * Пример выполнения: Optional<String> opt = findAllChars("ccch", 'c'); opt.get(); // вернет "ссс".
     */

    static void doTask4() {
        System.out.println("\nValues task 4: ");
        System.out.println(findAllChars("ccch", 'c').get());
        System.out.println(findAllChars("ccch", 'h').get());
    }

    static Optional<String> findAllChars(String target, char toFind) {
        char[] chars = target.toCharArray();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == toFind) {
                sb.append(toFind);
            }
        }

        if (sb.length() > 0) {
            return Optional.of(sb.toString());
        }
        return Optional.empty();
    }
}


