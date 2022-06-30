package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.operations.utils;

public class LevenshteinDistance implements Algorithm<Integer> {

    private final String input1;
    private final String input2;

    public LevenshteinDistance(final String input1, final String input2) {
        this.input1 = input1;
        this.input2 = input2;
    }

    public Integer compute() {
        return calculate(input1, input2);
    }

    private int cost(char a, char b) {
        return a == b ? 0 : 1;
    }

    private int min(int value1, int value2, int value3) {
        return Math.min(Math.min(value1, value2), value3);
    }

    private int calculate(String input1, String input2) {

        if (input1.isEmpty()) return input2.length();
        if (input2.isEmpty()) return input1.length();

        int substitution = calculate(input1.substring(1), input2.substring(1)) + cost(input1.charAt(0), input2.charAt(0));
        int insertion = calculate(input1, input2.substring(1)) + 1;
        int deletion = calculate(input1.substring(1), input2) + 1;

        return min(substitution, insertion, deletion);
    }
}
