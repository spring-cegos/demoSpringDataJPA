package fr.cegos.demospringdatajpa.service;



import fr.cegos.demospringdatajpa.bo.Participant;
import fr.cegos.demospringdatajpa.bo.Todo;
import jakarta.validation.Valid;

import java.util.List;

public interface TodoService {
    List<Todo> listTodos();
    Todo addTodo(Todo todo);
    void saveTodo(Todo todo);
    void deleteTodo(Long id);
    Todo getTodo(Long id);
    List<Todo> searchTodos(String termeRecherche);
    void ajoutParticipant(Long idTache, Participant participant);
}