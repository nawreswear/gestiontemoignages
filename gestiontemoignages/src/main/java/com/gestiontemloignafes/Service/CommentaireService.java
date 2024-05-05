package com.gestiontemloignafes.Service;



import com.gestiontemloignafes.model.Commentaire;

import java.util.List;

public interface CommentaireService {
    List<Commentaire> findAll();
    public Commentaire findByIdAndArticleId(Long idCommentaire, Long idArticle);
    Commentaire findById(Long id);
    public void deleteById(Long idCommentaire);

    boolean existsById(Long id);

    Commentaire save(Commentaire commentaire);
    List<Commentaire> findByArticleId(Long idArticle);
    void delete(Long id);

    //List<Commentaire> findByNomUtilisateur(String nom);

   // List<Commentaire> findByNomVendeur(String nom);

    Commentaire updateCommantaire(Commentaire commentaire, Long idCommentaire);
}
