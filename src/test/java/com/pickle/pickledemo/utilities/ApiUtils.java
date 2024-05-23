package com.pickle.pickledemo.utilities;

import io.restassured.response.Response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

public class ApiUtils {

    public static void responseTimeLessThan(Response response, Integer time, String endpoint) {
        String responseTime = Long.toString(response.getTime());
                     responseTime = responseTime.length()==5 ? responseTime.substring(0,2) :
                     responseTime.length()==4 ? responseTime.substring(0,1) :
                     "0,"+responseTime.substring(0,1);
        if (response.getTime()>(time * 1000L)) {
            System.out.println(endpoint + " response time is "+responseTime+" and greater than " + time + " second/s.");
        }
        assertThat(response.getTime(), is(lessThan((time * 1000L))));
    }

    public static boolean isDateBeforeToday(Object date) {
        LocalDate today = LocalDate.now();
        LocalDate endDate = LocalDate.parse(date.toString().substring(0, 10), DateTimeFormatter.ISO_DATE);
        return (today.isBefore(endDate) || today.equals(endDate));
    }


}
