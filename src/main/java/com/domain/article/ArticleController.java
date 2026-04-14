package com.domain.article;

import java.util.Scanner;

public class ArticleController {
    private final Scanner scanner;
    private final ArticleService articleService;

    public ArticleController(Scanner scanner, ArticleService articleService) {
        this.scanner = scanner;
        this.articleService = articleService;
    }

    public void write() {
        System.out.print("제목: ");
        String title = scanner.nextLine().trim();

        System.out.print("내용: ");
        String content = scanner.nextLine().trim();

        articleService.write(title, content);

        System.out.println("=> 게시글이 등록되었습니다.");
    }

    public void list() {
        System.out.println("번호 | 제목 | 등록일");
        System.out.println("-----------------------------");

        for (Article article : articleService.findAll()) {
            System.out.printf("%d | %s | %s%n",
                    article.getId(),
                    article.getTitle(),
                    article.getdate());
        }
    }

    public void detail(String param) {
        int id = getIdFromParam(param, "상세보기할 게시글 번호를 입력해주세요.");
        if (id == -1) {
            return;
        }

        Article article = articleService.findById(id);

        if (article == null) {
            System.out.println("해당 게시글이 존재하지 않습니다.");
            return;
        }

        System.out.println("번호: " + article.getId());
        System.out.println("제목: " + article.getTitle());
        System.out.println("내용: " + article.getContent());
        System.out.println("등록일: " + article.getdate());
    }

    public void update(String param) {
        int id = getIdFromParam(param, "수정할 게시글 번호를 입력해주세요.");
        if (id == -1) {
            return;
        }

        Article article = articleService.findById(id);

        if (article == null) {
            System.out.println("해당 게시글이 존재하지 않습니다.");
            return;
        }

        System.out.print("제목 (현재: " + article.getTitle() + "): ");
        String newTitle = scanner.nextLine().trim();

        System.out.print("내용 (현재: " + article.getContent() + "): ");
        String newContent = scanner.nextLine().trim();

        boolean isUpdated = articleService.update(id, newTitle, newContent);

        if (isUpdated) {
            System.out.println("=> 게시글이 수정되었습니다.");
        }
    }

    public void delete(String param) {
        int id = getIdFromParam(param, "삭제할 게시글 번호를 입력해주세요.");
        if (id == -1) {
            return;
        }

        boolean isDeleted = articleService.delete(id);

        if (!isDeleted) {
            System.out.println("해당 게시글이 존재하지 않습니다.");
            return;
        }

        System.out.println("=> 게시글이 삭제되었습니다.");
    }

    private int getIdFromParam(String param, String emptyMessage) {
        if (param.isEmpty()) {
            System.out.println(emptyMessage);
            return -1;
        }

        try {
            return Integer.parseInt(param);
        } catch (NumberFormatException e) {
            System.out.println("게시글 번호는 숫자로 입력해주세요.");
            return -1;
        }
    }
}
