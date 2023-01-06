package com.gmail.guia2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public String sendEmail(String toEamail, String titulo, String mensagem) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("auxconttemp@gmail.com");
            message.setTo(toEamail);
            message.setText(mensagem);
            message.setSubject(titulo);

            mailSender.send(message);

            return "Email enviado....";
        }catch (Exception ex){
            return "Erro ao enviar o email....";
        }
    }

}
