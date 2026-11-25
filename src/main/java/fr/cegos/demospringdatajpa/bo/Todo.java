package fr.cegos.demospringdatajpa.bo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // @Entity : je spécifie que c'est une entité gérée par JPA
// possibilité de specifier un nom de table différent que la classe, ex : @Entity(name = "Tache")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotEmpty // validation : le nom d'une tache doit être non vice
    @Size(min = 3, max = 20) // validation : le nom d'une tache doit contenir entre 3 et 10 caractères
    @Column(name = "nom") // on peut définir des nom de colonnes particulier
    String name;
    @Future(message = "une tâche doit être dans le futur") // validation : la date limite d'une tache doit être dans le futur
    LocalDate dueDate;


    /**
     * ASSOCIATIONS
     */
    // @OneToMany : UNE tache possède PLUSIEURS sous-taches
    // correspond à la cardinalité 1 -- * d'un diagramme de classe
    @OneToMany
    // Si on ne veut pas de table de jointure pour une relation @ManyToOne
    // On peut specifier @JoinColumn
    @JoinColumn(name = "tache_parente")
    private List<Todo> sousTaches = new ArrayList<>();

    // @ManyToOne : UNE tache possède UN responsable (de type participant), mais un participant peut être responsable de plusieurs taches
    // correspond à la cardinalité * -- A d'un diagramme de classe
    @ManyToOne
    private Participant responsable;

    // @ManyToMany : UNE tache possède plusieurs participants travaillants dessus, mais un participant peut participer à plusieurs taches
    // correspond à la cardinalité * -- A d'un diagramme de classe
    // cascade = permet au besoin de créer/modifier/... l'entité liée lors de la création/modification... de l'entité
    // ATTENTION : cascade = CascadeType.ALL : implique que si je supprime une tache je supprime les participants associés
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Participant> participants = new ArrayList<>();
}