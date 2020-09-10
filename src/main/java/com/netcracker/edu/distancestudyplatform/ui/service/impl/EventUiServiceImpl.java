package com.netcracker.edu.distancestudyplatform.ui.service.impl;

import com.netcracker.edu.distancestudyplatform.dto.AssignmentDto;
import com.netcracker.edu.distancestudyplatform.dto.EventStudentDto;
import com.netcracker.edu.distancestudyplatform.model.Event;
import com.netcracker.edu.distancestudyplatform.ui.dto.EventDto;
import com.netcracker.edu.distancestudyplatform.ui.service.EventUiService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class EventUiServiceImpl implements EventUiService {

    final static private String baseUri = "http://localhost:8080/";

    @Override
    public void saveEventDto(EventDto eventDto) {
        String URL = baseUri + "/saveEvent";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<EventDto> request = new HttpEntity<>(eventDto, headers);

        ResponseEntity<EventDto> response
                = restTemplate.postForEntity(URL, request, EventDto.class);
    }

    @Override
    public List<EventStudentDto> getAllStudentEvents(Long studentId) {
        String URL = baseUri + "events/all";
        return getEventStudentRestTemplate(URL, studentId);
    }

    @Override
    public List<EventStudentDto> getAllStudentSubjectEvents(Long studentId, Long subjectId) {
        String URL = baseUri + "events/all";
        return getEventStudentSubjectRestTemplate(URL, studentId, subjectId);
    }

    @Override
    public List<EventStudentDto> getActiveStudentEvents(Long studentId) {
        String URL = baseUri + "events/active";
        return getEventStudentRestTemplate(URL, studentId);
    }

    @Override
    public List<EventStudentDto> getActiveStudentSubjectEvents(Long studentId, Long subjectId) {
        String URL = baseUri + "events/active";
        return getEventStudentSubjectRestTemplate(URL, studentId, subjectId);
    }

    @Override
    public EventStudentDto getEvent(Long eventId) {
        String URL = baseUri + "events";
        return getEventRestTemplate(URL, eventId);
    }

    private List<EventStudentDto> getEventStudentRestTemplate(String URL, Long studentId){
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("studentId", studentId);
        ResponseEntity<List<EventStudentDto>> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EventStudentDto>>() {}
        );
        return response.getBody();
    }

    private EventStudentDto getEventRestTemplate(String URL, Long eventId){
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("eventId", eventId);
        ResponseEntity<EventStudentDto> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<EventStudentDto>() {}
        );
        return response.getBody();
    }

    private List<EventStudentDto> getEventStudentSubjectRestTemplate(String URL, Long studentId, Long subjectId){
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("studentId", studentId)
                .queryParam("subjectId", subjectId);
        ResponseEntity<List<EventStudentDto>> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EventStudentDto>>() {}
        );
        return response.getBody();
    }

}
