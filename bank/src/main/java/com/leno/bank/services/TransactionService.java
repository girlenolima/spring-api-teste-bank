package com.leno.bank.services;


import com.leno.bank.domain.transaction.Transaction;
import com.leno.bank.domain.user.User;
import com.leno.bank.dtos.TransactionDTO;
import com.leno.bank.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {


    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;


    public Transaction createTransaction(TransactionDTO dto) throws Exception {
        User sender = this.userService.findUserById(dto.senderId());
        User receiver = this.userService.findUserById(dto.receiverId());

        userService.validateTrasaction(sender, dto.value());


        /*
        * Esse trecho consome um serviço esterno que nao esta on line, caso um dia volte basta descomentar

        * */

        /*boolean isAut = this.athorizeTransaction(sender, dto.value());
        if (!isAut) {
            throw new Exception("Transacaçao nao autorizada");
        }*/

        Transaction transaction = new Transaction();
        transaction.setAmount(dto.value());
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setTime(LocalDate.from(LocalDateTime.now()));

        sender.setBalance(sender.getBalance().subtract(dto.value()));
        receiver.setBalance(receiver.getBalance().add(dto.value()));

        this.transactionRepository.save(transaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender,"Trasacao realizada com sucesso");
        this.notificationService.sendNotification(receiver,"Trasacao recebida com sucesso");

        return transaction;

    }


    public boolean athorizeTransaction(User user, BigDecimal value) {

        /*essa rota esta fora do AR, logo sempre dara erro ao tentar concluir uma transaçao.
        para rodar de forma local mas incluir um try..catch para suprimir o erro em createTransaction*/
        ResponseEntity<Map> author = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);


        if (author.getStatusCode() == HttpStatus.OK) {

            String message = (String) author.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);

        } else return false;


    }


}
