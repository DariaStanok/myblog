package ru.practicum.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ru.practicum.project.model.Post;

@Service
public interface PostService {

	List<Post> findAll ();
	
	Post save (Post post, MultipartFile image);
	
	Post update (Long id, Post updatePost, MultipartFile image);
	
	void deleteById (Long Id);
	
	void like (Long id, boolean like);
	
}
