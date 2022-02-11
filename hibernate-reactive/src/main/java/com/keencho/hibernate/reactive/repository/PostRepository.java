package com.keencho.hibernate.reactive.repository;

import com.keencho.hibernate.reactive.model.Author;
import com.keencho.hibernate.reactive.model.Post;
import io.smallrye.mutiny.Uni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepository {

    @Autowired
    KcReactiveRepository kcReactiveRepository;

    public Uni<Post> save(String title, String contents, Author author) {
        Post entity = Post.builder()
                .title(title)
                .contents(contents)
                .author(author)
                .build();

        return kcReactiveRepository.save(entity);
    }

    public Uni<List<Post>> listAll() {
        return kcReactiveRepository.listAll(Post.class);
    }

    public Uni<Post> findById(Long id) {
        return kcReactiveRepository.findById(Post.class, id);
    }

    public Uni<Integer> deleteAll() {
        return kcReactiveRepository.deleteAll(Post.class);
    }

    public Uni<Integer> deleteById(Long id) {
        return kcReactiveRepository.deleteById(Post.class, id);
    }

}
