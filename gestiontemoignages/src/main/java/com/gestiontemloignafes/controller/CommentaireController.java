package com.gestiontemloignafes.controller;


import com.gestiontemloignafes.Repository.ArticleRepository;
import com.gestiontemloignafes.Service.ArticleService;
import com.gestiontemloignafes.Service.CommentaireService;
import com.gestiontemloignafes.model.Article;
import com.gestiontemloignafes.model.Commentaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/commentaire")
public class CommentaireController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentaireService commentaireService;
    ArticleRepository articleRepository;
    @PostMapping("/article/{idArticle}")
    public ResponseEntity<Commentaire> ajouterCommentairePourArticle(@PathVariable Long idArticle, @RequestBody Commentaire commentaire) {
        // Récupérer l'article
        Article article = articleService.findById(idArticle);
        // Vérifier si l'article existe
        if (article == null) {
            return ResponseEntity.notFound().build();
        }

        // Associer l'article au commentaire
        commentaire.setArticle(article);

        // Enregistrer le commentaire
        Commentaire nouveauCommentaire = commentaireService.save(commentaire);

        return new ResponseEntity<>(nouveauCommentaire, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Commentaire>> getAllCommantaires() {
        List<Commentaire> temoignages = commentaireService.findAll();
        return ResponseEntity.ok(temoignages);
    }
    @GetMapping("/article/{idArticle}/commentaires")
    public ResponseEntity<List<Commentaire>> getAllCommentairesByArticleId(@PathVariable Long idArticle) {
        List<Commentaire> commentaires = commentaireService.findByArticleId(idArticle);
        if (commentaires.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(commentaires);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commentaire> getCommantaireById(@PathVariable Long id) {
        Commentaire temoignage = commentaireService.findById(id);
        return ResponseEntity.ok(temoignage);
    }
    @DeleteMapping("/article/{idArticle}/commentaire/{idCommentaire}")
    public ResponseEntity<Void> supprimerCommentairePourArticle(@PathVariable Long idArticle, @PathVariable Long idCommentaire) {
        // Vérifier si l'article existe
        Article article = articleService.findById(idArticle);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }

        // Vérifier si le commentaire existe pour cet article
        Commentaire commentaire = commentaireService.findByIdAndArticleId(idCommentaire, idArticle);
        if (commentaire == null) {
            return ResponseEntity.notFound().build();
        }

        // Supprimer le commentaire
        commentaireService.deleteById(idCommentaire);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/article/{idArticle}/commentaire/{idCommentaire}")
    public ResponseEntity<Commentaire> mettreAJourCommentairePourArticle(
            @PathVariable Long idArticle,
            @PathVariable Long idCommentaire,
            @RequestBody Commentaire commentaireModifie) {
        try {
            // Valider les ID d'article et de commentaire
            if (idArticle == null || idCommentaire == null) {
                return ResponseEntity.badRequest().build();
            }

            // Récupérer l'article
            Article article = articleService.findById(idArticle);

            // Vérifier si l'article existe
            if (article == null) {
                return ResponseEntity.notFound().build();
            }

            // Récupérer le commentaire à mettre à jour
            Commentaire commentaireExistant = commentaireService.findById(idCommentaire);

            // Vérifier si le commentaire existe
            if (commentaireExistant == null) {
                return ResponseEntity.notFound().build();
            }

            // Vérifier si le commentaire appartient à l'article
            if (!commentaireExistant.getArticle().getId().equals(idArticle)) {
                return ResponseEntity.badRequest().build();
            }

            // Mettre à jour les informations du commentaire
            commentaireExistant.setObject(commentaireModifie.getObject());
            commentaireExistant.setMessage(commentaireModifie.getMessage());
            // Formater la date
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String formattedDate = sdf.format(new Date());

            // Convertir la date formatée en objet Date
            Date date = sdf.parse(formattedDate);

            // Mise à jour de la date du commentaire
            commentaireExistant.setDate(date);
            // Enregistrer le commentaire mis à jour
            Commentaire commentaireMisAJour = commentaireService.save(commentaireExistant);

            return ResponseEntity.ok(commentaireMisAJour);
        } catch (IllegalArgumentException e) {
            // ID invalide
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException e) {
            // Ressource introuvable
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Gérer les autres exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCommantaire(@PathVariable Long id) {
        // Vérifier si le commentaire existe
        if (!commentaireService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        // Supprimer le commentaire
        commentaireService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/commentaire/{id}/articleid")
    public ResponseEntity<Long> getArticleIdByCommentaireId(@PathVariable Long id) {
        // Vérifier si le commentaire existe
        Commentaire commentaire = commentaireService.findById(id);
        if (commentaire == null) {
            return ResponseEntity.notFound().build();
        }

        // Récupérer l'ID de l'article associé au commentaire
        Long articleId = commentaire.getArticle().getId();

        return ResponseEntity.ok(articleId);
    }

    @PostMapping
    public ResponseEntity<Commentaire> createCommantaire(@RequestBody Commentaire temoignage) {
        Commentaire createdTemoignage = commentaireService.save(temoignage);
        return new ResponseEntity<>(createdTemoignage, HttpStatus.CREATED);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Commentaire> updateCommantaire(@RequestBody Commentaire temoignage, @PathVariable Long id) {
        Commentaire updatedTemoignage = commentaireService.updateCommantaire(temoignage, id);
        return ResponseEntity.ok(updatedTemoignage);
    }




}
