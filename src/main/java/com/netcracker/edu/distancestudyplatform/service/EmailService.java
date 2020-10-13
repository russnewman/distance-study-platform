package com.netcracker.edu.distancestudyplatform.service;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);
}
