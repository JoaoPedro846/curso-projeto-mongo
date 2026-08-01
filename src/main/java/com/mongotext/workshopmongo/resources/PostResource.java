package com.mongotext.workshopmongo.resources;

import com.mongotext.workshopmongo.domain.Post;
import com.mongotext.workshopmongo.resources.util.URL;
import com.mongotext.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        List<Post> list = service.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/fullsearch")
    public ResponseEntity<List<Post>> fullsearch(@RequestParam(value="text", defaultValue = "") String text,
                                                 @RequestParam(value="minDate", defaultValue = "") String minDate,
                                                 @RequestParam(value="maxDate", defaultValue = "") String maxDate) {
        text = URL.decodeParam(text);
        Instant min = URL.convertDate(minDate, Instant.EPOCH);
        Instant max = URL.convertDate(maxDate, Instant.now());

        List<Post> list = service.fullSearch(text, min, max);
        return ResponseEntity.ok().body(list);
    }
}
