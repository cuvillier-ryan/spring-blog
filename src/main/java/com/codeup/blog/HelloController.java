package com.codeup.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello from Spring!";
    }

    @GetMapping("/hello/{name}")
    @ResponseBody
    public String sayHello(@PathVariable String name) {
        return "Hello " + name + "!";
    }

    @GetMapping("/join")
    public String showJoinForm() {
        return "join";
    }

    @PostMapping("/join")
    public String joinCohort(@RequestParam(name = "cohort") String cohort, Model model) {
        model.addAttribute("cohort", "Welcome to " + cohort + "!");
        if (cohort != null && !cohort.equals("")) {
            List<String> shoppingCart = new ArrayList<>();
            shoppingCart.add("Bread");
            shoppingCart.add(("Butter"));
            shoppingCart.add("eggs");
            model.addAttribute("shoppingCart", shoppingCart);
        }

        // create error message
        String message = "Sending messages works.";
        model.addAttribute("page_has_error", message);

        return "join";
    }

}