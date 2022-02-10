package com.keencho.hibernate.reactive.repository;

import com.keencho.hibernate.reactive.model.Post;
import com.keencho.hibernate.reactive.model.PostDTO;
import io.smallrye.mutiny.Uni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepository {

    @Autowired
    KcReactiveRepository kcReactiveRepository;

    public Uni<Post> save(PostDTO data) {
        Post entity = Post.builder()
                .title(data.getTitle())
                .contents(data.getContents())
                .build();

        return kcReactiveRepository.save(entity);
    }

    public Uni<List<Post>> listAll() {
        return kcReactiveRepository.listAll(Post.class);
    }

    public Uni<Post> findById(Long id) {
        return kcReactiveRepository.findById(Post.class, id);
    }

}
