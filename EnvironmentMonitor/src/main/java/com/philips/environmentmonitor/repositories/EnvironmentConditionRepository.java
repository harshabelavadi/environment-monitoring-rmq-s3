package com.philips.environmentmonitor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.philips.environmentmonitor.models.EnvironmentCondition;

@Repository
public interface EnvironmentConditionRepository extends JpaRepository<EnvironmentCondition, Long>{
}
