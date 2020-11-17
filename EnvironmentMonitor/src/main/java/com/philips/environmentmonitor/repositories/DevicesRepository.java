package com.philips.environmentmonitor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.philips.environmentmonitor.models.Devices;

@Repository
public interface DevicesRepository extends JpaRepository<Devices, Long>{
}
