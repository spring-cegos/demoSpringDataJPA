package fr.cegos.demospringdatajpa.dao;

import fr.cegos.demospringdatajpa.bo.Todo;

import java.util.List;

/**
 * Interface qui définie les fonctionnalités nécessaires
 * pour l'interaction avec la base de donnée
 */
public interface TodoDAO {
    List<Todo> findAll();
    List<Todo> search(String search);
    Todo save(Todo todo);
    void update(Todo todo);
    void deleteById(Long id);
}