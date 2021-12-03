
package net.hasansajjadswati;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    
    private final Greeting greeting = new Greeting();
    
    public Greeting setGreeting(String Content){
        greeting.setContent("Hello, "+ Content +"!");
        greeting.setId(greeting.getId() + 1);
        return greeting;
    }
}
