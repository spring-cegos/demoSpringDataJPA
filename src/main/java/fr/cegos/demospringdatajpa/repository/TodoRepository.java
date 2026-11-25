package fr.cegos.demospringdatajpa.repository;

import fr.cegos.demospringdatajpa.bo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Lorsque je crée un repository, Spring Boot me crée une interface
 * qui étend l'interface générique JpaRepository<Type, TypeClePrimaire>
 *     - le premier argument correspond au type de la classe pour laquelle je veux un repository (ici : Todo)
 *     - le deuxième argument correspond au type de la clé primaire de cette classe (ici : Long id)
 *
 * Spring va AUTOMATIQUEMENT créer une classe qui implémente cette interface
 * avec les méthodes save()/ findAll()/etc… et la rendre disponible dans le contexte
 * comme un bean
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {
    /**
     * JPA Query Methods
     *
     * Si on définit dans notre repository une méthode avec une convention de nommage
     * qui répond aux specifications de JPA Query Methods : https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html#page-title
     *
     * Ici :
     * - findAll : renvoie une liste de Todo
     * - By Name : en faisant un filtre sur l'attrinut "name"
     * - ContainsIgnoreCase : condition de filtre : l'attribut "name" contient (pas de différence sur les majuscules/minuscule)
     */
    // Méthode qui renvoie les tâches dont le nom contient le terme de recherche en paramètre (pas de différence de casse)
    List<Todo> findAllByNameContainsIgnoreCase(String termeRecherche);

    // Méthode qui renvoie les tâches
    // dont le nom ou le prenom
    // du responsable contient le terme de recherche en paramètre (pas de différence de casse)
    @Query("select t from Todo t " +
            "where t.responsable.prenom like %:search% " +
            "OR t.responsable.nom  like %:search%")
    List<Todo> rechercheResponsable(String search);



    // TODO
    // Méthode qui renvoie les tâches dont le nom
    // Ou le prenom OU le nom d'un des participants contient le terme de recherche en paramètre (pas de différence de casse)
    @Query("select t from Todo t " +
            "left join t.participants p " +
            "where t.name like %:search% " +
            "OR p.prenom like %:search% " +
            "OR p.nom  like %:search%")
    List<Todo> recherche(String search);
}
