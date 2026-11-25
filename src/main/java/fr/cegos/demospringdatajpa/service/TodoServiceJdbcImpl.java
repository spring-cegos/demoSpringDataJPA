package fr.cegos.demospringdatajpa.service;

import fr.cegos.demospringdatajpa.bo.Participant;
import fr.cegos.demospringdatajpa.bo.Todo;
import fr.cegos.demospringdatajpa.dao.TodoDAO;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("jdbc")
@AllArgsConstructor // crée le constructeur avec arguments (plus besoin d'Autowired pour todoDAO)
public class TodoServiceJdbcImpl implements TodoService {

    private TodoDAO todoDAO;

    @Override
    public List<Todo> listTodos() {
        // je délègue le traitement à la couche DAO
        return todoDAO.findAll();
    }

    @Override
    public Todo addTodo(Todo todo) {
        // je délègue le traitement à la couche DAO
        return todoDAO.save(todo);
    }

    @Override
    public void saveTodo(Todo todo) {
        // je délègue le traitement à la couche DAO
        todoDAO.update(todo);
    }

    @Override
    public void deleteTodo(Long id) {
        // je délègue le traitement à la couche DAO
        todoDAO.deleteById(id);
    }

    @Override
    public Todo getTodo(Long id) {
        return null;
    }

    @Override
    public List<Todo> searchTodos(String termeRecherche) {
        // je délègue le traitement à la couche DAO
        return todoDAO.search(termeRecherche);
    }

    @Override
    public void ajoutParticipant(Long idTache, Participant participant) {

    }
}
