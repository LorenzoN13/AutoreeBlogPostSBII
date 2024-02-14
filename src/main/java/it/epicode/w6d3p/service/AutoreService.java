package it.epicode.w6d3p.service;

import it.epicode.w6d3p.Exception.NotFoundException;
import it.epicode.w6d3p.repository.AutoreRepository;
import it.epicode.w6d3p.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import it.epicode.w6d3p.model.Autore;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
public class AutoreService {
    @Autowired
    private AutoreRepository autoreRepository;

    private List<Autore> autori = new ArrayList<>();

    public Page<Autore> getAll(Pageable pageable){
        return autoreRepository.findAll(pageable);
    }

    public Autore getAutoreById(int id) throws NotFoundException{
        return autoreRepository.findById(id).orElseThrow(()->new NotFoundException("Persona con id=" + id + " non trovata"));
    }

    public Autore saveAutore(Autore autore){
        return autoreRepository.save(autore);
    }

    public Autore updateAutore(int id, Autore autore) throws NotFoundException {
        Autore a = getAutoreById(id);

        a.setNome(autore.getNome());
        a.setCognome(autore.getCognome());
        a.setEmail(autore.getEmail());
        a.setDataNascita(autore.getDataNascita());
        a.setAvatar(autore.getAvatar());

        return autoreRepository.save(a);
    }

    public void deleteAutore(int id) throws NotFoundException {
        Autore autore = getAutoreById(id);
        autoreRepository.delete(autore);
    }
}
