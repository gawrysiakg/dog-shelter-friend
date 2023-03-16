package com.example.dogshelter.service;

import com.example.dogshelter.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;

@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;
    @Autowired
    private AdminConfig adminConfig;

    public String buildVisitUsEmail(String message) {
        String dayOfWeek = String.valueOf(LocalDate.now().getDayOfWeek());
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("dogs_url", "https://www.dogopedia.pl/");
        context.setVariable("button", "Do You like dogs? Visit Dogopedia!");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("company_goal", adminConfig.getCompanyGoal());
        context.setVariable("company_email", adminConfig.getCompanyEmail());
        context.setVariable("company_phone", adminConfig.getCompanyPhone());
        context.setVariable("preview_message", "Automatically generated message from DogShelter");
        context.setVariable("show_button", true);
        context.setVariable("day_of_week", dayOfWeek);
        context.setVariable("admin_config", adminConfig);

        return templateEngine.process("mail/visit-us-this-weekend-mail", context);
    }

    public String buildInfoEmail(String message) {
        String dayOfWeek = String.valueOf(LocalDate.now().getDayOfWeek());
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("day_of_week", dayOfWeek);
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("company_goal", adminConfig.getCompanyGoal());
        context.setVariable("company_email", adminConfig.getCompanyEmail());
        context.setVariable("company_phone", adminConfig.getCompanyPhone());
        context.setVariable("preview_message", "Automatically generated message from DogShelter");
        return templateEngine.process("mail/info-mail", context);
    }

}