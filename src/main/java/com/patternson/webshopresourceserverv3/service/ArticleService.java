package com.patternson.webshopresourceserverv3.service;

import com.patternson.webshopresourceserverv3.model.ArticleDTO;

import java.util.List;

/**
 *
 * Created by Tobias Pettersson 20180320
 */
public interface ArticleService {

    List<ArticleDTO> getAllArticles();

    ArticleDTO getArticleById(Long id);

    ArticleDTO createNewArticle(ArticleDTO articleDTO);

    ArticleDTO saveArticleByDTO(Long id, ArticleDTO articleDTO);

//    ArticleDTO patchArticle(Long id, ArticleDTO articleDTO);
//
    void deleteArticleById(Long id);
}
