package com.example.demo.repository;

import com.example.demo.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
  //  List<Match> getMatchesByMascotaId(Long mascotaId);

  //  @Query("SELECT ...") para saber cuantos matches tiene mi mascota. Se hace a través de query de SQL.
}
