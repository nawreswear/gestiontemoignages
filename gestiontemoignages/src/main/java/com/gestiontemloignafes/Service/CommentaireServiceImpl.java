package com.gestiontemloignafes.Service;


import com.gestiontemloignafes.Repository.CommentaireRepository;
import com.gestiontemloignafes.model.Commentaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentaireServiceImpl implements CommentaireService {

    @Autowired
    private CommentaireRepository commentaireRepository;

    @Override
    public List<Commentaire> findAll() {
        return commentaireRepository.findAll();
    }

    @Override
    public Commentaire findById(Long id) {
        return commentaireRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commentaire introuvable avec l'identifiant : " + id));
    }
    @Override
    public boolean existsById(Long id) {
        return commentaireRepository.existsById(id);
    }
    @Override
    public Commentaire save(Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }
    @Override
    public List<Commentaire> findByArticleId(Long idArticle) {
        return commentaireRepository.findByArticleId(idArticle);
    }
    @Override
    public void delete(Long id) {
        commentaireRepository.deleteById(id);
    }
    public Commentaire findByIdAndArticleId(Long idCommentaire, Long idArticle) {
        return commentaireRepository.findByIdAndArticleId(idCommentaire, idArticle);
    }

    // Méthode pour supprimer un commentaire par son ID
    public void deleteById(Long idCommentaire) {
        commentaireRepository.deleteById(idCommentaire);
    }

    @Override
    public Commentaire updateCommantaire(Commentaire commentaire, Long idCommentaire) {
        Commentaire existingCommentaire = findById(idCommentaire);
        existingCommentaire.setObject(commentaire.getObject());
        existingCommentaire.setMessage(commentaire.getMessage());
        existingCommentaire.setDate(commentaire.getDate());
        return commentaireRepository.save(existingCommentaire);
    }
}
