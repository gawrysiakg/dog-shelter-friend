package com.example.dogshelter.scheduler;

import com.example.dogshelter.config.AdminConfig;
import com.example.dogshelter.domain.Mail;
import com.example.dogshelter.domain.Volunteer;
import com.example.dogshelter.domain.api.open_meteo.Weather;
import com.example.dogshelter.service.SimpleEmailService;
import com.example.dogshelter.service.VolunteerService;
import com.example.dogshelter.service.WalkService;
import com.example.dogshelter.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
@Slf4j
@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private final SimpleEmailService simpleEmailService;
    private final AdminConfig adminConfig;
    private final WalkService walkService;
    private final VolunteerService volunteerService;
    private final WeatherService weatherService;
    private static final String SUBJECT = "Dog Shelter Friend: Once a day email";
    private static final String SUBJECT2 = "Dog Shelter: Visit us this weekend! ";



   @Scheduled(cron = "0 0 10 * * *")
    //@Scheduled(fixedDelay = 30000)
    public void sendInformationEmail() {
        long size = walkService.findAllPlannedWalks().size();
        simpleEmailService.send(
                new Mail.MailBuilder()
                        .mailTo(adminConfig.getAdminMail())
                        .subject(SUBJECT)
                        .message("<p>For the next few days we have "  + size + " walks planned by Volunteers</p>")
                        .build());
       log.info("Email has been sent.");
    }


    @Scheduled(cron = "0 0 12 ? * FRI")
    //@Scheduled(fixedDelay = 100000)
    public void sendMessageWithWeather() {
        List<Volunteer> allVolunteers = volunteerService.getAllVolunteers();
        Weather weather = weatherService.getWeather();

        for(Volunteer volunteer : allVolunteers){
            simpleEmailService.sendScheduledEmailWithWeather(
                    new Mail.MailBuilder()
                            .mailTo(volunteer.getEmail())
                            .subject(SUBJECT2)
                            .message(mailMessage(weather, volunteer))
                            .build());
        }
        log.info("Email has been sent.");
    }



       private String mailMessage(Weather weather, Volunteer volunteer){
       return "<p>Hello "+volunteer.getFirstName()+"! <br>" +
               "Our dogs are waiting for You this weekend. <br></p>" +
               "<p>This is the weather for Saturday: <br>" +
               "Temperature: "+weather.getDaily().getTemperature2mMax().get(1)+weather.getDailyUnits().getTemperature2mMax()+"<br>" +
               "Probability of precipitation: "+weather.getDaily().getPrecipitationProbabilityMax().get(1)+weather.getDailyUnits().getPrecipitationProbabilityMax()+"<br>" +
               "</p><p>This is the weather for Sunday: <br>" +
               "Temperature: "+weather.getDaily().getTemperature2mMax().get(2)+weather.getDailyUnits().getTemperature2mMax()+"<br>" +
               "Probability of precipitation: "+weather.getDaily().getPrecipitationProbabilityMax().get(2)+weather.getDailyUnits().getPrecipitationProbabilityMax()+"</p>" +
               "<p>See You later... in Shelter!</p><p></p>";
        }
}
