package com.example.egr327project;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
public class Controller {
    public static int count = 0;

    @RequestMapping("/greeting")
    public Greeting greeting() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        String message = FileUtils.readFileToString(new File("/Users/T-Raww/IdeaProjects/egr327project/message.txt"));
        return mapper.readValue(message, Greeting.class);
    }

    @RequestMapping(value = "/createGreeting", method = RequestMethod.POST)
    public Greeting createGreeting(@RequestBody String content) throws IOException{
        Greeting newGreeting = new Greeting(count++,content);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("/Users/T-Raww/IdeaProjects/egr327project/message.txt"),newGreeting);
        return newGreeting;
    }
    @RequestMapping(value = "/updateGreeting", method = RequestMethod.PUT)
    public Greeting updateGreeting(@RequestBody String newMessage) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String message = FileUtils.readFileToString(new File("/Users/T-Raww/IdeaProjects/egr327project/message.txt"));
        Greeting newGreeting = mapper.readValue(message, Greeting.class);
        newGreeting.setContent(newMessage);
        mapper.writeValue(new File("/Users/T-Raww/IdeaProjects/egr327project/message.txt"), newGreeting);
        return newGreeting;
    }

    @RequestMapping(value = "/deleteGreeting", method = RequestMethod.DELETE)
    public void deleteGreeting(@RequestBody int id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String message = FileUtils.readFileToString(new File("/Users/T-Raww/IdeaProjects/egr327project/message.txt"));
        Greeting greeting = mapper.readValue(message, Greeting.class);
        if(greeting.getId() == id) {
            FileUtils.writeStringToFile(new File("/Users/T-Raww/IdeaProjects/egr327project/message.txt"),"");
        }
    }

}
