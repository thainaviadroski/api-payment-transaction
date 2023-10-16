package com.pickpaysimplify.services;

import com.pickpaysimplify.domain.Users;
import com.pickpaysimplify.dto.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotifyService {

    @Autowired
    private RestTemplate restTemplate;


    public void sendNotify(Users sender, String message) throws Exception {
        NotificationDTO noticiation = new NotificationDTO(sender.getEmail(), message);
        ResponseEntity<String> response = restTemplate.postForEntity("https://run.mocky.io/v3/967a4c24-2813-4f2a-952f-a4b56ff19baf", noticiation, String.class);

        if(!(response.getStatusCode() == HttpStatus.OK)){
            System.out.println("Fail sending notification");
            throw new Exception("Fail");
        }

    }


}
