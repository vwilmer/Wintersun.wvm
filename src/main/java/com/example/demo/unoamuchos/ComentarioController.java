package com.example.demo.unoamuchos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ComentarioController {

    private IComentarioRepository comentarioRepository;
    private IPostRepository postRepository;

    @Autowired
    public ComentarioController(IComentarioRepository comentarioRepository,
                                IPostRepository postRepository) {
        this.comentarioRepository = comentarioRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    @GetMapping("/posts/{postId}/comments")
    public Page<ComentarioEntity> getAllCommentsByPostId(@PathVariable(value = "postId") Long postId,
                                                         Pageable pageable) {
        return comentarioRepository.findByPostId(postId, pageable);
    }

    @PostMapping("/posts/{postId}/comments")
    public ComentarioEntity createComment(@PathVariable(value = "postId") Long postId,
                                          @Valid @RequestBody ComentarioEntity comment) {
        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            return comentarioRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ComentarioEntity updateComment(@PathVariable(value = "postId") Long postId,
                                          @PathVariable(value = "commentId") Long commentId,
                                          @Valid @RequestBody ComentarioEntity commentRequest) {
        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }

        return comentarioRepository.findById(commentId).map(comment -> {
            comment.setText(commentRequest.getText());
            return comentarioRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable(value = "postId") Long postId,
                                           @PathVariable(value = "commentId") Long commentId) {
        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }

        return comentarioRepository.findById(commentId).map(comment -> {
            comentarioRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + " not found"));
    }

}
