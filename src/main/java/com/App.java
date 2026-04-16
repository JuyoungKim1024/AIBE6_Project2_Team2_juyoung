package com;

import com.domain.article.Article;
import com.domain.article.ArticleController;
import com.domain.article.ArticleRepository;
import com.domain.article.ArticleService;
import com.domain.system.SystemController;
import com.util.Rq;
import java.time.LocalDate;
import java.util.Scanner;

public class App {
    private final Scanner scanner = new Scanner(System.in);
    private final ArticleRepository articleRepository = new ArticleRepository();
    private final ArticleService articleService = new ArticleService(articleRepository);
    private final ArticleController articleController = new ArticleController(scanner, articleService);
    private final SystemController systemController = new SystemController();

    public void run() {
        System.out.println("=== 게시판 프로그램 ===");

        while (true) {
            System.out.print("명령어: ");
            String cmd = scanner.nextLine();

            Rq rq = new Rq(cmd);

            switch (rq.getActionName()) {
                case "write" -> articleController.write();
                case "list" -> articleController.list();
                case "detail" -> articleController.detail(rq.getParam());
                case "update" -> articleController.update(rq.getParam());
                case "delete" -> articleController.delete(rq.getParam());
                case "exit" -> systemController.actionExit();
                default -> System.out.println("존재하지 않는 명령어입니다.");
            }
        }
    }
}