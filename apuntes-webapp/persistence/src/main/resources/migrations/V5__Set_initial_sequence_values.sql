SELECT setval('programs_program_id_seq', (SELECT max(program_id)
                                          FROM programs));
SELECT setval('courses_course_id_seq', (SELECT max(course_id)
                                        FROM courses));