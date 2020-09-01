package com.netcracker.edu.distancestudyplatform.ui.service.impl;

import com.netcracker.edu.distancestudyplatform.dto.ScheduleDtoList;
import com.netcracker.edu.distancestudyplatform.dto.SubjectDtoList;
import com.netcracker.edu.distancestudyplatform.mappers.SubjectMapper;
import com.netcracker.edu.distancestudyplatform.repository.SubjectRepository;
import com.netcracker.edu.distancestudyplatform.ui.service.SubjectUiService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;
import java.util.stream.Collectors;

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
}
