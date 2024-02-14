package it.epicode.w6d3p.repository;

import it.epicode.w6d3p.model.Autore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoreRepository extends JpaRepository<Autore,Integer>, PagingAndSortingRepository<Autore,Integer> {
}
