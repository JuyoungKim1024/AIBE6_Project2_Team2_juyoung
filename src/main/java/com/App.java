package com;

import java.util.Scanner;

public class App {
    Scanner scanner = new Scanner(System.in);

    public void run() {
        System.out.println("=== 게시판 프로그램 ===");

        while (true) {
            System.out.println("명령어: ");
            String cmd = scanner.nextLine().trim();

            switch (cmd) {
                case "exit" -> {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                default -> {
                    System.out.println("알 수 없는 명령어입니다.");
                }
            }
        }
    }
}
