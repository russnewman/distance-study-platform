-- FACULTIES
insert into distance_study_platform.faculties(faculty_name) values ('A');
insert into distance_study_platform.faculties(faculty_name) values ('B');

SET @aFacultyId = (select faculty_id from distance_study_platform.faculties where faculty_name = 'A');
SET @bFacultyId = (select faculty_id from distance_study_platform.faculties where faculty_name = 'B');

-- GROUPS
insert into distance_study_platform.student_groups(faculty_id, group_name) values (@aFacultyId, 'S15-402');
insert into distance_study_platform.student_groups(faculty_id, group_name) values (@bFacultyId, 'M14-609');


SET @sGroupId = (select group_id from distance_study_platform.student_groups where group_name = 'S15-402');
SET @mGroupId = (select group_id from distance_study_platform.student_groups where group_name = 'M14-609');

-- USERS, STUDENTS
insert into distance_study_platform.users(email, name, surname, login, password, role) values ('GenaSharpTeeth@gmail.com', 'Gena', 'Crocodile', 'gena', 'qwerty123', 'ROLE_STUDENT');
insert into distance_study_platform.users(email, name, surname, login, password, role) values ('Cheburashka@gmail.com', 'Cheburashka', 'Morozov', 'Cheba', 'qwerty123', 'ROLE_STUDENT');
insert into distance_study_platform.users(email, name, surname, login, password, role) values ('SuperGalya@gmail.com', 'Galya', 'Girl', 'galya', 'qwerty123', 'ROLE_STUDENT');

SET @genaUser = (select user_id from distance_study_platform.users where email = 'GenaSharpTeeth@gmail.com');
SET @ChebaUser = (select user_id from distance_study_platform.users where email = 'Cheburashka@gmail.com');
SET @galyaUser = (select user_id from distance_study_platform.users where email = 'SuperGalya@gmail.com');


insert into distance_study_platform.students(group_id, user_id) values (@sGroupId, @genaUser);
insert into distance_study_platform.students(group_id, user_id) values (@sGroupId, @ChebaUser);
insert into distance_study_platform.students(group_id, user_id) values (@sGroupId, @galyaUser);

insert into distance_study_platform.users(email, name, surname, login, password, role) values ('SpongeBob@gmail.com', 'SpongeBob', 'SquarePants', 'sponge', 'qwerty123', 'ROLE_STUDENT');
insert into distance_study_platform.users(email, name, surname, login, password, role) values ('Patrik@gmail.com', 'Patrick', 'Star', 'star', 'qwerty123', 'ROLE_STUDENT');
insert into distance_study_platform.users(email, name, surname, login, password, role) values ('Maestro@gmail.com', 'Squidward', 'Tentacles', 'squidward', 'qwerty123', 'ROLE_STUDENT');

SET @spongeUser = (select user_id from distance_study_platform.users where email = 'SpongeBob@gmail.com');
SET @patrikUser = (select user_id from distance_study_platform.users where email = 'Patrik@gmail.com');
SET @squidwarUser = (select user_id from distance_study_platform.users where email = 'Maestro@gmail.com');

insert into distance_study_platform.students(group_id, user_id) values (@mGroupId, @spongeUser);
insert into distance_study_platform.students(group_id, user_id) values (@mGroupId, @patrikUser);
insert into distance_study_platform.students(group_id, user_id) values (@mGroupId, @squidwarUser);

-- USERS, TEACHERS
insert into distance_study_platform.users(email, name, surname, login, password, role) values ('Shapoklyak@gmail.com', 'Shapoklyak', 'OldWoman', 'shapoklyak', 'qwerty123', 'ROLE_TEACHER');
insert into distance_study_platform.users(email, name, surname, login, password, role) values ('RatLariska@gmail.com', 'Lariska', 'rat', 'lariska', 'qwerty123', 'ROLE_TEACHER');
insert into distance_study_platform.users(email, name, surname, login, password, role) values ('RacerPuff@gmail.com', 'Puff', 'Mrs', 'Puff', 'qwerty123', 'ROLE_TEACHER');
insert into distance_study_platform.users(email, name, surname, login, password, role) values ('Thinker@gmail.com', 'Karen', 'Plankton', 'karen', 'qwerty123', 'ROLE_TEACHER');

SET @shapoklyakTeacher = (select user_id from distance_study_platform.users where email = 'Shapoklyak@gmail.com');
SET @lariskaTeacher = (select user_id from distance_study_platform.users where email = 'RatLariska@gmail.com');
SET @puffTeacher = (select user_id from distance_study_platform.users where email = 'RacerPuff@gmail.com');
SET @karenTeacher = (select user_id from distance_study_platform.users where email = 'Thinker@gmail.com');

insert into distance_study_platform.teachers(user_id) values (@shapoklyakTeacher);
insert into distance_study_platform.teachers(user_id) values (@lariskaTeacher);
insert into distance_study_platform.teachers(user_id) values (@puffTeacher);
insert into distance_study_platform.teachers(user_id) values (@karenTeacher);

-- SUBJECTS
insert into distance_study_platform.subjects(name, description) values ('Differential calculus', 'In mathematics, differential calculus is a subfield of calculus that studies the rates at which quantities change.[1] It is one of the two traditional divisions of calculus, the other being integral calculus—the study of the area beneath a curve');
insert into distance_study_platform.subjects(name, description) values ('Analytic geometry', 'Analytical Geometry is a branch of geometry in which geometric shapes and their properties are investigated by means of algebra.');
insert into distance_study_platform.subjects(name, description) values ('Mechanics', 'Department of Physics - the study of the movement of bodies in space and the forces that cause this movement.');
insert into distance_study_platform.subjects(name, description) values ('Quantum mechanics', 'Quantum mechanics (QM; also known as quantum physics, quantum theory, the wave mechanical model and matrix mechanics), part of quantum field theory, is a fundamental theory in physics. It describes physical properties of nature on an atomic scale.');
insert into distance_study_platform.subjects(name, description) values ('Сomputer architecture', 'In computer engineering, computer architecture is a set of rules and methods that describe the functionality, organization, and implementation of computer systems. Some definitions of architecture define it as describing the capabilities and programming model of a computer but not a particular implementation');
insert into distance_study_platform.subjects(name, description) values ('Data structure', 'In computer science, a data structure is a data organization, management, and storage format that enables efficient access and modification.');
insert into distance_study_platform.subjects(name, description) values ('Traffic Laws', 'A set of rules governing the duties of road users, as well as technical requirements for vehicles to ensure road safety.');
insert into distance_study_platform.subjects(name, description) values ('Driving', 'Driving lessons');

SET @diffCalcus = (select subject_id from distance_study_platform.subjects where name = 'Differential calculus');
SET @analGeam = (select subject_id from distance_study_platform.subjects where name = 'Analytic geometry');
SET @mechanics = (select subject_id from distance_study_platform.subjects where name = 'Mechanics');
SET @quantum = (select subject_id from distance_study_platform.subjects where name = 'Quantum mechanics');
SET @compArch = (select subject_id from distance_study_platform.subjects where name = 'Сomputer architecture');
SET @dataStructure = (select subject_id from distance_study_platform.subjects where name = 'Data structure');
SET @trafficLaws = (select subject_id from distance_study_platform.subjects where name = 'Traffic Laws');
SET @driving = (select subject_id from distance_study_platform.subjects where name = 'Driving');

-- CLASS-TIME
insert into distance_study_platform.class_time(start_time, end_time) VALUES ('08:30', '10:05');
insert into distance_study_platform.class_time(start_time, end_time) VALUES ('10:15', '11:50');
insert into distance_study_platform.class_time(start_time, end_time) VALUES ('11:55', '13:30');
insert into distance_study_platform.class_time(start_time, end_time) VALUES ('12:45', '14:20');
insert into distance_study_platform.class_time(start_time, end_time) VALUES ('14:30', '16:05');
insert into distance_study_platform.class_time(start_time, end_time) VALUES ('16:15', '17:50');
insert into distance_study_platform.class_time(start_time, end_time) VALUES ('18:45', '20:20');
insert into distance_study_platform.class_time(start_time, end_time) VALUES ('20:25', '22:00');

SET @firstLesson = (select class_time_id from class_time where start_time = '08:30');
SET @secondLesson = (select class_time_id from class_time where start_time = '10:15');
SET @thirdLesson = (select class_time_id from class_time where start_time = '11:55');
SET @fourthLesson = (select class_time_id from class_time where start_time = '12:45');
SET @fifthLesson = (select class_time_id from class_time where start_time = '14:30');
SET @sixthLesson = (select class_time_id from class_time where start_time = '16:15');
SET @eighthLesson = (select class_time_id from class_time where start_time = '18:45');
SET @ninthLesson = (select class_time_id from class_time where start_time = '20:25');

-- TEACHERS_2_SUBJECTS
insert into teachers_2_subjects(subject_id, teacher_id) VALUES (@diffCalcus, @shapoklyakTeacher);
insert into teachers_2_subjects(subject_id, teacher_id) VALUES (@analGeam, @shapoklyakTeacher);
insert into teachers_2_subjects(subject_id, teacher_id) VALUES (@mechanics, @lariskaTeacher);
insert into teachers_2_subjects(subject_id, teacher_id) VALUES (@quantum, @lariskaTeacher);
insert into teachers_2_subjects(subject_id, teacher_id) VALUES (@compArch, @karenTeacher);
insert into teachers_2_subjects(subject_id, teacher_id) VALUES (@dataStructure, @karenTeacher);
insert into teachers_2_subjects(subject_id, teacher_id) VALUES (@trafficLaws, @puffTeacher);
insert into teachers_2_subjects(subject_id, teacher_id) VALUES (@driving, @puffTeacher);

-- SCHEDULE
-- GROUP S
-- MONDAY
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@diffCalcus, false, @sGroupId, @firstLesson, 'SEMINAR', 'MONDAY', @shapoklyakTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@diffCalcus, false, @sGroupId, @secondLesson, 'LECTURE', 'MONDAY', @shapoklyakTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@analGeam, false, @sGroupId, @thirdLesson, 'LECTURE', 'MONDAY', @shapoklyakTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@driving, false, @sGroupId, @fourthLesson, 'LECTURE', 'MONDAY', @puffTeacher);

-- TUESDAY
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@quantum, true, @sGroupId, @firstLesson, 'SEMINAR', 'TUESDAY', @lariskaTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@quantum, true, @sGroupId, @secondLesson, 'LECTURE', 'TUESDAY', @lariskaTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@compArch, true, @sGroupId, @thirdLesson, 'LECTURE', 'TUESDAY', @karenTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@dataStructure, true, @sGroupId, @fourthLesson, 'LECTURE', 'TUESDAY', @karenTeacher);

-- WEDNESDAY
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@quantum, false, @sGroupId, @firstLesson, 'SEMINAR', 'WEDNESDAY', @lariskaTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@quantum, false, @sGroupId, @secondLesson, 'SEMINAR', 'WEDNESDAY', @lariskaTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@compArch, false, @sGroupId, @thirdLesson, 'LECTURE', 'WEDNESDAY', @karenTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@dataStructure, false, @sGroupId, @fourthLesson, 'LECTURE', 'WEDNESDAY', @karenTeacher);

-- THURSDAY
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@quantum, true, @sGroupId, @firstLesson, 'LAB', 'THURSDAY', @lariskaTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@compArch, true, @sGroupId, @secondLesson, 'SEMINAR', 'THURSDAY', @karenTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@compArch, true, @sGroupId, @thirdLesson, 'LECTURE', 'THURSDAY', @karenTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@driving, true, @sGroupId, @fourthLesson, 'SEMINAR', 'THURSDAY', @puffTeacher);

-- FRIDAY
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@compArch, false, @sGroupId, @firstLesson, 'LAB', 'FRIDAY', @lariskaTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@trafficLaws, false, @sGroupId, @secondLesson, 'LECTURE', 'FRIDAY', @karenTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@mechanics, false, @sGroupId, @thirdLesson, 'LAB', 'FRIDAY', @karenTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@mechanics, false, @sGroupId, @fourthLesson, 'LECTURE', 'FRIDAY', @puffTeacher);



-- MONDAY
-- GROUP M
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@diffCalcus, false, @mGroupId, @firstLesson, 'SEMINAR', 'MONDAY', @shapoklyakTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@diffCalcus, false, @mGroupId, @secondLesson, 'LECTURE', 'MONDAY', @shapoklyakTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@analGeam, false, @mGroupId, @thirdLesson, 'LECTURE', 'MONDAY', @shapoklyakTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@driving, false, @mGroupId, @fourthLesson, 'LECTURE', 'MONDAY', @puffTeacher);

-- TUESDAY
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@quantum, true, @mGroupId, @firstLesson, 'SEMINAR', 'TUESDAY', @lariskaTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@quantum, true, @mGroupId, @secondLesson, 'LECTURE', 'TUESDAY', @lariskaTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@compArch, true, @mGroupId, @thirdLesson, 'LECTURE', 'TUESDAY', @karenTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@dataStructure, true, @mGroupId, @fourthLesson, 'LECTURE', 'TUESDAY', @karenTeacher);

-- WEDNESDAY
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@quantum, false, @mGroupId, @firstLesson, 'SEMINAR', 'WEDNESDAY', @lariskaTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@quantum, false, @mGroupId, @secondLesson, 'SEMINAR', 'WEDNESDAY', @lariskaTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@compArch, false, @mGroupId, @thirdLesson, 'LECTURE', 'WEDNESDAY', @karenTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@dataStructure, false, @mGroupId, @fourthLesson, 'LECTURE', 'WEDNESDAY', @karenTeacher);

-- THURSDAY
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@quantum, true, @mGroupId, @firstLesson, 'LAB', 'THURSDAY', @lariskaTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@compArch, true, @mGroupId, @secondLesson, 'SEMINAR', 'THURSDAY', @karenTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@compArch, true, @mGroupId, @thirdLesson, 'LECTURE', 'THURSDAY', @karenTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@driving, true, @mGroupId, @fourthLesson, 'SEMINAR', 'THURSDAY', @puffTeacher);

-- FRIDAY
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@compArch, false, @mGroupId, @firstLesson, 'LAB', 'FRIDAY', @lariskaTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@trafficLaws, false, @mGroupId, @secondLesson, 'LECTURE', 'FRIDAY', @karenTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@mechanics, false, @mGroupId, @thirdLesson, 'LAB', 'FRIDAY', @karenTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@mechanics, false, @mGroupId, @fourthLesson, 'LECTURE', 'FRIDAY', @puffTeacher);

SET @sGroupId = (select group_id from distance_study_platform.student_groups where group_name = 'S15-402');
SET @mGroupId = (select group_id from distance_study_platform.student_groups where group_name = 'M14-609');
SET @diffCalcus = (select subject_id from distance_study_platform.subjects where name = 'Differential calculus');
SET @analGeam = (select subject_id from distance_study_platform.subjects where name = 'Analytic geometry');
SET @mechanics = (select subject_id from distance_study_platform.subjects where name = 'Mechanics');
SET @quantum = (select subject_id from distance_study_platform.subjects where name = 'Quantum mechanics');
SET @compArch = (select subject_id from distance_study_platform.subjects where name = 'Сomputer architecture');
SET @dataStructure = (select subject_id from distance_study_platform.subjects where name = 'Data structure');
SET @trafficLaws = (select subject_id from distance_study_platform.subjects where name = 'Traffic Laws');
SET @driving = (select subject_id from distance_study_platform.subjects where name = 'Driving');
SET @shapoklyakTeacher = (select user_id from distance_study_platform.users where email = 'Shapoklyak@gmail.com');
SET @lariskaTeacher = (select user_id from distance_study_platform.users where email = 'RatLariska@gmail.com');
SET @puffTeacher = (select user_id from distance_study_platform.users where email = 'RacerPuff@gmail.com');
SET @karenTeacher = (select user_id from distance_study_platform.users where email = 'Thinker@gmail.com');

-- EVENTS
insert into distance_study_platform.events(teacher_id, subject_id, description, start_date, end_date, group_id, file_id)
values(@lariskaTeacher, @compArch, 'Ваш вариант в задании сответсвуте вашему номеру в списке группы.', '2020-09-06 13:00:00', '2020-09-11 23:59:59', @mGroupId, '898b9fd8-1396-4419-b714-2fb8bf390400');
insert into distance_study_platform.events(teacher_id, subject_id, description, start_date, end_date, group_id, file_id)
values(@karenTeacher, @trafficLaws, 'Задание с неограниченным сроком сдачи, не влияет на итоговую оценку', '2020-09-01 11:12:00', '2021-05-27 23:59:59', @mGroupId, '62205acd-0a52-44c4-a054-a190c1295319');
insert into distance_study_platform.events(teacher_id, subject_id, description, start_date, end_date, group_id, file_id)
values(@lariskaTeacher, @compArch, 'Для оценки "отлично" достаточно сделать 2 задачи', '2020-09-06 13:00:00', '2020-09-22 12:10:00', @mGroupId, '9580d4f3-ed97-4f26-a613-dcc064089592');


-- ASSIGNMENTS
insert into distance_study_platform.assignments(event_id, student_id, grade, commentary, file_id)
values(1, 6, 4, 'Вариант 6', 'bedfddea-0aa6-4f0c-88de-2e3d80e94c11');
insert into distance_study_platform.assignments(event_id, student_id, grade, commentary, file_id)
values(5, 6, null, null, 'f3cd0413-c799-4f93-860f-2cc61f182f4d');
insert into distance_study_platform.assignments(event_id, student_id, grade, commentary, file_id)
values(5, 5, 2, null, 'f3cd0413-c799-4f93-860f-2cc61f182f4d');
insert into distance_study_platform.assignments(event_id, student_id, grade, commentary, file_id)
values(6, 6, null, 'В задаче #3 опечатка', '1c15ad99-fbf2-466d-87e9-c14b92e0cced');
insert into distance_study_platform.assignments(event_id, student_id, grade, commentary, file_id)
values(6, 5, 2, '...', '1c15ad99-fbf2-466d-87e9-c14b92e0cced');