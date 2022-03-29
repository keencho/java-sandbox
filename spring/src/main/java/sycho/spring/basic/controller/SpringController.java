package sycho.spring.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sycho.spring.basic.service.SpringService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/async-test")
public class SpringController {

    @Autowired
    SpringService springService;

    @GetMapping("/{num}")
    public ResponseEntity<?> test(@PathVariable int num) throws InterruptedException {
        switch (num) {
            case 1:
                springService.test1();
                break;
            case 2:
                var t2 = springService.test2();
//                System.out.println(result.get());
                break;
            case 3:
                var t3 = springService.test3();

                t3.addCallback(
                        res -> {
                            System.out.println("success: " + res);
                        },
                        err -> {
                            System.err.println("error occurred: " + err);
                        }
                );
                break;
            case 4:
                var t4 = springService.test4();

                var t4Res = t4.handle((s, e) -> {
                    if (e != null) {
                        return e.getMessage();
                    }
                    return s;
                });

                t4Res.thenAccept(System.out::println);
                break;
            default:
                break;
        }

        System.out.println(String.format("===== test%d task finished %s", num, LocalDateTime.now()));
        return ResponseEntity.ok("SUCCESS");
    }
}
