package com.netcracker.edu.distancestudyplatform.ui.service.impl;

import com.netcracker.edu.distancestudyplatform.dto.ScheduleDtoList;
import com.netcracker.edu.distancestudyplatform.ui.service.ScheduleUiService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Service
public class ScheduleUiServiceImpl implements ScheduleUiService {
    final static private String baseUri = "http://localhost:8080/";

    @Override
    public ScheduleDtoList getStudentFullSchedule(Long studentId){
        String URL = baseUri + "full";
        return getStudentScheduleRestTemplate(URL, studentId);
    }

    public ScheduleDtoList getStudentScheduleRestTemplate(String URL, Long studentId){
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("studentId", studentId);

        ResponseEntity<ScheduleDtoList> response
                = restTemplate.getForEntity(builder.toUriString(), ScheduleDtoList.class);

        return new ScheduleDtoList(Objects.requireNonNull(response.getBody()).getSchedules());
    }

}
