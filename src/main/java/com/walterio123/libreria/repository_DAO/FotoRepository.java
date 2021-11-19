package com.walterio123.libreria.repository_DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.walterio123.libreria.entity.Foto;

@Repository
public interface FotoRepository extends JpaRepository<Foto, String>{

}
