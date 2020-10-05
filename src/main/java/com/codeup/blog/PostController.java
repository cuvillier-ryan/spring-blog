package com.codeup.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String showAllPosts() {
        return "Here all the posts, my brotha!";
    }


    @GetMapping("/posts/{id}")
    @ResponseBody
    public String showOnePost(@PathVariable int id) {
        return String.format("Here is post number %d", id)
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createPostForm() {
        return "Tell me about your day!";
    }

    @RequestMapping(path = "posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String sendingCreatedPostToDB(){
        return "Sending your diary page to be seen by everyone!";
    }

//    @GetMapping("/posts/{id}")
//    @ResponseBody
//
//    @GetMapping("/posts/create")
//    @ResponseBody


}
