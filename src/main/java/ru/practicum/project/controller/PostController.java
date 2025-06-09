package ru.practicum.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.practicum.project.model.Post;

@Controller
@RequestMapping("/posts")
public class PostController {

	@GetMapping
	public String showPostsPage(Model model) {
	   return "posts";
	}

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("post", null);
        return "add-post";
    }
    
    @GetMapping("/{id}")
    public String showPostById(@PathVariable Long id, Model model) {
        Post post = postService.findById(id); 
        model.addAttribute("post", post);
        return "post";
    }
}
