package com.codeup.blog.controllers;

import com.codeup.blog.models.Ad;
import com.codeup.blog.models.Post;
import com.codeup.blog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private final PostRepository postRepo;

    public PostController(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @GetMapping("/post")
    public String showPosts(Model model) {
        List<Post> postList = new ArrayList<>();
        postList.add(new Post("First Post", "This is the body of the first test post."));
        postList.add(new Post("Second Post", "This is the body of the second test post."));
        model.addAttribute("postList", postList);
        return "posts/index";
    }

    @GetMapping("/post/{id}")
    public String showPost(@PathVariable Integer id, Model model) {
        Post post = new Post("A Single Post", "This is the body of a single test post.");
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/post/create")
    public String showCreatePost() {
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@RequestParam(name = "title") String title,
                             @RequestParam(name = "body") String body,
                             Model model) {
        Post post = new Post(title, body);
        postRepo.save(post);
        return "redirect:/ads/" + post.getId();
    }

    @GetMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id, Model model) {
        Post post = postRepo.getPostById(id);
        if (post != null) {
            postRepo.delete(post);
        }
        return "posts/index";
    }

    @GetMapping("/posts/edit/{id}")
    public String editPost(@PathVariable long id, Model model) {
        Post post = postRepo.getPostById(id);
        if (post != null) {
            postRepo.delete(post);
            return "redirect:/post/index";
        }
        model.addAttribute("post", post);
        return "posts/index";
    }

    @PostMapping("/posts/edit")
    public String updateAd(@RequestParam(name = "id") long id,
                           @RequestParam(name = "title") String title,
                           @RequestParam(name = "body") String body) {
        Post post = new Post();
        post.setId(id);
        post.setTitle(title);
        post.setBody(body);
        postRepo.save(post);
        return "redirect:/ads/" + post.getId();
    }
}