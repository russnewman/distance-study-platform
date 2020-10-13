package com.netcracker.edu.distancestudyplatform.service.impl;

import com.netcracker.edu.distancestudyplatform.dto.assignment.AssignmentDto;
import com.netcracker.edu.distancestudyplatform.ui.service.AssignmentUiService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class AssignmentUiServiceImpl implements AssignmentUiService {
    final static private String baseUri = "http://localhost:8080/";

    @Override
    public List<AssignmentDto> getAllStudentAssignments(Long studentId) {
        String URL = baseUri + "studentAssignments";
        return getStudentAssignmentRestTemplate(
                URL, studentId
        );
    }

    @Override
    public List<AssignmentDto> getAllStudentSubjectAssignments(Long studentId, Long subjectId) {
        return null;
    }

    @Override
    public List<AssignmentDto> getAssessedStudentAssignments(Long studentId) {
        String URL = baseUri + "studentAssessedAssignments";
        return getStudentAssignmentRestTemplate(
                URL, studentId
        );
    }

    @Override
    public List<AssignmentDto> getUnassessedStudentAssignments(Long studentId) {
        String URL = baseUri + "studentUnassessedAssignments";
        return getStudentAssignmentRestTemplate(
                URL, studentId
        );
    }

    @Override
    public List<AssignmentDto> getActiveStudentAssignments(Long studentId) {
        String URL = baseUri + "studentActiveAssignments";
        return getStudentAssignmentRestTemplate(
                URL, studentId
        );
    }

    @Override
    public List<AssignmentDto> getEventStudentAssignments(Long studentId, Long eventId) {
        String URL = baseUri + "studentEventAssignments";
        return getStudentAssignmentRestTemplate(
                URL, studentId, eventId
        );
    }

    @Override
    public List<AssignmentDto> getEventStudentAssessedAssignments(Long studentId, Long eventId) {
        String URL = baseUri + "studentEventAssessedAssignments";
        return getStudentAssignmentRestTemplate(
                URL, studentId, eventId
        );
    }

    @Override
    public List<AssignmentDto> getEventStudentUnassessedAssignments(Long studentId, Long eventId) {
        String URL = baseUri + "studentEventUnassessedAssignments";
        return getStudentAssignmentRestTemplate(
                URL, studentId, eventId
        );
    }

    @Override
    public void save(AssignmentDto assignment) {
        String URL = baseUri + "addAssignment";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        //headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        //headers.setContentType(MediaType.APPLICATION_JSON);
        //headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        //form.add("file", assignment.getDbFile().getFile());
        //form.add("eventId", assignment.getEvent().getId());
        form.add("commentary", assignment.getStudentCommentary());

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(form, headers);
        restTemplate.postForEntity(URL, request, AssignmentDto.class);
    }

    private List<AssignmentDto> getStudentAssignmentRestTemplate(String URL, Long studentId){
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("studentId", studentId);
        ResponseEntity<List<AssignmentDto>> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AssignmentDto>>() {}
                );
        return response.getBody();
    }

    private List<AssignmentDto> getStudentAssignmentRestTemplate(String URL, Long studentId, Long eventId){
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("studentId", studentId)
                .queryParam("eventId", eventId);
        ResponseEntity<List<AssignmentDto>> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AssignmentDto>>() {}
        );
        return response.getBody();
    }
}
