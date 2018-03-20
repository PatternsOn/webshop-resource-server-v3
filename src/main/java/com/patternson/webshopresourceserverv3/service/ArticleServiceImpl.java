package com.patternson.webshopresourceserverv3.service;

import com.patternson.webshopresourceserverv3.controller.ArticleController;
import com.patternson.webshopresourceserverv3.domain.Article;
import com.patternson.webshopresourceserverv3.exception.ResourceNotFoundException;
import com.patternson.webshopresourceserverv3.mapper.ArticleMapper;
import com.patternson.webshopresourceserverv3.model.ArticleDTO;
import com.patternson.webshopresourceserverv3.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * Created by Tobias Pettersson 20180320
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleMapper articleMapper, ArticleRepository articleRepository) {
        this.articleMapper = articleMapper;
        this.articleRepository = articleRepository;
    }

    @Override
    public List<ArticleDTO> getAllArticles() {
        return articleRepository
                .findAll()
                .stream()
                .map(article -> {
                    ArticleDTO articleDTO = articleMapper.articleToArticleDTO(article);
                    articleDTO.setArticleUrl(getArticleUrl(article.getId()));
                    return articleDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ArticleDTO getArticleById(Long id) {

        Article article = articleRepository.findOne(id);

        if (article == null) {
            throw new ResourceNotFoundException();
        }

        ArticleDTO articleDTO = articleMapper.articleToArticleDTO(articleRepository.findOne(id));

        articleDTO.setArticleUrl(getArticleUrl(id));

        return articleDTO;

//        return articleRepository
//                .findOne(id)   // todo lös repository
//
//
//
//
//                .map(articleMapper::articleToArticleDTO)
//                .map(articleDTO -> {
//                    // set API URL
//                    articleDTO.setArticleUrl(getArticleUrl(id));
//                    return articleDTO;
//                })
//                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public ArticleDTO createNewArticle(ArticleDTO articleDTO) {
        return saveAndReturnDTO(articleMapper.articleDtoToArticle(articleDTO));
    }

    private ArticleDTO saveAndReturnDTO(Article article) {
        Article savedArticle = articleRepository.save(article);

        ArticleDTO returnDTO = articleMapper.articleToArticleDTO(savedArticle);

        returnDTO.setArticleUrl(getArticleUrl(savedArticle.getId()));

        return returnDTO;
    }

    @Override
    public ArticleDTO saveArticleByDTO(Long id, ArticleDTO articleDTO) {
        Article article = articleMapper.articleDtoToArticle(articleDTO);
        article.setId(id);

        return saveAndReturnDTO(article);
    }


//    @Override
//    public ArticleDTO patchArticle(Long id, ArticleDTO articleDTO) {
//        return articleRepository
//                .findOne(id)
//
//
//
//                .map(article -> {
//                 if (articleDTO.getName() != null) {
//                     article.setName(articleDTO.getName());
//                 }
//
//                 if (articleDTO.getDescription() != null) {
//                     article.setDescription(articleDTO.getDescription());
//                 }
//
//                 if (articleDTO.getPrice() != null) {
//                     article.setPrice(articleDTO.getPrice());
//                 }
//
//                 if (articleDTO.getCompany() != null) {
//                     article.setPrice(articleDTO.getPrice());
//                 }
//
//                 if (articleDTO.getStock() != null) {
//                     article.setStock(articleDTO.getStock());
//                 }
//
//                 ArticleDTO returnDTO = articleMapper.articleToArticleDTO(articleRepository.save(article));
//
//                 returnDTO.setArticleUrl(getArticleUrl(id));
//
//                 return returnDTO;
//
//                }).orElseThrow(ResourceNotFoundException::new);
//
//    }

    private String getArticleUrl(Long id) {
        return ArticleController.BASE_URL + "/" + id;
    }

    @Override
    public void deleteArticleById(Long id) {
        articleRepository.delete(id);
    }
}
