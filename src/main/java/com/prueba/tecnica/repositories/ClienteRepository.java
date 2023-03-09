package com.prueba.tecnica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prueba.tecnica.entities.ClienteDO;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteDO, Long>{

	@Query("SELECT c FROM ClienteDO c WHERE idCliente=?1")
	ClienteDO getByIdCliente(Long idCliente);
}
