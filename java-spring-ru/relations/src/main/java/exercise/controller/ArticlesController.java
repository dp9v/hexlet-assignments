package exercise.controller;

import exercise.dto.ArticleDto;
import exercise.model.Article;
import exercise.model.Category;
import exercise.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticlesController {

    private final ArticleRepository articleRepository;

    @GetMapping(path = "")
    public Iterable<Article> getArticles() {
        return articleRepository.findAll();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteArticle(@PathVariable long id) {
        articleRepository.deleteById(id);
    }

    // BEGIN

    @PostMapping
    public void createArticle(@RequestBody ArticleDto article) {
        var newArticle = new Article()
            .setName(article.getName())
            .setBody(article.getBody())
            .setCategory(new Category().setId(article.getCategory().getId()));
        articleRepository.save(newArticle);
    }


    @PatchMapping("{id}")
    public void saveArticle(@PathVariable int id, @RequestBody ArticleDto article) {
        var newArticle = new Article()
            .setName(article.getName())
            .setBody(article.getBody())
            .setId(id)
            .setCategory(new Category().setId(article.getCategory().getId()));
        articleRepository.save(newArticle);
    }


    @GetMapping(path = "/{id}")
    public Article getArticle(@PathVariable long id) {
        return articleRepository.findById(id);
    }
    // END
}
