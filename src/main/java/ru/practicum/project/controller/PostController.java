package ru.practicum.project.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ru.practicum.project.model.Post;
import ru.practicum.project.service.CommentService;
import ru.practicum.project.service.PostService;

@Controller
@RequestMapping("/posts")
public class PostController {

	private final PostService service;
	private final CommentService commentService;

    public PostController(PostService service, CommentService commentService) {
        this.service = service;
        this.commentService = commentService; 
    }
    
    
    /**
     * Главная страница ленты постов.
     */
    @GetMapping
    public String showPostsPage(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "1") int pageNumber,
            Model model) {
        model.addAttribute("posts", service.findAll(search, pageSize, pageNumber));
        model.addAttribute("search", search);
        model.addAttribute("paging", Map.of(
                "pageSize", pageSize,
                "pageNumber", pageNumber
        ));
        return "posts";
    }

    /**
     * Просмотр одного поста с комментариями.
     */
    @GetMapping("/{id}")
    public String showPostPage(@PathVariable Long id, Model model) {
        model.addAttribute("post", service.findById(id));
        return "post";
    }

    /**
     * Форма добавления нового поста.
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("post", new Post());
        return "post-add";
    }

    /**
     * Создание нового поста.
     */
    @PostMapping
    public String addPost(
            @ModelAttribute Post post,
            @RequestParam MultipartFile image) throws IOException {
        Post saved = service.save(post, image);
        return "redirect:/posts/" + saved.getId();
    }

    /**
     * Форма редактирования поста.
     */
    @GetMapping("/{id}/edit")
    public String editPostPage(@PathVariable Long id, Model model) {
        model.addAttribute("post", service.findById(id));
        return "post-add";
    }

    /**
     * Сохранение изменений поста.
     */
    @PostMapping("/{id}")
    public String editPost(
            @PathVariable Long id,
            @ModelAttribute Post post,
            @RequestParam(required = false) MultipartFile image) throws IOException {
        service.update(id, post, image);
        return "redirect:/posts/" + id;
    }

    /**
     * Удаление поста.
     */
    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id) throws IOException {
        service.delete(id);
        return "redirect:/posts";
    }

    /**
     * Лайк/дизлайк поста.
     */
    @PostMapping("/{id}/like")
    public String like(
            @PathVariable Long id,
            @RequestParam boolean like) {
        service.like(id, like);
        return "redirect:/posts/" + id;
    }

    /**
     * Добавление комментария к посту.
     */
    @PostMapping("/{id}/comments")
    public String addComment(
            @PathVariable Long id,
            @RequestParam String text) {
        commentService.addComment(id, text);
        return "redirect:/posts/" + id;
    }

    /**
     * Редактирование комментария.
     */
    @PostMapping("/{id}/comments/{commentId}")
    public String editComment(
            @PathVariable Long id,
            @PathVariable Long commentId,
            @RequestParam String text) {
        commentService.editComment(id, commentId, text);
        return "redirect:/posts/" + id;
    }

    /**
     * Удаление комментария.
     */
    @PostMapping("/{id}/comments/{commentId}/delete")
    public String deleteComment(
            @PathVariable Long id,
            @PathVariable Long commentId) {
        commentService.deleteComment(id, commentId);
        return "redirect:/posts/" + id;
    }
}
