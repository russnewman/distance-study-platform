package com.netcracker.edu.distancestudyplatform.ui.service.impl;

import com.netcracker.edu.distancestudyplatform.dto.ScheduleDtoList;
import com.netcracker.edu.distancestudyplatform.dto.SubjectDto;
import com.netcracker.edu.distancestudyplatform.model.Group;
import com.netcracker.edu.distancestudyplatform.model.Schedule;
import com.netcracker.edu.distancestudyplatform.ui.service.wrappers.ScheduleList;
import com.netcracker.edu.distancestudyplatform.ui.service.ScheduleUiService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScheduleUiServiceImpl implements ScheduleUiService {


    final static private String baseUri = "http://localhost:8080/";


    @Override
    public ScheduleDtoList getStudentFullSchedule(Long studentId){
        String URL = baseUri + "full";
        return getStudentScheduleRestTemplate(URL, studentId);
    }

    @Override
    public ScheduleDtoList getStudentTodaySchedule(Long studentId){
        String URL = baseUri + "todayStudentSchedule";
        return getStudentScheduleRestTemplate(URL, studentId);
    }

    @Override
    public ScheduleDtoList getStudentTomorrowSchedule(Long studentId) {
        String URL = baseUri + "tomorrowStudentSchedule";
        return getStudentScheduleRestTemplate(URL, studentId);
    }

    @Override
    public SubjectDto getStudentCurrentSubject(Long studentId) {
        String URL = baseUri + "currentStudentSubject";
        return getSubjectRestTemplate(URL, studentId);
    }

    @Override
    public SubjectDto getStudentNextSubject(Long studentId) {
        String URL = baseUri + "nextStudentSubject";
        return getSubjectRestTemplate(URL, studentId);
    }

    @Override
    public ScheduleDtoList getStudentSubjectSchedule(Long studentId, Long subjectId){
        String URL = baseUri + "getSubjectStudentSchedule";
        return getStudentScheduleRestTemplate(URL, studentId, subjectId);
    }

    public SubjectDto getSubjectRestTemplate(String URL, Long studentId){
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("studentId", studentId);
        return restTemplate.getForObject(builder.toUriString(), SubjectDto.class);
    }

    public ScheduleDtoList getStudentScheduleRestTemplate(String URL, Long studentId){
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("studentId", studentId);

        ResponseEntity<ScheduleDtoList> response
                = restTemplate.getForEntity(builder.toUriString(), ScheduleDtoList.class);

        return new ScheduleDtoList(Objects.requireNonNull(response.getBody()).getSchedules());
    }

    public ScheduleDtoList getStudentScheduleRestTemplate(String URL, Long studentId, Long subjectId){
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("studentId", studentId)
                .queryParam("subjectId", subjectId);
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
        return map.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }
}
