-- FACULTIES
insert into distance_study_platform.faculties(faculty_name) values ('A');
insert into distance_study_platform.faculties(faculty_name) values ('B');

-- GROUPS
insert into distance_study_platform.student_group(faculty_id, group_name) values (1, 'S15-402');
insert into distance_study_platform.student_group(faculty_id, group_name) values (2, 'M14-609');

-- USERS, STUDENTS
insert into distance_study_platform.users(email, name, surname, login, password) values ('GenaSharpTeeth@gmail.com', 'Gena', 'Crocodile', 'gena', 'qwerty123');
insert into distance_study_platform.users(email, name, surname, login, password) values ('Cheburashka@gmail.com', 'Cheburashka', 'Morozov', 'Cheba', 'qwerty123');
insert into distance_study_platform.users(email, name, surname, login, password) values ('SuperGalya@gmail.com', 'Galya', 'Girl', 'galya', 'qwerty123');

insert into distance_study_platform.students(group_id, user_id) values (1, 1);
insert into distance_study_platform.students(group_id, user_id) values (1, 2);
insert into distance_study_platform.students(group_id, user_id) values (1, 3);

insert into distance_study_platform.users(email, name, surname, login, password) values ('SpongeBob@gmail.com', 'SpongeBob', 'SquarePants', 'sponge', 'qwerty123');
insert into distance_study_platform.users(email, name, surname, login, password) values ('Patrik@gmail.com', 'Patrick', 'Star', 'star', 'qwerty123');
insert into distance_study_platform.users(email, name, surname, login, password) values ('Maestro@gmail.com', 'Squidward', 'Tentacles', 'squidward', 'qwerty123');

insert into distance_study_platform.students(group_id, user_id) values (1, 4);
insert into distance_study_platform.students(group_id, user_id) values (1, 5);
insert into distance_study_platform.students(group_id, user_id) values (1, 6);

-- USERS, TEACHERS
insert into distance_study_platform.users(email, name, surname, login, password) values ('Shapoklyak@gmail.com', 'Shapoklyak', 'OldWoman', 'shapoklyak', 'qwerty123');
insert into distance_study_platform.users(email, name, surname, login, password) values ('RatLariska@gmail.com', 'Lariska', 'rat', 'lariska', 'qwerty123');
insert into distance_study_platform.users(email, name, surname, login, password) values ('RacerPuff@gmail.com', 'Puff', 'Mrs', 'Puff', 'qwerty123');
insert into distance_study_platform.users(email, name, surname, login, password) values ('Thinker@gmail.com', 'Karen', 'Plankton', 'karen', 'qwerty123');

insert into distance_study_platform.teachers(user_id) values (7);
insert into distance_study_platform.teachers(user_id) values (8);
insert into distance_study_platform.teachers(user_id) values (9);
insert into distance_study_platform.teachers(user_id) values (10);

-- SUBJECTS
insert into distance_study_platform.subjects(name, description) values ('Differential calculus', 'In mathematics, differential calculus is a subfield of calculus that studies the rates at which quantities change.[1] It is one of the two traditional divisions of calculus, the other being integral calculus—the study of the area beneath a curve');
insert into distance_study_platform.subjects(name, description) values ('Analytic geometry', 'Analytical Geometry is a branch of geometry in which geometric shapes and their properties are investigated by means of algebra.');
insert into distance_study_platform.subjects(name, description) values ('Mechanics', 'Department of Physics - the study of the movement of bodies in space and the forces that cause this movement.');
insert into distance_study_platform.subjects(name, description) values ('Quantum mechanics', 'Quantum mechanics (QM; also known as quantum physics, quantum theory, the wave mechanical model and matrix mechanics), part of quantum field theory, is a fundamental theory in physics. It describes physical properties of nature on an atomic scale.');
insert into distance_study_platform.subjects(name, description) values ('Сomputer architecture', 'In computer engineering, computer architecture is a set of rules and methods that describe the functionality, organization, and implementation of computer systems. Some definitions of architecture define it as describing the capabilities and programming model of a computer but not a particular implementation');
insert into distance_study_platform.subjects(name, description) values ('Data structure', 'In computer science, a data structure is a data organization, management, and storage format that enables efficient access and modification.');
insert into distance_study_platform.subjects(name, description) values ('Traffic Laws', 'A set of rules governing the duties of road users, as well as technical requirements for vehicles to ensure road safety.');
insert into distance_study_platform.subjects(name, description) values ('Driving', 'Driving lessons');

-- CLASS-TIME
insert into distance_study_platform.class_time(start_time, end_time) VALUES ('08:30', '10:05');
insert into distance_study_platform.class_time(start_time, end_time) VALUES ('10:15', '11:50');
insert into distance_study_platform.class_time(start_time, end_time) VALUES ('11:55', '13:30');
insert into distance_study_platform.class_time(start_time, end_time) VALUES ('12:45', '14:20');
insert into distance_study_platform.class_time(start_time, end_time) VALUES ('14:30', '16:05');
insert into distance_study_platform.class_time(start_time, end_time) VALUES ('16:15', '17:50');
insert into distance_study_platform.class_time(start_time, end_time) VALUES ('18:45', '20:20');
insert into distance_study_platform.class_time(start_time, end_time) VALUES ('20:25', '22:00');

-- TEACHERS_2_SUBJECTS
insert into teachers_2_subjects(subject_id, teacher_id) VALUES (1, 7);
insert into teachers_2_subjects(subject_id, teacher_id) VALUES (2, 7);
insert into teachers_2_subjects(subject_id, teacher_id) VALUES (3, 8);
insert into teachers_2_subjects(subject_id, teacher_id) VALUES (4, 8);
insert into teachers_2_subjects(subject_id, teacher_id) VALUES (5, 9);
insert into teachers_2_subjects(subject_id, teacher_id) VALUES (6, 9);
insert into teachers_2_subjects(subject_id, teacher_id) VALUES (7, 10);
insert into teachers_2_subjects(subject_id, teacher_id) VALUES (7, 10);

-- SCHEDULE
insert into schedule(subject_id, odd_week, class_time_id, class_type, day_name, teacher_id) VALUES (1, 1, 1, 'SEMINAR', '', 7)


