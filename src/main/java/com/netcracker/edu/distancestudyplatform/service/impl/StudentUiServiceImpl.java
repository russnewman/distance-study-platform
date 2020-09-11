package com.netcracker.edu.distancestudyplatform.ui.service.impl;

import com.netcracker.edu.distancestudyplatform.dto.SubjectDto;
import com.netcracker.edu.distancestudyplatform.model.Student;
import com.netcracker.edu.distancestudyplatform.ui.service.StudentUiService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class StudentUiServiceImpl implements StudentUiService {

    final static private String baseUri = "http://localhost:8080/";

    @Override
    public Student getStudentByEmail(String email) {
        String URL = baseUri + "students/email";
        return getStudentRestTemplate(URL, email);
    }

    @Override
    public Student getStudent(Long id) {
        String URL = baseUri + "students/";
        return getStudentRestTemplate(URL, id);
    }

    private Student getStudentRestTemplate(String URL, String email){
            RestTemplate restTemplate = new RestTemplate();
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                    .queryParam("email", email);
            return restTemplate.getForObject(builder.toUriString(), Student.class);
    }

    private Student getStudentRestTemplate(String URL, Long studentId){
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("studentId", studentId);
        return restTemplate.getForObject(builder.toUriString(), Student.class);
    }
}
