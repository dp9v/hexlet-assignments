package exercise.controller;

import exercise.ResourceNotFoundException;
import exercise.dto.CommentDto;
import exercise.model.Comment;
import exercise.model.Post;
import exercise.repository.CommentRepository;
import exercise.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    // BEGIN
    @GetMapping("{postId}/comments")
    public Iterable<Comment> findAllComments(@PathVariable long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @GetMapping("{postId}/comments/{id}")
    public Comment findAllComments(@PathVariable long postId, @PathVariable long id) {
        return commentRepository.findByIdAndPostId(id, postId)
            .orElseThrow(() -> new ResourceNotFoundException(""));
    }

    @PatchMapping("{postId}/comments/{id}")
    public void addComment(@PathVariable long postId, @PathVariable long id, @RequestBody CommentDto commentValue) {
        var comment = commentRepository.findByIdAndPostId(id, postId)
            .orElseThrow(() -> new ResourceNotFoundException(""));

        commentRepository.save(comment.setContent(commentValue.content()));
    }

    @PostMapping("{postId}/comments")
    public void addComment(@PathVariable long postId, @RequestBody CommentDto comment) {
        var newComment = new Comment()
            .setContent(comment.content())
            .setPost(new Post().setId(postId));
        commentRepository.save(newComment);
    }

    @DeleteMapping("{postId}/comments/{id}")
    public void deleteComment(@PathVariable long postId, @PathVariable long id) {
        var comment = commentRepository.findByIdAndPostId(id, postId)
            .orElseThrow(() -> new ResourceNotFoundException(""));

        commentRepository.deleteById(comment.getId());
    }
    // END
}
