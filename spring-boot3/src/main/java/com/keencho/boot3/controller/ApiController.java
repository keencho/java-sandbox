package com.keencho.boot3.controller;

import com.keencho.boot3.utils.FakerUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequestMapping(value = ControllerPrefix.API_CONTROLLER_PREFIX)
@RestController
public class ApiController {

    @GetMapping("/index")
    public List<?> index() {
        return IntStream.range(0, 30).mapToObj(idx -> FakerUtils.generateBook()).collect(Collectors.toList());
    }
}
