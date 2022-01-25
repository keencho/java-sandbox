package sycho.spring.controller;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sycho.spring.repo.CacheTestRepository;
import sycho.spring.service.CacheTestService;

@RestController
@RequestMapping("/cache")
public class CacheTestController {

    @Autowired
    CacheTestRepository cacheTestRepository;

    @Autowired
    CacheTestService cacheTestService;

    @GetMapping("/second-level/insert")
    public ResponseEntity<?> insert() {
        cacheTestService.insertDummyData();

        return ResponseEntity.ok("SUCCESS");
    }

    @GetMapping("/second-level/getById")
    public ResponseEntity<?> getById(@RequestParam Long id) {

        return ResponseEntity.ok(cacheTestService.secondLevelTest(id));
    }

}
