package com.psy888;

import java.util.ArrayList;
import java.util.Arrays;

public class Calc {

    //1 - найти выражения в скобках (последний индекс "(" до следующего индекса ")"
    //2 - вычислить выражения в скобках
    //3 - заменить выражения в скобках на результат вычисления в общей строке
    //--------------------повторять до тех пор пока есть скобки---------------
    //4 - вычислить все ^
    //5 - вычислить все / * %
    //6 - вычислить все + -
    //7 - вернуть результат

    private static ArrayList<String> operators = new ArrayList<>();
    private static ArrayList<String> stringNums = new ArrayList<>();
    private static ArrayList<Double> numbers = new ArrayList<>();




    private static boolean parseInput(String string) {
        //очищаем
        stringNums.clear();
        operators.clear();
        numbers.clear();
        //добавляем
        stringNums.addAll(Arrays.asList(string.split("[-*/+%^]")));
        for (String num : stringNums) {
            numbers.add(getNumber(num));
        }
        operators.addAll(Arrays.asList(string.split("\\d*\\.?\\d+")));
        operators.remove(0);//костыль!!!!
//        System.out.println("stringNums = " + stringNums);
//        System.out.println("numbers = " + numbers);
//        System.out.println("operators = " + operators);
        return numbers.size() == operators.size() + 1;
    }


    private static int[] findParentheses(String s) {
        //1 - найти выражения в скобках (последний индекс "(" до следующего индекса ")"
        int lastOpenParenthesesIndex = s.lastIndexOf("(");
        int closeParenthesesIndex = lastOpenParenthesesIndex;

        if (lastOpenParenthesesIndex != -1) {
            closeParenthesesIndex = s.indexOf(")", lastOpenParenthesesIndex + 1);
            return new int[]{lastOpenParenthesesIndex, closeParenthesesIndex};
        }
        return null;
    }


    public static String getResult(String str) throws Exception {
        if(str.isEmpty()) throw new Exception("Ввод пустой строки!");
        while (findParentheses(str) != null) {
            int[] expressionIndexes = findParentheses(str);
            //выражение в скобках в виде строки
            String subExpression = str.substring(expressionIndexes[0] + 1, expressionIndexes[1]);

            //2 - вычеслить выражения в скобках
            String tmpResult = doMath(subExpression);

            //3 - заменить выражения в скобках на результат вычисления в общей строке
            str = str.substring(0, expressionIndexes[0]) + tmpResult + str.substring(expressionIndexes[1]+1);

        }

        return doMath(str);
    }

    private static String doMath(String subExpression) throws Exception {
        if(!parseInput(subExpression)) return subExpression;
        String[] priority = {"^", "%", "/", "*", "-", "+"};
        for (String s : priority) {
            while (operators.indexOf(s) != -1) {
                double value = 0;
                double a = numbers.get(operators.indexOf(s));
                double b = numbers.get(operators.indexOf(s) + 1);

                switch (s) {
                    case "^":
                        value = Math.pow(a, b);
                        break;
                    case "%":
                        value = a % b;
                        break;
                    case "/":
                        if(b==0)throw new Exception("На ноль делить нельзя");
                        value = a / b;
                        break;
                    case "*":
                        value = a * b;
                        break;
                    case "+":
                        value = a + b;
                        break;
                    case "-":
                        value = a - b;
                        break;
                    default:
//                        throw new Exception("Неверный математический оператор ");
                }
                setSubResult(operators.indexOf(s), value);
            }
        }
        return String.valueOf(numbers.get(0));
    }

    private static void setSubResult(int index, double val) {
        operators.remove(index);
        numbers.remove(index + 1);
        numbers.set(index, val);
    }

    private static double getNumber(String s) {
        double num = 0;
        try {
            num = Double.parseDouble(s);
        } catch (NumberFormatException nfe) {
            System.out.println("Неверный ввод " + s + " числа!");
        }
        return num;
    }


}
