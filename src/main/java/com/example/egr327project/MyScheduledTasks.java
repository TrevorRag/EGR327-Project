package com.example.egr327project;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MyScheduledTasks {

    RestTemplate restTemplate = new RestTemplate();
    private boolean isHello = true;

    @Scheduled(cron = "*/2 * * * * *")
    public void updateGreeting(){
        String url = "http://localhost:8080/updateGreeting";
        String message = (isHello)?"Hello World":"Bye World";
        restTemplate.put(url, message);
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void getGreeting() {
        String url = "http://localhost:8080/greeting";
        Greeting g = restTemplate.getForObject(url, Greeting.class);
        System.out.println(g.getContent());
        isHello = !isHello;
    }
}
