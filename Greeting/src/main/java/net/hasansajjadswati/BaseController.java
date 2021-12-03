
package net.hasansajjadswati;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/hello")
    public String message(){
        return "Hello Spring";
    }
}
