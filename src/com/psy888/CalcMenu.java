package com.psy888;

import java.util.Scanner;

public class CalcMenu {

    public void start() {
        String userInput = "";
        System.out.println("Можно вводить выражения типа 25+(3*20+15)/3");
        System.out.println("Возможно использование таких операторов ^ % + - * /");
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите выражение :");
            System.out.printf("-> ");
            userInput = scanner.nextLine();
            try {
                System.out.println("= " + Calc.getResult(userInput));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!userInput.isEmpty());
    }
}
