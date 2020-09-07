package com.netcracker.edu.distancestudyplatform.ui.service.impl;

import com.netcracker.edu.distancestudyplatform.dto.SubjectDtoList;
import com.netcracker.edu.distancestudyplatform.model.Subject;
import com.netcracker.edu.distancestudyplatform.ui.service.wrappers.SubjectList;
import com.netcracker.edu.distancestudyplatform.ui.service.SubjectUiService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.List;
import java.util.Objects;

@Service
public class SubjectUiServiceImpl implements SubjectUiService {
    final static private String baseUri = "http://localhost:8080/";

    @Override
    public SubjectDtoList getAllSubjects(){
        String URL = baseUri + "allSubjects";
        return getSubjectRestTemplate(URL);
    }

    public SubjectDtoList getSubjectRestTemplate(String URL){
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL);

        ResponseEntity<SubjectDtoList> response
                = restTemplate.getForEntity(builder.toUriString(), SubjectDtoList.class);

        return new SubjectDtoList(Objects.requireNonNull(response.getBody()).getSubjects());
    }



    //----------------------------------------------------------//
    //Methods need for teacherTT functionality//


    @Override
    public List<Subject> getSubjectsByTeacherId(Long teacherId) {

        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/subjectsByTeacher")
                .queryParam("teacherId", teacherId);


        ResponseEntity<SubjectList> response
                = restTemplate.getForEntity(builder.toUriString(), SubjectList.class);

        return response.getBody().getSubjects();
    }
}
