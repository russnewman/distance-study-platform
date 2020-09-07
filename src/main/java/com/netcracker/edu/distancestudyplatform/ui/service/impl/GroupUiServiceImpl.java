package com.netcracker.edu.distancestudyplatform.ui.service.impl;

import com.netcracker.edu.distancestudyplatform.model.Group;
import com.netcracker.edu.distancestudyplatform.ui.service.wrappers.GroupList;
import com.netcracker.edu.distancestudyplatform.ui.service.GroupUiService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@Service
public class GroupUiServiceImpl implements GroupUiService {


    @Override
    public List<Group> findGroupsByTeacherAndSubject(Long teacherId, String subjectName) {

        String baseUrl = "http://localhost:8080/";
        String URL = baseUrl + "findGroupsByTeacherAndSubject";


        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("teacherId", teacherId)
                .queryParam("subjectName", subjectName);



        ResponseEntity<GroupList> response
                = restTemplate.getForEntity(builder.toUriString(), GroupList.class);
        return response.getBody().getGroups();
    }


}
