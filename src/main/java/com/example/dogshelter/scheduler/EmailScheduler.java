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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

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
   // @Scheduled(fixedDelay = 10000) //for testing 1 mail/10 seconds
    public void sendInformationEmail() {
        long size = walkService.findAllPlannedWalks().size();
        simpleEmailService.send(
                new Mail.MailBuilder()
                        .mailTo(adminConfig.getAdminMail())
                        .subject(SUBJECT)
                        .message("For the next few days we have "  + size + " walks planned by Volunteers")
                        .build());
    }


    @Scheduled(cron = "0 0 12 ? * FRI")
    // @Scheduled(fixedDelay = 100000) //for testing 1 mail per minute
    public void sendMessageWithWeather() {
        List<Volunteer> allVolunteers = volunteerService.getAllVolunteers();
        Weather weather = weatherService.getWeather();

        for(Volunteer volunteer : allVolunteers){
            simpleEmailService.send(
                    new Mail.MailBuilder()
                            .mailTo(volunteer.getEmail())
                            .subject(SUBJECT2)
                            .message(mailMessage(weather, volunteer))
                            .build());
        }
    }



       private String mailMessage(Weather weather, Volunteer volunteer){
       return "Hello "+volunteer.getFirstName()+"! \n" +
               "Our dogs are waiting for You this weekend. \n" +
               "This is the weather for Saturday: \n" +
               "Temperature: "+weather.getDaily().getTemperature2mMax().get(1)+weather.getDailyUnits().getTemperature2mMax()+"\n" +
               "Probability of precipitation: "+weather.getDaily().getPrecipitationProbabilityMax().get(1)+weather.getDailyUnits().getPrecipitationProbabilityMax()+"\n" +
               "This is the weather for Sunday: \n" +
               "Temperature: "+weather.getDaily().getTemperature2mMax().get(2)+weather.getDailyUnits().getTemperature2mMax()+"\n" +
               "Probability of precipitation: "+weather.getDaily().getPrecipitationProbabilityMax().get(2)+weather.getDailyUnits().getPrecipitationProbabilityMax()+"\n" +
               "See You later!";
        }

}
