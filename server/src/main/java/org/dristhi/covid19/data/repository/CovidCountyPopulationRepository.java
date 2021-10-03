package org.dristhi.covid19.data.repository;

import java.util.List;
import org.dristhi.covid19.data.entity.CovidCountyPopulation;
import org.dristhi.covid19.data.entity.CovidCountyPopulationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CovidCountyPopulationRepository extends JpaRepository<CovidCountyPopulation, CovidCountyPopulationId> {

    List<CovidCountyPopulation> findByStateAndName(String state, String name);
}
