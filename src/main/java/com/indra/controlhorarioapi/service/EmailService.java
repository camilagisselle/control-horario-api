package com.indra.controlhorarioapi.service;

import com.indra.controlhorarioapi.model.EmailDetails;

public interface EmailService {

    String sendSimpleMail(EmailDetails details);
}
