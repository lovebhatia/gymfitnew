INSERT INTO public.workout_program 
(days_per_week, created_at, description, duration_range, "level", main_goal, published_by, rating, "sequence", workout_program_name, icon, image_name)
VALUES
('3-5', now(), '', '3-4 Months', 'Intermediate', 'Muscle Building', 'Gym Fit','2.5','1','Full Body', '', '');

INSERT INTO public.workout_program 
(days_per_week, created_at, description, duration_range, "level", main_goal, published_by, rating, "sequence", workout_program_name, icon, image_name)
VALUES
('4', now(), '', '3-4 Months', 'Intermediate', 'Muscle Building', 'Gym Fit','2.5','1','Upper/Lower Split', '', '');

INSERT INTO public.workout_program 
(days_per_week, created_at, description, duration_range, "level", main_goal, published_by, rating, "sequence", workout_program_name, icon, image_name)
VALUES
('3-5', now(), '', '3-4 Months', 'Intermediate', 'Muscle Building', 'Gym Fit','2.5','1','Body Part Split', '', '');

INSERT INTO public.workout_program 
(days_per_week, created_at, description, duration_range, "level", main_goal, published_by, rating, "sequence", workout_program_name, icon, image_name)
VALUES
('3-5', now(), '', '3-4 Months', 'Intermediate', 'Muscle Building', 'Gym Fit','2.5','1','Cardiovascular', '', '');

INSERT INTO public.workout_program 
(days_per_week, created_at, description, duration_range, "level", main_goal, published_by, rating, "sequence", workout_program_name, icon, image_name)
VALUES
('3-5', now(), '', '3-4 Months', 'Intermediate', 'Muscle Building', 'Gym Fit','2.5','1','High-Intensity Interval Training (HIIT)', '', '');

INSERT INTO public.workout_program 
(days_per_week, created_at, description, duration_range, "level", main_goal, published_by, rating, "sequence", workout_program_name, icon, image_name)
VALUES
('3-5', now(), '', '3-4 Months', 'Intermediate', 'Muscle Building', 'Gym Fit','2.5','1','Flexibility and Mobility', '', '');

INSERT INTO public.workout_program
(days_per_week, created_at, description, duration_range, "level", main_goal, published_by, rating, "sequence", workout_program_name,icon, image_name)
VALUES
('3', now(), '', '3-4 Months', 'Intermediate', 'Muscle Building', 'Gym Fit','2.5','1', 'Push/Pull/Legs', '', '');


----------------------------------------------------------------------------------------------------------------------

INSERT INTO public.workout(created_at,workout,image_name,show_sequence) VALUES(now(),'Chest','chest_home.jpg',1);
INSERT INTO public.workout(created_at,workout,image_name,show_sequence) VALUES(now(),'Back','back_home.jpg',2);
INSERT INTO public.workout(created_at,workout,image_name,show_sequence) VALUES(now(),'Biceps','biceps_home.jpg',3);
INSERT INTO public.workout(created_at,workout,image_name,show_sequence) VALUES(now(),'Shoulder','shoulder_home.jpg',4);
INSERT INTO public.workout(created_at,workout,image_name,show_sequence) VALUES(now(),'Tricep','tricep_home.jpg',5);
INSERT INTO public.workout(created_at,workout,image_name,show_sequence) VALUES(now(),'Abs','abs_home.jpg',6);


-----------------------------------------------------------------------------------------------------------------------

INSERT INTO public.workout_program_workout (workout_id, workout_program_id) VALUES(1, 3);
INSERT INTO public.workout_program_workout (workout_id, workout_program_id) VALUES(2, 3);
INSERT INTO public.workout_program_workout (workout_id, workout_program_id) VALUES(3, 3);
INSERT INTO public.workout_program_workout (workout_id, workout_program_id) VALUES(4, 3);
INSERT INTO public.workout_program_workout (workout_id, workout_program_id) VALUES(5, 3);
INSERT INTO public.workout_program_workout (workout_id, workout_program_id) VALUES(6, 3);

-----------------------------------------------------------------------------------------------------------------------

INSERT INTO public.exercise(created_at, set_range, gif_name, exercise_name) VALUES(now(), '3-5', 'BenchPress.gif', 'Bench Press');
INSERT INTO public.exercise(created_at, set_range, gif_name, exercise_name) VALUES(now(), '3-5', 'CableLowFly.gif', 'Cable Low Fly');
INSERT INTO public.exercise(created_at, set_range, gif_name, exercise_name) VALUES(now(), '3-5', 'CableStandingFly.gif', 'Cable Standing Fly');
INSERT INTO public.exercise(created_at, set_range, gif_name, exercise_name) VALUES(now(), '3-5', 'ChestDip.gif', 'Chest Dip');
INSERT INTO public.exercise(created_at, set_range, gif_name, exercise_name) VALUES(now(), '3-5', 'DumbellBenchPress.gif', 'DumbellBenchPress');
INSERT INTO public.exercise(created_at, set_range, gif_name, exercise_name) VALUES(now(), '3-5', 'Fly.gif', 'Fly');
INSERT INTO public.exercise(created_at, set_range, gif_name, exercise_name) VALUES(now(), '3-5', 'InclineBenchPress.gif', 'Incline Bench Press');
INSERT INTO public.exercise(created_at, set_range, gif_name, exercise_name) VALUES(now(), '3-5', 'InclineDumbbellBenchPress.gif', 'Incline Dumbbell Bench Press');
INSERT INTO public.exercise(created_at, set_range, gif_name, exercise_name) VALUES(now(), '3-5', 'InclineFly.gif', 'Incline Fly');
INSERT INTO public.exercise(created_at, set_range, gif_name, exercise_name) VALUES(now(), '3-5', 'LeverSeatedFly.gif', 'LeverSeatedFly');
INSERT INTO public.exercise(created_at, set_range, gif_name, exercise_name) VALUES(now(), '3-5', 'Pullover.gif', 'Pullover');
INSERT INTO public.exercise(created_at, set_range, gif_name, exercise_name) VALUES(now(), '3-5', 'Push-up.gif', 'Push-up');

------------------------------------------------------------------------------------------------------------------------

INSERT INTO public.workout_exercises (exercise_id, workout_id) VALUES(1, 1);
INSERT INTO public.workout_exercises (exercise_id, workout_id) VALUES(2, 1);
INSERT INTO public.workout_exercises (exercise_id, workout_id) VALUES(3, 1);
INSERT INTO public.workout_exercises (exercise_id, workout_id) VALUES(4, 1);
INSERT INTO public.workout_exercises (exercise_id, workout_id) VALUES(5, 1);
INSERT INTO public.workout_exercises (exercise_id, workout_id) VALUES(6, 1);
INSERT INTO public.workout_exercises (exercise_id, workout_id) VALUES(7, 1);
INSERT INTO public.workout_exercises (exercise_id, workout_id) VALUES(8, 1);
INSERT INTO public.workout_exercises (exercise_id, workout_id) VALUES(9, 1);
INSERT INTO public.workout_exercises (exercise_id, workout_id) VALUES(10, 1);
INSERT INTO public.workout_exercises (exercise_id, workout_id) VALUES(11, 1);
INSERT INTO public.workout_exercises (exercise_id, workout_id) VALUES(12, 1);

----------------------------------------------------------------------------------------------------------------------


INSERT INTO public.gym_roles ("name") VALUES(1, 'ROLE_ADMIN');
INSERT INTO public.gym_roles ("name") VALUES(2, 'ROLE_USER');
