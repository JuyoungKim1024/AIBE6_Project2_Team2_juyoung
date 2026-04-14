package com.domain.article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    private final ArrayList<Article> articles = new ArrayList<>();

    public void add(Article article) {
        articles.add(article);
    }

    public void remove(Article article) {
        articles.remove(article);
    }

    public Article findById(int id) {
        for (Article article : articles) {
            if (article.getId() == id) {
                return article;
            }
        }

        return null;
    }

    public List<Article> findAll() {
        return articles;
    }


}