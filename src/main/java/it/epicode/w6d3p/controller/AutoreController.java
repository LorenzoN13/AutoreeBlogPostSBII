package it.epicode.w6d3p.controller;

import it.epicode.w6d3p.Exception.NotFoundException;
import it.epicode.w6d3p.model.Autore;
import it.epicode.w6d3p.model.CustomResponse;
import it.epicode.w6d3p.service.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AutoreController {
    @Autowired
    private AutoreService autoreService;
    @GetMapping("/autori")
    public ResponseEntity<CustomResponse> getAll(Pageable pageable){
        try {
            return CustomResponse.success(HttpStatus.OK.toString(), autoreService.getAll(pageable), HttpStatus.OK);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/autori/{id}")
    public ResponseEntity<CustomResponse> getAutoreById(@PathVariable int id){
        try {
            return CustomResponse.success(HttpStatus.OK.toString(), autoreService.getAutoreById(id), HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/autori")
    public ResponseEntity<CustomResponse> saveAutore(@RequestBody Autore autore){
        try{
            return CustomResponse.success(HttpStatus.OK.toString(), autoreService.saveAutore(autore), HttpStatus.OK);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/autori/{id}")
    public ResponseEntity<CustomResponse> updateAutore(@PathVariable int id, @RequestBody Autore autore){
        try {
            return CustomResponse.success(HttpStatus.OK.toString(), autoreService.updateAutore(id, autore), HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/autori/{id}")
    public ResponseEntity<CustomResponse> deleteAutore(@PathVariable int id){
        try{
            autoreService.deleteAutore(id);
            return CustomResponse.emptyResponse("Persona con id=" + id + " cancellata", HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
