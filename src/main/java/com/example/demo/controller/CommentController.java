package com.example.demo.controller;

import com.example.demo.entity.CommentEntity;
import com.example.demo.entity.enumerador.CommentType;
import com.example.demo.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController()
public class CommentController {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> getListado() {
        List<CommentEntity> allComments = commentService.getAllCommentsForToday();
        Map<CommentType, List<CommentEntity>> groupedComments = allComments.stream().collect(Collectors.groupingBy(CommentEntity::getType));

        return new ResponseEntity<>(groupedComments, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createComment(@RequestParam(name = "plusComment", required = false) String pPlusComment,
                                           @RequestParam(name = "deltaComment", required = false) String pDeltaComment,
                                           @RequestParam(name = "starComment", required = false) String pStartComment) {

        List<CommentEntity> comments = new ArrayList<>();

        if (pPlusComment != null && !pPlusComment.isEmpty()) {
            comments.add(getComment(pPlusComment, CommentType.PLUS));
        }

        if (!pDeltaComment.isEmpty()) {
            comments.add(getComment(pDeltaComment, CommentType.DELTA));
        }

        if (!pStartComment.isEmpty()) {
            comments.add(getComment(pStartComment, CommentType.STAR));
        }

        if (!comments.isEmpty()) {
            LOGGER.info("Saved {}", commentService.saveAll(comments));
        }

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    private CommentEntity getComment(String comment, CommentType commentType) {
        CommentEntity commentObject = new CommentEntity();
        commentObject.setType(commentType);
        commentObject.setComment(comment);

        return commentObject;
    }

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public String getName(Authentication authentication, Principal principal) {
//        System.out.println(authentication.getName());
//        System.out.println("-----------------");
//        System.out.println(principal.getName());
        return "principal.getName()";
    }
}

