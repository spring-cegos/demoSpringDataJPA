package fr.cegos.demospringdatajpa.service;

import fr.cegos.demospringdatajpa.bo.Participant;
import fr.cegos.demospringdatajpa.bo.Todo;
import fr.cegos.demospringdatajpa.repository.ParticipantRepository;
import fr.cegos.demospringdatajpa.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * @Transactional
     * Etant donné que ma méthode possède 2 instructions qui effectuent des traitements en base de donnée
     * Il est important que ces 2 instrutions réussissent ou échouent, mais pas à moitié.
     * Pour garantir cela, on annote la méthode avec @Transactional
     * => si la première ou deuxième instruction échoue, toute la transaction effectue un "RollBack" et rien n'est créé (pas d'incohérence de données)
     */
    @Transactional
    @Override
    public Todo addTodo(Todo todo) {
        // instruction 1 - je sauvegarde le responsable si pas existant en base de donnée
        if (todo.getResponsable() != null){
            participantRepository.save(todo.getResponsable());
        }

        // instruction 2 - je sauvegarde la tache
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
