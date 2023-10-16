package com.pickpaysimplify.services;

import com.pickpaysimplify.domain.Transaction;
import com.pickpaysimplify.domain.Users;
import com.pickpaysimplify.dto.TransactionDTO;
import com.pickpaysimplify.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {


    @Autowired
    private UserService userService;

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void createNewTransaction(TransactionDTO transaction) throws Exception {
        Users sender = userService.getUserById(transaction.sender());
        Users reciver = userService.getUserById(transaction.reciver());
        userService.validateTransaction(sender, transaction.value());
        if (!isAuthorizedTransaction(sender, transaction.value())) {
            throw new Exception("Not authorized!!");
        }

        Transaction t = new Transaction();

        t.setSender(sender);
        t.setReciver(reciver);
        t.setAmount(transaction.value());
        t.setCreated(LocalDateTime.now());

        sender.setBalence(sender.getBalence().subtract(transaction.value()));
        reciver.setBalence(reciver.getBalence().add(transaction.value()));

        this.transactionRepository.save(t);
        this.userService.saveUser(sender);
        this.userService.saveUser(reciver);

        String msg = "You recive new valeu,  R$" + transaction.value() + ", send " + sender.getName() + ".";
        notifyService.sendNotify(reciver, msg);
    }


    public boolean isAuthorizedTransaction(Users sender, BigDecimal amount) {
        ResponseEntity<Map> auth = restTemplate.getForEntity("https://run.mocky.io/v3/7c4f0850-9e19-4430-ac80-68bfa927046b", Map.class);
        System.out.println("------------");
        System.out.println(auth.getBody());
        String isAuth = (String) auth.getBody().get("messagem");
        if (auth.getStatusCode() == HttpStatus.OK && isAuth.equalsIgnoreCase("Authorezed")) {
            return true;
        }
        return false;
    }
}
