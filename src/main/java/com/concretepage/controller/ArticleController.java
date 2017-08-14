package com.concretepage.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.concretepage.entity.Article;
import com.concretepage.service.IArticleService;

@RestController
@RequestMapping("api/user")
public class ArticleController {
	@Autowired
	private IArticleService articleService;

	@RequestMapping(value = "article/{id}", method = RequestMethod.GET)
	public ResponseEntity<Article> getArticleById(@PathVariable("id") Integer id) {

		Article article = articleService.getArticleById(id);
		return new ResponseEntity<Article>(article, HttpStatus.OK);

	}

	@RequestMapping(value = "articles")
	public ResponseEntity<List<Article>> getAllArticles() {

		List<Article> list = articleService.getAllArticles();
		return new ResponseEntity<List<Article>>(list, HttpStatus.OK);

	}

	@RequestMapping(value = "article",method = RequestMethod.POST)
	public ResponseEntity<Void> addArticle(@RequestBody Article article, UriComponentsBuilder builder) {

        boolean flag = articleService.addArticle(article);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/article/{id}").buildAndExpand(article.getArticleId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

	}

	@RequestMapping(value = "article",method = RequestMethod.PUT)
	public ResponseEntity<Article> updateArticle(@RequestBody Article article) {

		articleService.updateArticle(article);
		return new ResponseEntity<Article>(article, HttpStatus.OK);

	}

	@RequestMapping(value = "article/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteArticle(@PathVariable("id") Integer id) {

		articleService.deleteArticle(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}
	@RequestMapping(value = "saldo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getFechaSaldo(){
		Double saldo=new Double(12.3456);

		return new ResponseEntity<Object>(saldo,HttpStatus.OK);
	}
} 