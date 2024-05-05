package com.gestiontemloignafes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String object;
    private String message;
    private Date date;

    @JsonIgnoreProperties({"commentaires"})
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnoreProperties({"commentaires"})
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;



}
