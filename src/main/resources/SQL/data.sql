USE todo;

INSERT INTO task(id, name, user_id, is_completed, deadline, completed_at)
VALUES (1, 'Buy groceries', 1, false, '2023-02-26 15:30:00', null);

INSERT INTO task(id, name, user_id, is_completed, deadline, completed_at)
VALUES (2, 'Buy present for friend', 1, true, '2023-02-26 14:30:00', '2023-02-26 13:00:00');

INSERT INTO task(id, name, user_id, is_completed, deadline, completed_at)
VALUES (3, 'Clean the kitchen', 2, false, '2023-03-03 15:30:00', null);

INSERT INTO task(id, name, user_id, is_completed, deadline, completed_at)
VALUES (4, 'Apply for a job', 2, true, '2023-02-26 16:00:00', '2023-02-26 09:00:00');

-- INSERT INTO user()