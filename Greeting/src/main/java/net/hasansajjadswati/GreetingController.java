/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.hasansajjadswati;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GreetingController {
    
    @Autowired
    private GreetingService greetingService;
    
    
    @RequestMapping("/greetings")
    public Greeting messageRequest(@RequestParam(value = "name", defaultValue = "User") String name ){
          return greetingService.setGreeting(name);
    }
    
}
