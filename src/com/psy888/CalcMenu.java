package com.psy888;

import java.util.Scanner;

public class CalcMenu {

    public void start() {
        String userInput = "";
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
