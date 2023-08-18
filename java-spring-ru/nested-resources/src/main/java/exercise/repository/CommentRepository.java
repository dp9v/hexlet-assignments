package exercise.repository;

import exercise.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    Iterable<Comment> findAllByPostId(long id);

    Optional<Comment> findByIdAndPostId(Long aLong, Long postId);
}
