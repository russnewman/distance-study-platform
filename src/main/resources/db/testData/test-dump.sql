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
insert into distance_study_platform.users(email, name, surname, login, password) values ('GenaSharpTeeth@gmail.com', 'Gena', 'Crocodile', 'gena', 'qwerty123');
insert into distance_study_platform.users(email, name, surname, login, password) values ('Cheburashka@gmail.com', 'Cheburashka', 'Morozov', 'Cheba', 'qwerty123');
insert into distance_study_platform.users(email, name, surname, login, password) values ('SuperGalya@gmail.com', 'Galya', 'Girl', 'galya', 'qwerty123');

SET @genaUser = (select user_id from distance_study_platform.users where email = 'GenaSharpTeeth@gmail.com');
SET @ChebaUser = (select user_id from distance_study_platform.users where email = 'Cheburashka@gmail.com');
SET @galyaUser = (select user_id from distance_study_platform.users where email = 'SuperGalya@gmail.com');


insert into distance_study_platform.students(group_id, user_id) values (@sGroupId, @genaUser);
insert into distance_study_platform.students(group_id, user_id) values (@sGroupId, @ChebaUser);
insert into distance_study_platform.students(group_id, user_id) values (@sGroupId, @galyaUser);

insert into distance_study_platform.users(email, name, surname, login, password) values ('SpongeBob@gmail.com', 'SpongeBob', 'SquarePants', 'sponge', 'qwerty123');
insert into distance_study_platform.users(email, name, surname, login, password) values ('Patrik@gmail.com', 'Patrick', 'Star', 'star', 'qwerty123');
insert into distance_study_platform.users(email, name, surname, login, password) values ('Maestro@gmail.com', 'Squidward', 'Tentacles', 'squidward', 'qwerty123');

SET @spongeUser = (select user_id from distance_study_platform.users where email = 'SpongeBob@gmail.com');
SET @patrikUser = (select user_id from distance_study_platform.users where email = 'Patrik@gmail.com');
SET @squidwarUser = (select user_id from distance_study_platform.users where email = 'Maestro@gmail.com');

insert into distance_study_platform.students(group_id, user_id) values (@mGroupId, @spongeUser);
insert into distance_study_platform.students(group_id, user_id) values (@mGroupId, @patrikUser);
insert into distance_study_platform.students(group_id, user_id) values (@mGroupId, @squidwarUser);

-- USERS, TEACHERS
insert into distance_study_platform.users(email, name, surname, login, password) values ('Shapoklyak@gmail.com', 'Shapoklyak', 'OldWoman', 'shapoklyak', 'qwerty123');
insert into distance_study_platform.users(email, name, surname, login, password) values ('RatLariska@gmail.com', 'Lariska', 'rat', 'lariska', 'qwerty123');
insert into distance_study_platform.users(email, name, surname, login, password) values ('RacerPuff@gmail.com', 'Puff', 'Mrs', 'Puff', 'qwerty123');
insert into distance_study_platform.users(email, name, surname, login, password) values ('Thinker@gmail.com', 'Karen', 'Plankton', 'karen', 'qwerty123');

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
values(@diffCalcus, 0, @sGroupId, @firstLesson, 'SEMINAR', 'MONDAY', @shapoklyakTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@diffCalcus, 0, @sGroupId, @secondLesson, 'LECTURE', 'MONDAY', @shapoklyakTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@analGeam, 0, @sGroupId, @thirdLesson, 'LECTURE', 'MONDAY', @shapoklyakTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@driving, 0, @sGroupId, @fourthLesson, 'LECTURE', 'MONDAY', @puffTeacher);

-- TUESDAY
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@quantum, 1, @sGroupId, @firstLesson, 'SEMINAR', 'TUESDAY', @lariskaTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@quantum, 1, @sGroupId, @secondLesson, 'LECTURE', 'TUESDAY', @lariskaTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@compArch, 1, @sGroupId, @thirdLesson, 'LECTURE', 'TUESDAY', @karenTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@dataStructure, 1, @sGroupId, @fourthLesson, 'LECTURE', 'TUESDAY', @karenTeacher);

-- WEDNESDAY
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@quantum, 0, @sGroupId, @firstLesson, 'SEMINAR', 'WEDNESDAY', @lariskaTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@quantum, 0, @sGroupId, @secondLesson, 'SEMINAR', 'WEDNESDAY', @lariskaTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@compArch, 0, @sGroupId, @thirdLesson, 'LECTURE', 'WEDNESDAY', @karenTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@dataStructure, 0, @sGroupId, @fourthLesson, 'LECTURE', 'WEDNESDAY', @karenTeacher);

-- THURSDAY
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@quantum, 1, @sGroupId, @firstLesson, 'LAB', 'THURSDAY', @lariskaTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@compArch, 1, @sGroupId, @secondLesson, 'SEMINAR', 'THURSDAY', @karenTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@compArch, 1, @sGroupId, @thirdLesson, 'LECTURE', 'THURSDAY', @karenTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@driving, 1, @sGroupId, @fourthLesson, 'SEMINAR', 'THURSDAY', @puffTeacher);

-- FRIDAY
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@compArch, 1, @sGroupId, @firstLesson, 'LAB', 'FRIDAY', @lariskaTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@trafficLaws, 1, @sGroupId, @secondLesson, 'LECTURE', 'FRIDAY', @karenTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@mechanics, 1, @sGroupId, @thirdLesson, 'LAB', 'FRIDAY', @karenTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@mechanics, 1, @sGroupId, @fourthLesson, 'LECTURE', 'FRIDAY', @puffTeacher);



-- MONDAY
-- GROUP M
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@diffCalcus, 0, @mGroupId, @firstLesson, 'SEMINAR', 'MONDAY', @shapoklyakTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@diffCalcus, 0, @mGroupId, @secondLesson, 'LECTURE', 'MONDAY', @shapoklyakTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@analGeam, 0, @mGroupId, @thirdLesson, 'LECTURE', 'MONDAY', @shapoklyakTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@driving, 0, @mGroupId, @fourthLesson, 'LECTURE', 'MONDAY', @puffTeacher);

-- TUESDAY
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@quantum, 1, @mGroupId, @firstLesson, 'SEMINAR', 'TUESDAY', @lariskaTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@quantum, 1, @mGroupId, @secondLesson, 'LECTURE', 'TUESDAY', @lariskaTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@compArch, 1, @mGroupId, @thirdLesson, 'LECTURE', 'TUESDAY', @karenTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@dataStructure, 1, @mGroupId, @fourthLesson, 'LECTURE', 'TUESDAY', @karenTeacher);

-- WEDNESDAY
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@quantum, 0, @mGroupId, @firstLesson, 'SEMINAR', 'WEDNESDAY', @lariskaTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@quantum, 0, @mGroupId, @secondLesson, 'SEMINAR', 'WEDNESDAY', @lariskaTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@compArch, 0, @mGroupId, @thirdLesson, 'LECTURE', 'WEDNESDAY', @karenTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@dataStructure, 0, @mGroupId, @fourthLesson, 'LECTURE', 'WEDNESDAY', @karenTeacher);

-- THURSDAY
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@quantum, 1, @mGroupId, @firstLesson, 'LAB', 'THURSDAY', @lariskaTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@compArch, 1, @mGroupId, @secondLesson, 'SEMINAR', 'THURSDAY', @karenTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@compArch, 1, @mGroupId, @thirdLesson, 'LECTURE', 'THURSDAY', @karenTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@driving, 1, @mGroupId, @fourthLesson, 'SEMINAR', 'THURSDAY', @puffTeacher);

-- FRIDAY
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@compArch, 1, @mGroupId, @firstLesson, 'LAB', 'FRIDAY', @lariskaTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@trafficLaws, 1, @mGroupId, @secondLesson, 'LECTURE', 'FRIDAY', @karenTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@mechanics, 1, @mGroupId, @thirdLesson, 'LAB', 'FRIDAY', @karenTeacher);
insert into distance_study_platform.schedule(subject_id, odd_week, group_id, class_time_id, class_type, day_name, teacher_id)
values(@mechanics, 1, @mGroupId, @fourthLesson, 'LECTURE', 'FRIDAY', @puffTeacher);