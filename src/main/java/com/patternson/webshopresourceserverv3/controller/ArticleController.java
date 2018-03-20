package com.patternson.webshopresourceserverv3.controller;

import com.patternson.webshopresourceserverv3.model.ArticleDTO;
import com.patternson.webshopresourceserverv3.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.List;

/**
 *
 * Created by Tobias Pettersson 20180320
 */
@Slf4j
@RestController
@RequestMapping(ArticleController.BASE_URL)
public class ArticleController {

    public static final String BASE_URL = "/api/v1/articles";

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ArticleDTO>> getListOfArticles() {
        List<ArticleDTO> articleListDTO = articleService.getAllArticles();

        if (articleListDTO.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(articleListDTO, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ArticleDTO getArticleById(@PathVariable Long id) {
        return  articleService.getArticleById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasRole('ADMIN')")
    public ArticleDTO createNewArticle(@Valid @RequestBody ArticleDTO articleDTO) {
        return articleService.createNewArticle(articleDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
//    @PreAuthorize("hasRole('ADMIN')")
    public ArticleDTO updateArticle(@PathVariable Long id, @RequestBody ArticleDTO articleDTO) {
        System.out.println("Inne i update på resource server");
        return articleService.saveArticleByDTO(id, articleDTO);
    }

//    @PatchMapping({"/{id}"})
//    @ResponseStatus(HttpStatus.OK)
//    @PreAuthorize("hasRole('ADMIN')")
//    public ArticleDTO patchArticle(@PathVariable Long id, @RequestBody ArticleDTO articleDTO) {
//        System.out.println("Inne i patch på resource server");
//        return articleService.patchArticle(id, articleDTO);
//    }
//
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
//    @PreAuthorize("hasRole('ADMIN')")
    public void deleteArticle(@PathVariable Long id) {
        System.out.println("Inne i delete på resource server");
        articleService.deleteArticleById(id);
    }
}
