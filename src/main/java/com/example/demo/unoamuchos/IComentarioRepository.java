package com.example.demo.unoamuchos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComentarioRepository extends JpaRepository<ComentarioEntity, Long> {

    Page<ComentarioEntity> findByPostId(Long postId, Pageable pageable);

}
