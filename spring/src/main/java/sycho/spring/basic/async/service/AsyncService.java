package sycho.spring.basic.async.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class AsyncService {

    private static final int THREAD_MILLS = 5000;

    public void sleep(int num) throws InterruptedException {
        System.out.println(String.format("===== test%d thread sleep start %s =====", num, LocalDateTime.now()));
        Thread.sleep(THREAD_MILLS);
        System.out.println(String.format("===== test%d thread sleep end %s =====", num, LocalDateTime.now()));
    }

    @Async
    public void test1() throws InterruptedException {
        sleep(1);
    }

    @Async
    public Future<String> test2() throws InterruptedException {
        sleep(2);

        var result = "Hello, test2 World!";

        return new AsyncResult<>(result);
    }

    @Async
    public ListenableFuture<String> test3() throws InterruptedException {
        sleep(3);

        var result = "Hello, test3 World!";

        return new AsyncResult<>(result);
    }

    @Async
    public CompletableFuture<String> test4() throws InterruptedException {
        sleep(4);

        var i = 1;

        if (i == 2) {
            throw new RuntimeException("ERROR!");
        }

        var result = "Hello, test4 World!";

        return CompletableFuture.completedFuture(result);
    }
}
