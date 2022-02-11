package com.keencho.hibernate.reactive;

import com.keencho.hibernate.reactive.model.Author;
import com.keencho.hibernate.reactive.model.Post;
import com.keencho.hibernate.reactive.repository.AuthorRepository;
import com.keencho.hibernate.reactive.repository.KcReactiveRepository;
import com.keencho.hibernate.reactive.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.time.Duration;
import java.util.stream.IntStream;


@SpringBootTest
class HibernateReactiveTest {

    @Autowired
    KcReactiveRepository kcReactiveRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PostRepository postRepository;

    @Test
    void fullTest() {

        var simpleAuthor = authorRepository
                .save("홍길동")
                .await()
                .indefinitely();

        IntStream.range(1, 11).forEach(idx -> {
           var uni = postRepository.save("title" + idx, "contents"+ idx, simpleAuthor);
           uni.await().indefinitely();
        });

        ////////////////////////////////////////////////////////////////////////////////////////////////

        var authorList = authorRepository.listAll().await().indefinitely();
        var postList = postRepository.listAll().await().indefinitely();

        Assert.isTrue(authorList.size() == 1, "size of author list must be 1");
        Assert.isTrue(postList.size() == 10, "size of post list must be 10");

        long authorId = authorList.stream().findFirst().get().getId();
        long postId = postList.stream().findAny().get().getId();

        var deleteNotExistAuthor = authorRepository.deleteById(authorId + 1).await().indefinitely();
        var deleteExistPost = postRepository.deleteById(postId).await().indefinitely();

        Assert.isTrue(deleteNotExistAuthor == 0, "query result of delete empty author must be 0");
        Assert.isTrue(deleteExistPost == 1, "query result of delete post must be 1");

    }

}
