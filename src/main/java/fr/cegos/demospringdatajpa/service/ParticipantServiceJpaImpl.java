package fr.cegos.demospringdatajpa.service;

import fr.cegos.demospringdatajpa.bo.Participant;
import fr.cegos.demospringdatajpa.repository.ParticipantRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("jpa")
@AllArgsConstructor // crée le constructeur avec arguments (plus besoin d'Autowired pour participantRepository)
public class ParticipantServiceJpaImpl implements ParticipantService {

    private ParticipantRepository participantRepository;

    @Override
    public List<Participant> listParticipants() {
        // je délègue le traitement à la couche Repository
        return participantRepository.findAll();
    }

    @Override
    public Participant addParticipant(Participant participant) {
        // je délègue le traitement à la couche Repository
        return participantRepository.save(participant);
    }

    @Override
    public void saveParticipant(Participant participant) {
        // je délègue le traitement à la couche Repository
        participantRepository.save(participant);
    }

    @Override
    public void deleteParticipant(Long id) {
        // je délègue le traitement à la couche Repository
        participantRepository.deleteById(id);
    }

    @Override
    public Participant getParticipant(Long id) {
        return null;
    }


}
