package ru.practicum.project.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository {

    void save(Long postId, String text);
    
    void update(Long commentId, String text);
    
    void delete(Long commentId);
	
}
