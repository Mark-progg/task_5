package org.example;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String testString = "Object-oriented programming is a programming language model organized around objects rather than \"actions\" and data rather than logic. Object-oriented programming blabla. Object-oriented programming bla.";

        Pattern pattern = Pattern.compile("object-oriented programming", Pattern.CASE_INSENSITIVE);

        System.out.println("Сообщение с заменой второго вхождения object-oriented programming на OOP:");
        System.out.println(findAndReplaceSecondPattern(testString, pattern));

        System.out.println("Ниже слово содержащее минимальное кол-во различных символов:");
        System.out.println(findWordOfMinDifferentChars(testString));

        System.out.println("Количество слов состоящих из латинских букв: " + findCountLatinWords(testString));

        System.out.println("Ниже слова палиндромы:");
        System.out.println(getPalindromeWords(testString));
    }

    public static String findAndReplaceSecondPattern(String inputString, Pattern pattern) {
        Matcher matcher = pattern.matcher(inputString);
        int startSecondPatter = 0;
        int endSecondPattern = 0;
        int countPattern = 0;

        while (matcher.find()) {
            countPattern++;
            if (countPattern == 2) {
                startSecondPatter = matcher.start();
                endSecondPattern = matcher.end();
                break;
            }
        }

        if (countPattern <= 1) {
            return inputString;
        } else {
            String begin = inputString.substring(0, startSecondPatter);
            String end = inputString.substring(endSecondPattern, inputString.length());
            return begin + "OOP" + end;
        }

    }

    private static String findWordOfMinDifferentChars(String inputString) {
        String[] words = inputString.split(" ");
        String wordOfMinDiffChars = words[0];
        int minCountOfDifferentChars = words[0].length();

        for (String word : words) {
            char[] chars = word.toCharArray();
            int currentCount = getCountDifferentChars(chars);
            if (currentCount < minCountOfDifferentChars) {
                minCountOfDifferentChars = currentCount;
                wordOfMinDiffChars = word;
            }
        }

        return wordOfMinDiffChars;
    }

    private static int getCountDifferentChars(char[] chars) {
        int countOfDifferentChars = 0;
        int countOfMatchingChars = 0;
        char charToCompare;

        for (char var1 : chars) {
            int compareCount = 0;
            charToCompare = var1;
            for (char var2 : chars) {
                if (charToCompare == var2) {
                    compareCount++;
                }
            }
            if (compareCount == 1)
                countOfDifferentChars++;
            else
                countOfMatchingChars++;
        }

        return countOfDifferentChars + countOfMatchingChars / 2;
    }

    public static int findCountLatinWords(String inputString) {
        String[] words = inputString.split(" ");
        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        int countPattern = 0;

        for (String word : words) {
            word = word.replaceAll("[!?.,:;{}()\"']", "");
            Matcher matcher = pattern.matcher(word);
            if (matcher.find()) {
                countPattern++;
            }
        }

        return countPattern;
    }

    public static String getPalindromeWords(String inputString) {
        String[] words = inputString.split(" ");
        ArrayList<String> palindromeList = new ArrayList<String>();

        for (String word : words) {
            word = word.replaceAll("[!?.,:;{}()]", "");
            String reverseWord = new StringBuilder(word).reverse().toString();
            if (word.equals(reverseWord)) {
                palindromeList.add(word);
            }
        }

        return String.join("\n", palindromeList);
    }
}