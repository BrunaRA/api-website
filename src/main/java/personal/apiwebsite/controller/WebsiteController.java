package personal.apiwebsite.controller;

import personal.apiwebsite.model.dto.PostDTO;
import personal.apiwebsite.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/api/website")
@Validated
public class WebsiteController {

    private final PostService postService;

    public WebsiteController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/fetch-posts")
    public ResponseEntity<String> fetchAndStorePosts() {
        postService.fetchAndStorePosts();
        return ResponseEntity.ok("Posts fetched and stored successfully.");
    }

    @GetMapping("/dashboard")
    public List<PostDTO> dashboard() {
        return postService.getPosts();
    }

}