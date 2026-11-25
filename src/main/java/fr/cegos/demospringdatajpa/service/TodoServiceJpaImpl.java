package fr.cegos.demospringdatajpa.service;

import fr.cegos.demospringdatajpa.bo.Participant;
import fr.cegos.demospringdatajpa.bo.Todo;
import fr.cegos.demospringdatajpa.repository.ParticipantRepository;
import fr.cegos.demospringdatajpa.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("jpa")
@AllArgsConstructor // crée le constructeur avec arguments (plus besoin d'Autowired pour todoRepository)
public class TodoServiceJpaImpl implements TodoService {

    private TodoRepository todoRepository;
    private ParticipantRepository participantRepository;

    @Override
    public List<Todo> listTodos() {
        // je délègue le traitement à la couche Repository
        return todoRepository.findAll();
    }

    @Override
    public Todo addTodo(Todo todo) {
        // je délègue le traitement à la couche Repository
        return todoRepository.save(todo);
    }

    @Override
    public void saveTodo(Todo todo) {
        // je délègue le traitement à la couche Repository
        todoRepository.save(todo);
    }

    @Override
    public void deleteTodo(Long id) {
        // je délègue le traitement à la couche Repository
        todoRepository.deleteById(id);
    }

    @Override
    public Todo getTodo(Long id) {
        return null;
    }

    @Override
    public List<Todo> searchTodos(String termeRecherche) {
        // je délègue le traitement à la couche Repository
        // return todoRepository.findAllByNameContainsIgnoreCase(termeRecherche); // todo : Query method pour la requête
        return todoRepository.recherche(termeRecherche); // todo : Query method pour la requête
    }

    @Override
    public void ajoutParticipant(Long idTache, Participant participant) {
        // 1 - je récupère la tache correspondante à l'id
        Todo todo =  todoRepository.findById(idTache).orElseThrow(() -> new EntityNotFoundException("pas de tache corespondante à l'id"));

        // 2 - si un id est renseigné je récupère le participant correspondante à son id
        if (participant != null && participant.getId() != null) {
            participant = participantRepository.findById(participant.getId()).orElseThrow(() -> new EntityNotFoundException("pas de participant corespondante à l'id"));
        }

        // 3 - j'ajoute le participant à la liste de la tache si pas déjà présent
        if (todo.getParticipants().contains(participant)) {
            return; // si participant déjà affecté à la tache, on sort
        }
        // sinon on l'ajoute
        todo.getParticipants().add(participant);

        // 4 - on sauvegarde la tache
        todoRepository.save(todo);
    }
}
