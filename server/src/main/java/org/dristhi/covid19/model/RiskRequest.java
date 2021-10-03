package org.dristhi.covid19.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RiskRequest {

    /*
    "country" = input$country,
    "zip" = input$zip,
    "age"= as.numeric(input$age),
    "sex" = input$sex,
    "symptoms" = as.list(input$symptoms),
    "direct_contacts" = as.numeric(input$direct_contacts),
    "live_w_others"= input$live_w_others,
    "indirect_contacts" = as.numeric(input$indirect_contacts),
    "hand"= bool2char(input$hand),
    "ppe"= bool2char(input$ppe),
    "conditions" = as.list(input$conditions),
    "activities_high" = length(input$h_activities),
    "activities_medium" = length(input$m_activities),
    "activities_low" = length(input$l_activities),
    "exercise_level" = input$exercise_level)
     */

    private Location location;

    private Personal personal;

    private HealthStatus health;

    private Vaccine vaccine;

    @Getter
    @Setter
    public static class Location {
        private String country;

        private String zip;
    }

    @Getter
    @Setter
    public static class Personal {
        private int age;

        private String sex;
    }

    @Getter
    @Setter
    public static class HealthStatus {
        private String[] symptoms;

        private String[] conditions;

        private ExerciseLevel exerciseLevel;
    }

    @Getter
    @Setter
    public static class Behavior {
        private boolean liveWithOthers;

        private int directContacts;

        private Activity activity;

    }

    @Getter
    @Setter
    public static class Vaccine {
        private String provider;

        private int doses;
    }

    public enum ExerciseLevel {
        HIGH, MEDIUM, LOW
    }

    public enum Activity {
        HIGH, MEDIUM, LOW
    }
}
