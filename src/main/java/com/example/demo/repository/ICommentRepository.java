package com.example.demo.repository;

import com.example.demo.entity.CommentEntity;
import com.example.demo.entity.enumerador.CommentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<CommentEntity, Long> {
    @Query("SELECT c FROM CommentEntity c WHERE extract(year from c.createdDate) = ?1 AND extract(month from c.createdDate) = ?2 AND extract(day from c.createdDate) = ?3")
    List<CommentEntity> findByCreatedYearAndMonthAndDay(int year, int month, int day);

    List<CommentEntity> findByType(CommentType pType);
}
