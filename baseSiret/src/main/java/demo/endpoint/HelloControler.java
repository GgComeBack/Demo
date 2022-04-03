package demo.endpoint;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "hello")
public class HelloControler {

  @GetMapping(produces = {MediaType.TEXT_PLAIN_VALUE})
  public String hello() {
    return "Hello";
  }

  @GetMapping(value = "test", produces = {MediaType.APPLICATION_JSON_VALUE})
  public List<String> hellolist() {
    return Collections.singletonList("Hello");
  }

}
