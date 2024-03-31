package com.aplication.prco.repository;

import com.aplication.prco.data.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
    
}
