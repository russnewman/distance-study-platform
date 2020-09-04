package com.netcracker.edu.distancestudyplatform.ui.service.impl;


import com.netcracker.edu.distancestudyplatform.ui.service.DatabaseFileUiService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;


@Service
public class DatabaseFileUiServiceImpl implements DatabaseFileUiService {
    @Override
    public void saveDatabaseFile(MultipartFile multipartFile) {
        String baseURL = "http://localhost:8080";
        String URL = baseURL + "/upload";
        RestTemplate restTemplate = new RestTemplate();



        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultipartFile> request = new HttpEntity<>(multipartFile, headers);


        ResponseEntity<MultipartFile> response
                = restTemplate.postForEntity(URL, request, MultipartFile.class);
    }
}
