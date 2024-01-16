USE todo;

INSERT INTO task(id, name, userId, isCompleted, deadline, completed_at)
VALUES (1, 'Buy groceries', 'ingrid', false, '2023-02-26 15:30:00', null);

INSERT INTO task(id, name, userId, isCompleted, deadline, completed_at)
VALUES (2, 'Buy present for friend', 'ingrid', true, '2023-02-26 14:30:00', '2023-02-26 13:00:00');

INSERT INTO task(id, name, userId, isCompleted, deadline, completed_at)
VALUES (3, 'Clean the kitchen', 'bertil', false, '2023-03-03 15:30:00', null);

INSERT INTO task(id, name, userId, isCompleted, deadline, completed_at)
VALUES (4, 'Apply for a job', 'bertil', true, '2023-02-26 16:00:00', '2023-02-26 09:00:00');