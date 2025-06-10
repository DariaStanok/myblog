package ru.practicum.project.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ru.practicum.project.dao.PostRepository;
import ru.practicum.project.model.Post;

public class PostServiceImpl {

	private final PostRepository postRepository;
	
	public PostServiceImpl (PostRepository postRepository) {
		this.postRepository = postRepository;
	};
	
	public List<Post> findAll(String search, int pageSize, int pageNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Post save(Post post, MultipartFile image) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Post update(Long id, Post updatePost, MultipartFile image) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void deleteById(Long Id) {
		// TODO Auto-generated method stub
		
	}

	public void like(Long id, boolean like) {
		// TODO Auto-generated method stub
		
	}


}
