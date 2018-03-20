package com.patternson.webshopresourceserverv3.mapper;

import com.patternson.webshopresourceserverv3.domain.Article;
import com.patternson.webshopresourceserverv3.model.ArticleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * Created by Tobias Pettersson 20180320
 */
@Mapper
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    ArticleDTO articleToArticleDTO(Article article);

    Article articleDtoToArticle(ArticleDTO articleDTO);

}
