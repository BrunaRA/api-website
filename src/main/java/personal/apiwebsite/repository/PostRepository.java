package personal.apiwebsite.repository;

import org.springframework.data.jpa.repository.Query;
import personal.apiwebsite.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import personal.apiwebsite.model.dto.PostDTO;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {


    @Query("SELECT new personal.apiwebsite.model.dto.PostDTO(p.id, p.userId, p.title, p.body) FROM post_table p")
    List<PostDTO> getPostData();
}
