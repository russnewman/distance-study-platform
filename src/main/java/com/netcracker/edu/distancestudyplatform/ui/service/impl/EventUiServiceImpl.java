package com.netcracker.edu.distancestudyplatform.ui.service.impl;

import com.netcracker.edu.distancestudyplatform.dto.EventDto;
import com.netcracker.edu.distancestudyplatform.dto.EventStudentDto;
import com.netcracker.edu.distancestudyplatform.model.Event;
import com.netcracker.edu.distancestudyplatform.ui.service.EventUiService;
import com.netcracker.edu.distancestudyplatform.service.wrappers.EventList;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class EventUiServiceImpl implements EventUiService {


    private String baseURL = "http://localhost:8080";

    @Override
    public void saveEventDto(EventDto eventDto) {

        String URL = baseURL + "/saveEvent";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<EventDto> request = new HttpEntity<>(eventDto, headers);

        ResponseEntity<EventDto> response
                = restTemplate.postForEntity(URL, request, EventDto.class);
    }


    @Override
    public void editEvent(Long eventId, EventDto eventDto) {
        String URL = baseURL + "/editEvent";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("eventId", eventId);

        HttpEntity<EventDto> request = new HttpEntity<>(eventDto, headers);

        ResponseEntity<EventDto> response
                = restTemplate.postForEntity(builder.toUriString(), request, EventDto.class);
    }


    @Override
    public List<Event> getEvents(Long teacherId, String sortingType, String subjectName) {

        String URL = baseURL + "/getEvents";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("teacherId", teacherId)
                .queryParam("sortingType", sortingType)
                .queryParam("subjectName", subjectName);

        ResponseEntity<EventList> response
                = restTemplate.getForEntity(builder.toUriString(), EventList.class);

        return response.getBody().getEvents();
    }

    @Override
    public void deleteEvent(Long eventId) {
        String URL = baseURL + "/deleteEvent";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Long> request = new HttpEntity<>(eventId, headers);
        System.out.println("UISERV " + eventId);
        ResponseEntity<Long> response
                = restTemplate.postForEntity(URL, request, Long.class);

    }

    @Override
    public Event getEventById(Long eventId) {
        String URL = baseURL + "/getEventById";
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("eventId", eventId);


        ResponseEntity<Event> response
                = restTemplate.getForEntity(builder.toUriString(), Event.class);
        return response.getBody();


    }

    @Override
    public List<EventStudentDto> getAllStudentEvents(Long studentId) {
        String URL = baseURL + "events/all";
        return getEventStudentRestTemplate(URL, studentId);
    }

    @Override
    public List<EventStudentDto> getAllStudentSubjectEvents(Long studentId, Long subjectId) {
        String URL = baseURL + "events/all";
        return getEventStudentSubjectRestTemplate(URL, studentId, subjectId);
    }

    @Override
    public List<EventStudentDto> getActiveStudentEvents(Long studentId) {
        String URL = baseURL + "events/active";
        return getEventStudentRestTemplate(URL, studentId);
    }

    @Override
    public List<EventStudentDto> getActiveStudentSubjectEvents(Long studentId, Long subjectId) {
        String URL = baseURL + "events/active";
        return getEventStudentSubjectRestTemplate(URL, studentId, subjectId);
    }

    @Override
    public EventStudentDto getEvent(Long eventId) {
        String URL = baseURL + "events";
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
