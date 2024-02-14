package it.epicode.w6d3p.repository;

import it.epicode.w6d3p.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost,Integer>, PagingAndSortingRepository<BlogPost,Integer> {
}
