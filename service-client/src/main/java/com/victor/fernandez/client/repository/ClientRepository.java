package com.victor.fernandez.client.repository;

import com.victor.fernandez.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByCodigoUnico(String codigoUnico);
    Optional<Client> findByNumeroDocumento(String numeroDocumento);
}
