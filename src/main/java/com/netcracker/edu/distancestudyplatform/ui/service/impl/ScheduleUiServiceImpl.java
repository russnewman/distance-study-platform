package com.netcracker.edu.distancestudyplatform.ui.service.impl;

import com.netcracker.edu.distancestudyplatform.dto.ScheduleDtoList;
import com.netcracker.edu.distancestudyplatform.dto.SubjectDto;
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

    @Override
    public ScheduleDtoList getStudentTodaySchedule(Long studentId){
        String URL = baseUri + "todayStudentSchedule";
        return getStudentScheduleRestTemplate(URL, studentId);
    }

    @Override
    public ScheduleDtoList getStudentTomorrowSchedule(Long studentId) {
        String URL = baseUri + "tomorrowStudentSchedule";
        return getStudentScheduleRestTemplate(URL, studentId);
    }

    @Override
    public SubjectDto getStudentCurrentSubject(Long studentId) {
        String URL = baseUri + "currentStudentSubject";
        return getSubjectRestTemplate(URL, studentId);
    }

    @Override
    public SubjectDto getStudentNextSubject(Long studentId) {
        String URL = baseUri + "nextStudentSubject";
        return getSubjectRestTemplate(URL, studentId);
    }

    @Override
    public ScheduleDtoList getStudentSubjectSchedule(Long studentId, Long subjectId){
        String URL = baseUri + "getSubjectStudentSchedule";
        return getStudentScheduleRestTemplate(URL, studentId, subjectId);
    }

    public SubjectDto getSubjectRestTemplate(String URL, Long studentId){
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("studentId", studentId);
        return restTemplate.getForObject(builder.toUriString(), SubjectDto.class);
    }

    public ScheduleDtoList getStudentScheduleRestTemplate(String URL, Long studentId){
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("studentId", studentId);

        ResponseEntity<ScheduleDtoList> response
                = restTemplate.getForEntity(builder.toUriString(), ScheduleDtoList.class);

        return new ScheduleDtoList(Objects.requireNonNull(response.getBody()).getSchedules());
    }

    public ScheduleDtoList getStudentScheduleRestTemplate(String URL, Long studentId, Long subjectId){
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("studentId", studentId)
                .queryParam("subjectId", subjectId);
        System.out.println(studentId + " " + subjectId);//fixme: debug purpose
        ResponseEntity<ScheduleDtoList> response
                = restTemplate.getForEntity(builder.toUriString(), ScheduleDtoList.class);

        return new ScheduleDtoList(Objects.requireNonNull(response.getBody()).getSchedules());
    }

}
