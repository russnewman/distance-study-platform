package com.netcracker.edu.distancestudyplatform.ui.controller;
import com.netcracker.edu.distancestudyplatform.model.Group;
import com.netcracker.edu.distancestudyplatform.model.Schedule;

import com.netcracker.edu.distancestudyplatform.ui.service.ScheduleUiService;
import com.netcracker.edu.distancestudyplatform.ui.service.SubjectUiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class TeacherTimetableControllerUi {

    private final ScheduleUiService scheduleUiService;
    private final SubjectUiService subjectUiService;


    @Autowired
    public TeacherTimetableControllerUi(ScheduleUiService scheduleUiService, SubjectUiService subjectUiService) {
        this.scheduleUiService = scheduleUiService;
        this.subjectUiService = subjectUiService;
    }


    @GetMapping("/teacherSchedule/{teacherId}")
    public String getWeekSchedule(
            @PathVariable("teacherId") Long teacherId,
            @RequestParam(value = "weekIsOdd", required = false) Optional<Boolean> weekIsOddOptional,
            @RequestParam(value = "subjectId", required = false) Optional<Long> subjectIdOptional, Model model)
        {

            model.addAttribute("subjects", subjectUiService.getSubjectsByTeacherId(teacherId));
            model.addAttribute("teacherId",teacherId);


            if (weekIsOddOptional.isPresent()){

                Boolean weekIsOdd = weekIsOddOptional.get();
                model.addAttribute("weekIsOdd", weekIsOdd);

                List<Schedule> weekSchedule = scheduleUiService.getTeacherSchedule(teacherId, weekIsOdd);
                List<Schedule> tomorrowSchedule = scheduleUiService.getTomorrowTeacherSchedule(teacherId, weekIsOdd);


                ifSubjectPresent(subjectIdOptional, model, weekSchedule, tomorrowSchedule);
            }
            else{

                List<Schedule> weekSchedule = scheduleUiService.getTeacherSchedule(teacherId);
                List<Schedule> tomorrowSchedule = scheduleUiService.getTomorrowTeacherSchedule(teacherId);

                ifSubjectPresent(subjectIdOptional, model, weekSchedule, tomorrowSchedule);
            }

        return "teacher_schedule";
    }




    private void ifSubjectPresent(@RequestParam(value = "subject", required = false) Optional<Long> subjectIdOptional, Model model, List<Schedule> weekSchedule, List<Schedule> tomorrowSchedule) {

        Map<Schedule, List<Group>> weekScheduleMap ;
        Map<Schedule, List<Group>> tomorrowScheduleMap;

        if (subjectIdOptional.isPresent()){

            Long subjectId = subjectIdOptional.get();
            model.addAttribute("subjectId", subjectId);
            weekScheduleMap = scheduleUiService.mapScheduleToGroups(scheduleUiService.getSubjectTeacherSchedule(weekSchedule, subjectId));
            tomorrowScheduleMap = scheduleUiService.mapScheduleToGroups(scheduleUiService.getSubjectTeacherSchedule(tomorrowSchedule, subjectId));
        }

        else{
            weekScheduleMap = scheduleUiService.mapScheduleToGroups(weekSchedule);
            tomorrowScheduleMap = scheduleUiService.mapScheduleToGroups(tomorrowSchedule);
        }

        List<Schedule> weekScheduleMapKeys = scheduleUiService.mapKeysList(weekScheduleMap);
        List<Schedule> tomorrowScheduleMapKeys = scheduleUiService.mapKeysList(tomorrowScheduleMap);


        model.addAttribute("weekScheduleMap", weekScheduleMap);
        model.addAttribute("weekScheduleMapKeys", weekScheduleMapKeys);
        model.addAttribute("tomorrowScheduleMap", tomorrowScheduleMap);
        model.addAttribute("tomorrowScheduleMapKeys", tomorrowScheduleMapKeys);

    }
}
