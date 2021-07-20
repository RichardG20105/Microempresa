package com.mans.backMicroempresa.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mans.backMicroempresa.entidades.Administrador;

@Repository
public interface administradorServicios extends JpaRepository<Administrador, Long> {
	Boolean existsByUsuario(String usuario);
}
