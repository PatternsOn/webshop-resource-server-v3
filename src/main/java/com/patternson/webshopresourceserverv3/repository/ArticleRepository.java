package com.patternson.webshopresourceserverv3.repository;

import com.patternson.webshopresourceserverv3.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * Created by Tobias Pettersson 20180320
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
