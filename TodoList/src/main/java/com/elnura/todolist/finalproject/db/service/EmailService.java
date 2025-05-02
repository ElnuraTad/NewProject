package com.elnura.todolist.finalproject.db.service;

import com.elnura.todolist.finalproject.db.entity.Task;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendTaskCreatedNotification(Task task) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("receiver@example.com"); // Кому отправлять
        message.setSubject("New Task Created: " + task.getTitle());
        message.setText("Description: " + task.getDescription() +
                "\nCompleted: " + (task.isCompleted() ? "Yes" : "No"));

        mailSender.send(message);
    }
}
