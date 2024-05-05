package com.gestiontemloignafes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity

public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;

    @Column(length = 1000)
    private String photo;
    private String description;
    private int quantiter;

    private double prix;
    private boolean Livrable = false;
    private String statut;
    private double prixvente;


    @JsonIgnoreProperties("article")
    @OneToMany(mappedBy = "article")
    private List<Commentaire> commentaires;


}
