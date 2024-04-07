package com.example.WetherInformation.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.WetherInformation.models.Event;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{
    List<Event> findByDateBetween(LocalDate date, LocalDate endDate);
}
