package com.gestiontemloignafes.Repository;


import com.gestiontemloignafes.model.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {

    List<Commentaire> findByArticleId(Long idArticle);
   // List<Commentaire> findByUtilisateurNom(String nom);


    Commentaire findByIdAndArticleId(Long idCommentaire, Long idArticle);
    //List<Commentaire> findByNomVendeur(String nom);

}
