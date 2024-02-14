package it.epicode.w6d3p.controller;

import it.epicode.w6d3p.Exception.NotFoundException;
import it.epicode.w6d3p.model.BlogPostRequest;
import it.epicode.w6d3p.model.CustomResponse;
import it.epicode.w6d3p.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @GetMapping("/blogPost")
    public ResponseEntity<CustomResponse> getAll(Pageable pageable){
        try {
            return CustomResponse.success(HttpStatus.OK.toString(), blogPostService.getAll(pageable), HttpStatus.OK);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/blogPost/{id}")
    public ResponseEntity<CustomResponse> getBlogPostById(@PathVariable int id){
        try {
            return CustomResponse.success(HttpStatus.OK.toString(), blogPostService.getBlogPostById(id), HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/blogPost")
    public ResponseEntity<CustomResponse> saveBlogPost(@RequestBody BlogPostRequest blogPostRequest){
        try{
            return CustomResponse.success(HttpStatus.OK.toString(), blogPostService.saveBlogPost(blogPostRequest), HttpStatus.OK);
        }
        catch (Exception | NotFoundException e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/blogPost/{id}")
    public ResponseEntity<CustomResponse> updateBlogPost(@PathVariable int id, @RequestBody BlogPostRequest blogPostRequest){
        try {
            return CustomResponse.success(HttpStatus.OK.toString(), blogPostService.updateBlogPost(id, blogPostRequest), HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/blogPost/{id}")
    public ResponseEntity<CustomResponse> deleteBlogPost(@PathVariable int id){
        try {
            blogPostService.deleteBlogPost(id);
            return CustomResponse.emptyResponse("BlogPost con id=" + id + " cancellata", HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
