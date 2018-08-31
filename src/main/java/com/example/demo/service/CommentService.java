package com.example.demo.service;

import com.example.demo.entity.CommentEntity;
import com.example.demo.entity.enumerador.CommentType;
import com.example.demo.repository.ICommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CommentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentService.class);

    private final ICommentRepository commentRepository;

    public CommentService(ICommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<CommentEntity> saveAll(List<CommentEntity> comments) {
        LOGGER.info("Saving {}", comments);
        return commentRepository.saveAll(comments);
    }

    public List<CommentEntity> getAllCommentsForToday() {
        LocalDate localDate = LocalDate.now();
        return commentRepository.findByCreatedYearAndMonthAndDay(localDate.getYear(), localDate.getMonth().getValue(), localDate.getDayOfMonth());
    }

    public List<CommentEntity> getByType(CommentType type) {
        LOGGER.info("Finding... ", type);
        return this.commentRepository.findByType(type);
    }
}
