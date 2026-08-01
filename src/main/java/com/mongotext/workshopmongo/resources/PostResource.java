package com.mongotext.workshopmongo.resources;

import com.mongotext.workshopmongo.domain.Post;
import com.mongotext.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

}
