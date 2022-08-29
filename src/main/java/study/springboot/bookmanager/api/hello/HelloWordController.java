package study.springboot.bookmanager.api.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/hello")
public class HelloWordController {

    @GetMapping
    public String helloWorld() {
        return "hello world";
    }
}
