package com.challenge.cmg.views;

import com.challenge.cmg.chat.EmailService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@Route("send-email")
public class EmailView extends VerticalLayout {

    private final EmailService emailService;

    @Autowired
    public EmailView(EmailService emailService) {
        this.emailService = emailService;

        TextArea messageField = new TextArea("Message");
        messageField.setPlaceholder("Type your message here...");
        messageField.setWidthFull();

        Button sendButton = new Button("Send Email", event -> {
            String message = messageField.getValue();
            if (!message.trim().isEmpty()) {
                emailService.sendSimpleMessage("igormiguel37@gmail.com", "New Message", message);
                Notification.show("Email sent successfully!");
                messageField.clear();
            } else {
                Notification.show("Please enter a message before sending.");
            }
        });

        add(messageField, sendButton);
    }
}

