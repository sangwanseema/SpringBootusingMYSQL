package org.example.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
// this is an annotation
@RestController
public class MyClass {
    // api end point
  @GetMapping("about")
public String hello(){
    return "Hello , welcome to springBoot learning";
}

}
