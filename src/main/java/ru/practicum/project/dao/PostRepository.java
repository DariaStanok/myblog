package ru.practicum.project.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import ru.practicum.project.model.Post;

@Repository
public interface PostRepository {
	List<Post> findAll(int pageSize, int pageNumber, String tagFilter);
    
	Post findById(Long id);
    
	Long save(Post post);
    
	void update(Post post);
	
	void updateLikes(Long postId, int delta);
    
	void deleteById(Long id);
}
