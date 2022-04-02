package com.femissiameri.api.repository;

import com.femissiameri.api.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server, Long> {
    Server findByIpAddress(String idAddress);
}
