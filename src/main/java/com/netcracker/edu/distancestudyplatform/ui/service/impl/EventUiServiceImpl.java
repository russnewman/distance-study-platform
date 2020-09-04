package com.netcracker.edu.distancestudyplatform.ui.service.impl;

import com.netcracker.edu.distancestudyplatform.model.Event;
import com.netcracker.edu.distancestudyplatform.ui.dto.EventDto;
import com.netcracker.edu.distancestudyplatform.ui.service.EventUiService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EventUiServiceImpl implements EventUiService {

    @Override
    public void saveEventDto(EventDto eventDto) {

        String baseURL = "http://localhost:8080";
        String URL = baseURL + "/saveEvent";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<EventDto> request = new HttpEntity<>(eventDto, headers);

        ResponseEntity<EventDto> response
                = restTemplate.postForEntity(URL, request, EventDto.class);
    };
}
