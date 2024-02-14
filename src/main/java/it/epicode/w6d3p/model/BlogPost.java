package it.epicode.w6d3p.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Random;

@Data
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String contenuto;
    private String titolo;
    private String cover = "https://picsum.photos/200/300";
    private String categoria;
    private int tempoLettura;

    @ManyToOne
    @JoinColumn(name = "autore_id")
    private Autore autore;
}
