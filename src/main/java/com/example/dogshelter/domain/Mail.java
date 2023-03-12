package com.example.dogshelter.domain;
import lombok.Getter;

@Getter
public class Mail {
    private final String mailTo;
    private String toCc;
    private final String subject;
    private final String message;

        public static class MailBuilder {
            private String mailTo;
            private String toCc;
            private String subject;
            private String message;


        public MailBuilder mailTo(String mailTo){
            this.mailTo=mailTo;
            return this;
        }
        public MailBuilder toCc(String toCc){
            this.toCc=toCc;
            return this;
        }
        public MailBuilder subject(String subject){
            this.subject=subject;
            return this;
        }
        public MailBuilder message(String message){
            this.message=message;
            return this;
        }

        public Mail build (){
            return new Mail(mailTo, toCc, subject, message);
        }
    }

    private Mail(String mailTo, String toCc, String subject, String message) {
        this.mailTo = mailTo;
        this.toCc = toCc;
        this.subject = subject;
        this.message = message;
    }

    public String getMailTo() {
        return mailTo;
    }
    public String getToCc() {
        return toCc;
    }
    public String getSubject() {
        return subject;
    }
    public String getMessage() {
        return message;
    }
}