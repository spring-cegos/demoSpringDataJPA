package fr.cegos.demospringdatajpa.repository;

import fr.cegos.demospringdatajpa.bo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
