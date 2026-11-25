package fr.cegos.demospringdatajpa.repository;

import fr.cegos.demospringdatajpa.bo.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
