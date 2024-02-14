package it.epicode.w6d3p.service;

import it.epicode.w6d3p.Exception.NotFoundException;
import it.epicode.w6d3p.model.Autore;
import it.epicode.w6d3p.model.BlogPost;
import it.epicode.w6d3p.model.BlogPostRequest;
import it.epicode.w6d3p.repository.AutoreRepository;
import it.epicode.w6d3p.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BlogPostService {

    @Autowired
    private AutoreService autoreService;

    @Autowired
    private BlogPostRepository blogPostRepository;

    public Page<BlogPost> getAll(Pageable pageable){
        return blogPostRepository.findAll(pageable);
    }

    public BlogPost getBlogPostById(int id) throws NotFoundException {
        return blogPostRepository.findById(id).orElseThrow(() -> new NotFoundException("Auto con id=" + id + " non trovata"));
    }

    public BlogPost saveBlogPost(BlogPostRequest blogPostRequest) throws NotFoundException {

        Autore autore = autoreService.getAutoreById(blogPostRequest.getIDAutore());

        BlogPost blogPost = new BlogPost();

        blogPost.setContenuto(blogPostRequest.getContenuto());
        blogPost.setCategoria(blogPostRequest.getCategoria());
        blogPost.setCover(blogPostRequest.getCover());
        blogPost.setTitolo(blogPostRequest.getTitolo());
        blogPost.setTempoLettura(blogPostRequest.getTempoLettura());
        blogPost.setAutore(autore);

        return blogPostRepository.save(blogPost);

    }

    public BlogPost updateBlogPost(int id, BlogPostRequest blogPostRequest) throws NotFoundException {
        BlogPost blogPost = getBlogPostById(id);

        Autore autore = autoreService.getAutoreById(blogPostRequest.getIDAutore());

        blogPost.setContenuto(blogPostRequest.getContenuto());
        blogPost.setCategoria(blogPostRequest.getCategoria());
        blogPost.setCover(blogPostRequest.getCover());
        blogPost.setTitolo(blogPostRequest.getTitolo());
        blogPost.setTempoLettura(blogPostRequest.getTempoLettura());
        blogPost.setAutore(autore);

        return blogPostRepository.save(blogPost);

    }

    public void deleteBlogPost(int id) throws NotFoundException {
        BlogPost blogPost = getBlogPostById(id);

        blogPostRepository.delete(blogPost);
    }
}
