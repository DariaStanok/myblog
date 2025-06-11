package ru.practicum.project.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ru.practicum.project.dao.CommentRepository;
import ru.practicum.project.dao.PostRepository;
import ru.practicum.project.dao.TagRepository;
import ru.practicum.project.model.Post;

@Service
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final TagRepository tagRepository;
	private final CommentRepository commentRepository;
	private final String uploadDir;

	public PostServiceImpl(PostRepository postRepository, TagRepository tagRepository, CommentRepository commentRepository,
			@Value("${upload.dir}") String uploadDir) {
		this.postRepository = postRepository;
		this.tagRepository = tagRepository;
		this.commentRepository = commentRepository;
		this.uploadDir = uploadDir;
	}

	@Override
	public List<Post> findAll(String search, int pageSize, int pageNumber) {
		List<Post> posts = (search != null && !search.isBlank()) ? postRepository.findByTag(search.toLowerCase())
				: postRepository.findAll();
		for (Post post : posts) {
			post.setTags(tagRepository.loadTags(post.getId()));
		}
		int fromIndex = (pageNumber - 1) * pageSize;
		int toIndex = Math.min(fromIndex + pageSize, posts.size());
		if (fromIndex >= posts.size()) {
			return List.of(); 
		}
		return posts.subList(fromIndex, toIndex);
	}

	@Override
	public Post findById(Long id) {
		Post post = postRepository.findById(id);
		if (post != null) {
			post.setTags(tagRepository.loadTags(id));
			post.setComments(commentRepository.loadComments(id));
		}return post;
	};

	@Override
	public Post save(Post post, MultipartFile image, String tags) {
		String filename = storeImage(image);
		if (filename != null) {
			post.setImagePath(filename);
		}
		Long id = postRepository.save(post);
		tagRepository.insertTags(id, parseTags(tags));
		return findById(id);
	}

	@Override
	public Post update(Long id, Post updatePost, MultipartFile image, String tags) {
		String filename = storeImage(image);
		if (filename != null) {
			updatePost.setImagePath(filename);
		}
		updatePost.setId(id);
		postRepository.update(updatePost);
		tagRepository.deleteTags(id);
		tagRepository.insertTags(id, parseTags(tags));
		return findById(id);
	}

	@Override
	public void deleteById(Long id) {
		postRepository.deleteById(id);
	}

	@Override
	public void like(Long id, boolean like) {
		int delta = like ? 1 : -1;
		postRepository.updateLikes(id, delta);

	}

	private String storeImage(MultipartFile image) {
		if (image == null || image.isEmpty())
			return null;
		String originalFilename = image.getOriginalFilename();
		String filename = UUID.randomUUID() + "_" + (originalFilename != null ? originalFilename : "");
		Path target = Paths.get(uploadDir).resolve(filename);
		try {
			Files.createDirectories(target.getParent());
			image.transferTo(target.toFile());
			return filename;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private List<String> parseTags(String tags) {
		if (tags == null || tags.isBlank()) {
			return List.of();
		}
		return List.of(tags.split(","))
				.stream()
				.map(String::trim)
				.map(String::toLowerCase)
				.filter(s -> !s.isEmpty())
				.toList();
	}

}
