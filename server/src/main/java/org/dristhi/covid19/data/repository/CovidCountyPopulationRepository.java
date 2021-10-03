package org.dristhi.covid19.data.repository;

import org.dristhi.covid19.data.entity.CovidCountyPopulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CovidCountyPopulationRepository extends JpaRepository<CovidCountyPopulation, Integer> {
}
