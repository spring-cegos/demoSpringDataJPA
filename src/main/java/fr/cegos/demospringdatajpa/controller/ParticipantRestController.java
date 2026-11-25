package fr.cegos.demospringdatajpa.controller;


import fr.cegos.demospringdatajpa.bo.Participant;
import fr.cegos.demospringdatajpa.service.ParticipantService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participants")
@CrossOrigin // @CrossOrigin : permet d'accéder à l'application depuis un Front situé à un origine différente (port ou adresse différente)
class ParticipantRestController {
    @Autowired //possible également de créer un constructeur avec argument pour le controller
    private ParticipantService participantService;

    /**
     * GetMapping
     * Affiche la liste des taches
     *
     * On a ajouté un terme de recherche :
     * @RequestParam(required = false) String termeRecherche
     * afin de permettre les requêtes de type : GET http://localhost:8080/participant?termeRecherche=faire
     */
    @Operation(summary = "récupérer la liste des participants") // sert à la doc d'API
    @GetMapping
    public List<Participant> getParticipant(){
        return participantService.listParticipants();
    }

    /**
     * GetMapping
     * Affiche un participant spécifique
     */
    @Operation(summary = "récupérer un participant") // sert à la doc d'API
    @GetMapping("/{id}")
    public Participant getParticipant(@PathVariable Long id){
        return participantService.getParticipant(id);
    }

    /**
     * PostMapping
     * Crée un nouvelle participant
     */
    @Operation(summary = "créer un participant") // sert à la doc d'API
    @PostMapping
    public void postParticipant(@Valid @RequestBody Participant participant){ // @Valid = l'objet de type Participant doit répondre aux cntraintes de validations, sinon, un erreur sera retournée
        participantService.addParticipant(participant);
    }

    /**
     * PutMapping
     * Modifie un  participant
     *
     * note : l'utilisation de /{id} dans la route et de @PathVariable Long id en argument
     * me permet de récupérer la valeur de l'id de tache dans l'url de la requête
     * exemple : http://localhost:8080/participant/3 => id=3
     */
    @Operation(summary = "modifier un participant") // sert à la doc d'API
    @PutMapping("/{id}")
    public void putParticipant(@PathVariable Long id, @Valid @RequestBody Participant participant){
        participant.setId(id);
        participantService.saveParticipant(participant);
    }


    /**
     * DeleteMapping
     * Supprime un participant
     *
     * note : l'utilisation de /{id} dans la route et de @PathVariable Long id en argument
     * me permet de récupérer la valeur de l'id de tache dans l'url de la requête
     * exemple : http://localhost:8080/participant/3 => id=3
     */
    @Operation(summary = "supprimer un participant") // sert à la doc d'API
    @DeleteMapping("/{id}")
    public void deleteParticipant(@PathVariable Long id){
        participantService.deleteParticipant(id);
    }

}
