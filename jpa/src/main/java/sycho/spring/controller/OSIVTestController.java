package sycho.spring.controller;

import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sycho.spring.service.OSIVTestService;

import java.util.HashMap;

@Controller
@RequestMapping("/osiv")
public class OSIVTestController {

    @Autowired
    OSIVTestService osivTestService;

    @GetMapping("/insert")
    public ResponseEntity<?> insert() {
        osivTestService.insertParent();
        osivTestService.insertChildren();

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getChildren() {
        var children = osivTestService.getChildren();
        return ResponseEntity.ok(children);
    }
}
