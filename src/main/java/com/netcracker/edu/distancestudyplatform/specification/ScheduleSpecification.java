package com.netcracker.edu.distancestudyplatform.specification;

import com.netcracker.edu.distancestudyplatform.model.Schedule;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class ScheduleSpecification {
    class ScheduleWithTeacher implements Specification<Schedule>{
        private Long teacherId;
        @Override
        public Predicate toPredicate(Root<Schedule> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            if(teacherId == null) return cb.isTrue(cb.literal(true));
            return cb.equal(root.get("teacherId"), this.teacherId);
        }
    }

    class ScheduleWithSubjectId implements Specification<Schedule>{
        private Long subjectId;
        @Override
        public Predicate toPredicate(Root<Schedule> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            if(subjectId == null) return cb.isTrue(cb.literal(true));
            return cb.equal(root.get("subjectId"), this.subjectId);
        }
    }

    class ScheduleWithStartTime implements Specification<Schedule>{
        private Long subjectId;
        @Override
        public Predicate toPredicate(Root<Schedule> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            if(subjectId == null) return cb.isTrue(cb.literal(true));
            return cb.equal(root.get("subjectId"), this.subjectId);
        }
    }
}
