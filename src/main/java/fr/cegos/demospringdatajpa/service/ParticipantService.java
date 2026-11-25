package fr.cegos.demospringdatajpa.service;



import fr.cegos.demospringdatajpa.bo.Participant;

import java.util.List;

public interface ParticipantService {
    List<Participant> listParticipants();
    Participant addParticipant(Participant todo);
    void saveParticipant(Participant todo);
    void deleteParticipant(Long id);
    Participant getParticipant(Long id);
}