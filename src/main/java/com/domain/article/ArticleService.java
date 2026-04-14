package com.domain.article;

import java.time.LocalDate;
import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository;
    private int lastId = 0;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void write(String title, String content) {
        int id = ++lastId;
        String Date = getCurrentDate();

        Article article = new Article(id, title, content, Date);
        articleRepository.add(article);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findById(int id) {
        return articleRepository.findById(id);
    }

    public boolean update(int id, String title, String content) {
        Article article = articleRepository.findById(id);

        if (article == null) {
            return false;
        }
        article.setTitle(title);
        article.setContent(content);

        return true;
    }

    public boolean delete(int id) {
        Article article = articleRepository.findById(id);

        if (article == null) {
            return false;
        }

        articleRepository.remove(article);
        return true;
    }

    private String getCurrentDate() {
        return LocalDate.now().toString();
    }
}