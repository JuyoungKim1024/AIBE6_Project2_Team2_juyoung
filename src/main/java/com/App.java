package com;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Article> articles = new ArrayList<>();
    int lastId = 0;

    public void run() {
        System.out.println("=== 게시판 프로그램 ===");

        while (true) {
            System.out.print("명령어: ");
            String cmd = scanner.nextLine();

            Rq rq = new Rq(cmd);

            switch (rq.getActionName()) {
                case "write" -> writeArticle();
                case "list" -> listArticles();
                case "detail" -> showDetail(rq.getParam());
                case "update" -> updateArticle(rq.getParam());
                case "delete" -> deleteArticle(rq.getParam());
                case "exit" -> {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("존재하지 않는 명령어입니다.");
            }
        }
    }

    public void writeArticle() {
        System.out.println("제목: ");
        String title = scanner.nextLine().trim();
        System.out.println("내용: ");
        String content = scanner.nextLine().trim();

        int id = ++lastId;
        String date = getCurrentDate();

        Article article = new Article(id, title, content, date);

        addArticle(article);

        System.out.println("=> "+id + "번 글이 등록되었습니다.");
    }

    public void listArticles(){
        System.out.println("번호 | 제목 | 작성일");
        System.out.println("-----------------------------");
        for (int i = 0; i <= articles.size() - 1; i++) {
            Article article = articles.get(i);
            System.out.printf("%d | %s | s% \n",article.getId(), article.getTitle(), article.getdate());
        }
    }

    public void showDetail(String param){
        Article article = getArticleFromParam(param, "상세보기할 글 번호를 입력해주세요.");
        if(article == null) {
            return;
        }

        System.out.println("번호: " + article.getId());
        System.out.println("제목: " + article.getTitle());
        System.out.println("내용: " + article.getContent());
        System.out.println("작성일: " + article.getdate());
    }

    public void updateArticle(String param){
        Article article=getArticleFromParam(param, "수정할 글 번호를 입력해주세요.");
        if(article == null) {
            return;
        }

        System.out.print("제목 (현재: " + article.getTitle() + "): ");
        String newTitle = scanner.nextLine().trim();
        System.out.println("내용 (현재: " + article.getContent() + "): ");
        String newContent = scanner.nextLine().trim();

        article.setTitle(newTitle);
        article.setContent(newContent);

        System.out.println("=> " + article.getId() + "번 글이 수정되었습니다.");
    }

    public void deleteArticle(String param){
        Article article = getArticleFromParam(param, "삭제할 글 번호를 입력해주세요.");
        if(article == null) {
            return;
        }

        removeArticle(article);

        System.out.println("=> " + article.getId() + "번 글이 삭제되었습니다.");
    }

    public void removeArticle(Article article){
        articles.remove(article);
    }

    public Article getArticleFromParam(String param, String emptyMessage){
        if(param.isEmpty()){
            System.out.println(emptyMessage);
            return null;
        }

        int id =Integer.parseInt(param);
        Article article = findArticleById(id);

        if(article == null){
            System.out.println(id + "번 글은 존재하지 않습니다.");
             return null;
        }

        return article;
    }

    public Article findArticleById(int Id) {
        for (Article article : articles) {
            if (article.getId() == Id) {
                return article;
            }
        }
        return null;
    }

    public void addArticle(Article article) {
        articles.add(article);
    }

    public String getCurrentDate() {
        return LocalDate.now().toString();
    }

}
