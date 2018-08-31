package com.example.demo.repository;

import com.example.demo.entity.ArchivoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IArchivoRepository extends JpaRepository<ArchivoEntity, Long> {

}
