package ru.practicum.project.service;

import ru.practicum.project.dao.CommentRepository;

public class CommentServiceImpl {

	private final CommentRepository commentRepository;

	public CommentServiceImpl(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	public void addComment(Long postId, String text) {
		// TODO Auto-generated method stub

	}

	public void editComment(Long postId, Long commentId, String text) {
		// TODO Auto-generated method stub

	}

	public void deleteComment(Long postId, Long commentId) {
		// TODO Auto-generated method stub

	}

}
