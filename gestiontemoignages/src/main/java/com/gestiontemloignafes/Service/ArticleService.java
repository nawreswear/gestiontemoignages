package com.gestiontemloignafes.Service;



import com.gestiontemloignafes.model.Article;

import java.util.Optional;

public interface ArticleService {
    Optional<Article> getArticleById(Long id);

    Article findById(Long idArticle);
    //void updateIdEnchers(Long articleId, Long enchereId);
    //Article updateArticlee(Long id, Long enchereId);
   // Article createArticle(Article article);


   // Article createArticlee(Article article, Long vendeurId);

    //Article updateArticle(Long id, Article newArticle);

    
//    void deleteArticle(Long id);

    //String updateArticlePriceByEnchereAndArticle(Long enchereId, Long articleId, double prixvente);

}
