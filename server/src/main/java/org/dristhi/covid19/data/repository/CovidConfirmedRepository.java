package org.dristhi.covid19.data.repository;

import org.dristhi.covid19.data.entity.CountyFipsStateId;
import org.dristhi.covid19.data.entity.CovidConfirmed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CovidConfirmedRepository
        extends JpaRepository<CovidConfirmed, CountyFipsStateId> {
}
