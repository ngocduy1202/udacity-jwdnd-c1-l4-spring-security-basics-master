package com.udacity.jwdnd.spring_security_basics.service;

import com.udacity.jwdnd.spring_security_basics.mapper.MessageMapper;
import com.udacity.jwdnd.spring_security_basics.model.ChatForm;
import com.udacity.jwdnd.spring_security_basics.model.ChatMessage;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class MessageService {
    private MessageMapper messageMapper;

    public MessageService(MessageMapper messageMapper){
        this.messageMapper = messageMapper;
    }

    @PostConstruct
    public void postContruct(){
        System.out.println("Create Message Service Bean.");
    }

    public void addMessage(ChatForm chatForm){
        ChatMessage chatMessage = new ChatMessage(); //create obj ChatMessage
        chatMessage.setUsername(chatForm.getUsername()); //get username from charForm -> set username for chatMessage
        switch (chatForm.getMessageType()){ //get message text from chatForm -> set messageText for chatMessage
            case "Say":
                chatMessage.setMessageText(chatForm.getMessageText());
                break;
            case "Shout":
                chatMessage.setMessageText(chatForm.getMessageText().toUpperCase());
                break;
            case "Whisper":
                chatMessage.setMessageText(chatForm.getMessageText().toLowerCase());
                break;
        }
        messageMapper.addMessage(chatMessage); //use addMessage method from messageMapper
    }

    public List<Message> getAllMessages(){
        return messageMapper.getListMessages(); //return all message by list
    }

}
