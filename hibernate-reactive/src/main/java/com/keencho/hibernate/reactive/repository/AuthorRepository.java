package com.keencho.hibernate.reactive.repository;

import com.keencho.hibernate.reactive.model.Author;
import com.keencho.hibernate.reactive.model.Post;
import io.smallrye.mutiny.Uni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepository {

    @Autowired
    KcReactiveRepository kcReactiveRepository;

    public Uni<Author> save(String name) {
        Author entity = Author.builder()
                .name(name)
                .build();

        return kcReactiveRepository.save(entity);
    }

    public Uni<List<Author>> listAll() {
        return kcReactiveRepository.listAll(Author.class);
    }

    public Uni<Author> findById(Long id) {
        return kcReactiveRepository.findById(Author.class, id);
    }

    public Uni<Integer> deleteAll() {
        return kcReactiveRepository.deleteAll(Author.class);
    }

    public Uni<Integer> deleteById(Long id) {
        return kcReactiveRepository.deleteById(Author.class, id);
    }

}
