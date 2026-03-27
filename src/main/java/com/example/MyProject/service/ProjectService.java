package com.example.MyProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.MyProject.model.Project;
import com.example.MyProject.repository.ProjectRepository;

@Service
public class ProjectService {
     
    @Autowired
    ProjectRepository repository;
     @Autowired
    private JavaMailSender mailSender;
    public ProjectService(){
    }
    public void saveData(Project student){
        this.repository.save(student);
    }
    public Project findByEmail(String email) {
    return repository.findByEmail(email);
}
    public void sendEmail(String to,String otp){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP for Verification is:"+ otp);
        this.mailSender.send(message);
    }

    public Project AddStudent(Project projectmodel){
        return repository.save(projectmodel);
    }
   
    
   
}
