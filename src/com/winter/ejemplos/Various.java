package com.winter.ejemplos;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Various {

    public static boolean isUniqueUsingHash(String word) {
        char[] chars = word.toCharArray();
        Set<Character> set = new HashSet<Character>();
        for (char c : chars)
            if (set.contains(c))
                return false;
            else
                set.add(c);
        return true;
    }

    public static boolean isUniqueUsingSort(String word) {
        char[] chars = word.toCharArray();
        if (chars.length <= 1)
            return true;
        Arrays.sort(chars);
        char temp = chars[0];
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == temp)
                return false;
            temp = chars[i];
        }
        return true;
    }

    public static boolean isPermutation(String s1, String s2) {
        char[] a = s1.toCharArray();
        char[] b = s2.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
        if (a.length != b.length)
            return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i])
                return false;
        }
        return true;
    }

    public static char[] URLify(char[] chars, int len) {
        int spaces = countSpaces(chars, len);
        int end = len - 1 + spaces * 2;
        for (int i = len - 1; i >= 0; i--) {
            if (chars[i] == ' ') {
                chars[end - 2] = '%';
                chars[end - 1] = '2';
                chars[end] = '0';
                end -= 3;
            } else {

                chars[end] = chars[i];
                end--;
            }
        }
        return chars;
    }

    public static int countSpaces(char[] chars, int len) {
        int count = 0;
        for (int i = 0; i < len; i++)
            if (chars[i] == ' ')
                count++;
        return count;
    }

    public static boolean permuteHash(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            Character c = Character.toLowerCase(str.charAt(i));
            if (!Character.isLetter(c))
                continue;
            if (map.containsKey(c))
                map.put(c, map.get(c) + 1);
            else
                map.put(c, 1);
        }

        // print map
        map.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " " + entry.getValue());
        });

        int odd = 0;
        for (Character key : map.keySet()) {
            System.out.println("key  = " + key + " , value = " + map.get(key));
            if (map.get(key) % 2 != 0)
                odd++;
        }

        if (odd > 1)
            return false;
        else
            return true;
    }

    public static String compress(String input) {
        char[] cs = input.toCharArray();
        char temp = cs[0];
        int i = 0, j = 0, count = 0, len = cs.length;
        while (j < len) {
            cs[i++] = temp;
            while (j < len && temp == cs[j]) {
                j++;
                count++;
            }
            if (j < len)
                temp = cs[j++];
            cs[i++] = String.valueOf(count).charAt(0);
            count = 1;
        }
        return new String(cs, 0, i);
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;
            for (int i = first; i < last; i++) {
                int offset = i - first;
                int top = matrix[first][i];
                matrix[first][i] = matrix[last - offset][first];
                matrix[last - offset][first] = matrix[last][last - offset];
                matrix[last][last - offset] = matrix[i][last];
                matrix[i][last] = top;
            }
        }
    }

    public static int add(int a, int b) {
        if (b > a) {
            int temp = b;
            b = a;
            a = temp;
        }
        int carry = 0;
        while (b != 0) {
            carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }
        return a;
    }

    public static int[] removeDuplicates(int[] input) {
        int j = 0;
        int i = 1;
        if (input.length < 2) {
            return input;
        }
        while (i < input.length) {
            if (input[i] == input[j]) {
                i++;
            } else {
                input[++j] = input[i++];
            }
        }
        int[] output = new int[j + 1];
        for (int k = 0; k < output.length; k++) {
            output[k] = input[k];
        }
        return output;
    }

    public static boolean isPrimeNumber(int number) {
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void findTheSum() {
        int number = 2;
        int count = 0;
        long sum = 0;
        while (count < 1000) {
            if (isPrimeNumber(number)) {
                sum += number;
                count++;
            }
            number++;
        }
        System.out.println("\nTHE SUM OF THE FIRST 1000 PRIME NUMBERS is " + sum);
    }

    public static void main(String[] args) {
        System.out.println(isUniqueUsingHash("Word") ? "Unique" : "Not Unique");
        System.out.println(isUniqueUsingSort("Not unique") ? "Unique" : "Not Unique");

        System.out.println(isPermutation("test", "estt") ? "It is a permutation" : "It is not a permutation");

        char[] chars = "Mr John Smith ".toCharArray();
        // Mr%20John%20h
        System.out.println(URLify(chars, 8));

        System.out.println(permuteHash("Tact Coa") ? "True" : "False");

        // a2b3c3
        System.out.println(compress("aabbbccc"));

//        int[][] matrix = new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15},
//                {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};

        int[][] matrix = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };

        // print matrix i = row; j = col
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }

        rotate(matrix);
        System.out.println("Rotate matrix");

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        int[] input1 = {2, 3, 6, 6, 8, 9, 10, 10, 10, 12, 12};
        int[] output = removeDuplicates(input1);
        for (int i : output) {
            System.out.print(i + " ");
        }

        int a = 0, b = 10, c = 0;
        if (input1[2] == input1[2])
            c = input1[++a] + input1[b++];

        System.out.println("c = " + c);
        System.out.println("c = " + b);

        findTheSum();
    }
}
