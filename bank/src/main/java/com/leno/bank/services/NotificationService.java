package com.leno.bank.services;


import com.leno.bank.domain.user.User;
import com.leno.bank.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;


    public void sendNotification(User user, String messagen) throws Exception {

        String email = user.getEmail();
        NotificationDTO notificantionRequest = new NotificationDTO(email, messagen);
        ResponseEntity<String> response = restTemplate.postForEntity("http://o4d9z.mocklab.io/notify", notificantionRequest, String.class);

        if (!(response.getStatusCode() == HttpStatus.OK)) {
            System.out.println("Notificaçao com erro");
            throw new Exception("Serviço de notificaçao fora do ar");
        }

    }


}
