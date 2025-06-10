package ru.practicum.project.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcNativeCommentRepository  {

	private final JdbcTemplate jdbcTemplate;
	
	public JdbcNativeCommentRepository (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void save(Long postId, String text) {
	   jdbcTemplate.update("insert into comments (post_id, text) values (?, ?)", postId, text);
	}

	public void update(Long commentId, String text) {
        jdbcTemplate.update("update comments set text = ? where id = ?", text, commentId);
	}
	
	public void delete(Long commentId) {
	    jdbcTemplate.update("delete from comments where id = ?", commentId);
	}

}
