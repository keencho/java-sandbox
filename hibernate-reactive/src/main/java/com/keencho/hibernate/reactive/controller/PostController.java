package com.keencho.hibernate.reactive.controller;

import com.keencho.hibernate.reactive.model.PostDTO;
import com.keencho.hibernate.reactive.repository.PostRepository;
import io.smallrye.mutiny.Uni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping
    public ResponseEntity<?> listAll() {
        return buildResponse(postRepository.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return buildResponse(postRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PostDTO data) {
        return buildResponse(postRepository.save(data));
    }

    private ResponseEntity<?> buildResponse(Uni<?> result) {
        return ResponseEntity.ok().body(result);
    }

}
