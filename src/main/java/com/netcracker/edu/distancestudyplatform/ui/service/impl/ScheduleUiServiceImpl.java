package com.netcracker.edu.distancestudyplatform.ui.service.impl;

import com.netcracker.edu.distancestudyplatform.dto.ScheduleDtoList;
import com.netcracker.edu.distancestudyplatform.model.Group;
import com.netcracker.edu.distancestudyplatform.model.Schedule;
import com.netcracker.edu.distancestudyplatform.ui.service.ScheduleList;
import com.netcracker.edu.distancestudyplatform.ui.service.ScheduleUiService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
public class ScheduleUiServiceImpl implements ScheduleUiService {


    final static private String baseUri = "http://localhost:8080/";


    @Override
    public ScheduleDtoList getStudentFullSchedule(Long studentId){
        String URL = baseUri + "full";
        return getStudentScheduleRestTemplate(URL, studentId);
    }

    public ScheduleDtoList getStudentScheduleRestTemplate(String URL, Long studentId){
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("studentId", studentId);

        ResponseEntity<ScheduleDtoList> response
                = restTemplate.getForEntity(builder.toUriString(), ScheduleDtoList.class);

        return new ScheduleDtoList(Objects.requireNonNull(response.getBody()).getSchedules());
    }


    //----------------------------------------------------------//
    //Methods need for teacherTT functionality//

    @Override
    public List<Schedule> getTeacherSchedule(Long teacherId) {
        String URL = baseUri +  "teacherSchedule";
        return getScheduleRestTemplate(URL, teacherId);
    }


    @Override
    public List<Schedule> getTomorrowTeacherSchedule(Long teacherId) {
        String URL = baseUri + "tomorrowTeacherSchedule";
        return getScheduleRestTemplate(URL, teacherId);
    }


    @Override
    public List<Schedule> getTeacherSchedule(Long teacherId, Boolean weekIsOdd) {
        String URL = baseUri + "teacherWeekSchedule";
        return getScheduleRestTemplate(URL, teacherId, weekIsOdd);
    }


    @Override
    public List<Schedule> getTomorrowTeacherSchedule(Long teacherId, Boolean weekIsOdd) {
        String URL = baseUri + "tomorrowTeacherWeekSchedule";
        return getScheduleRestTemplate(URL, teacherId, weekIsOdd);
    }




    @Override
    public List<Schedule> getSubjectTeacherSchedule(List<Schedule> schedules, Long subjectId){

        RestTemplate restTemplate = new RestTemplate();
//        ScheduleList scheduleList = new ScheduleList(schedules);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUri + "subjectTeacherSchedule")
                .queryParam("subjectId", subjectId);




        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<Schedule>> request = new HttpEntity<>(schedules, headers);

        ResponseEntity<ScheduleList> response
                = restTemplate.postForEntity(builder.toUriString(), request, ScheduleList.class);

        return response.getBody().getSchedule();
    }



    private List<Schedule> getScheduleRestTemplate(String URL, Long teacherId){
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("teacherId", teacherId);

        ResponseEntity<ScheduleList> response
                = restTemplate.getForEntity(builder.toUriString(), ScheduleList.class);

        return response.getBody().getSchedule();
    }


    private List<Schedule> getScheduleRestTemplate(String URL, Long teacherId, Boolean weekIsOdd){
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("teacherId", teacherId)
                .queryParam("weekIsOdd", weekIsOdd);

        ResponseEntity<ScheduleList> response
                = restTemplate.getForEntity(builder.toUriString(), ScheduleList.class);

        return response.getBody().getSchedule();
    }




    //Redefined hashCode and equals of the Schedule
    @Override
    public Map<Schedule, List<Group>> mapScheduleToGroups(List<Schedule> schedules) {
        Map<Schedule, List<Group>> res = new LinkedHashMap<>();
        for (int i = 0; i < schedules.size(); i++) {
            Schedule sch = schedules.get(i);

            if(res.containsKey(sch)){
                res.get(sch).add(sch.getGroup());
            }
            else{
                List<Group> g = new ArrayList<>();
                g.add(sch.getGroup());
                res.put(sch, g);
            }
        }
        return res;
    }


    @Override
    public List<Schedule> mapKeysList(Map<Schedule, List<Group>> map){
        List<Schedule> res = new ArrayList<>();
        for (Map.Entry<Schedule, List<Group>> entry: map.entrySet()){
            res.add(entry.getKey());
        }
        return res;
    }







}
