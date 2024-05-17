CREATE TABLE todo.task
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime NULL,
    updated_at    datetime NULL,
    name          VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NULL,
    deadline      datetime     NOT NULL,
    completed_at  datetime NULL,
    user_id       BIGINT       NOT NULL,
    CONSTRAINT pk_task PRIMARY KEY (id)
);

CREATE TABLE todo.user
(
    id       BIGINT       NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    `role`   VARCHAR(255) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE todo.user
    ADD CONSTRAINT uc_user_username UNIQUE (username);

ALTER TABLE todo.task
    ADD CONSTRAINT FK_TASK_ON_USER FOREIGN KEY (user_id) REFERENCES todo.user (id);