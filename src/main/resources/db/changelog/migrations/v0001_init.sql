--liquibase formatted sql
--tag: '0.1'
--changeset user:1

-- HUMAN COMPONENT TABLE
CREATE TABLE IF NOT EXISTS human_components
(
    component_id serial4      NOT NULL,
    description  varchar(255) NOT NULL,
    CONSTRAINT human_components_pk PRIMARY KEY (component_id)
);

-- BEHAVIORS TABLE
CREATE TABLE IF NOT EXISTS behaviors
(
    behavior_id serial4      NOT NULL,
    description varchar(255) NOT NULL,
    CONSTRAINT behaviors_pk PRIMARY KEY (behavior_id)
);

-- MAIN QUESTIONS TABLE
CREATE TABLE IF NOT EXISTS requirement_questions
(
    requirement_id     varchar(255) NOT NULL,
    human_component_id serial4      NOT NULL,
    behavior_id        serial4      NOT NULL,
    description        varchar(255) NOT NULL,
    rating             int4 NULL,
    CONSTRAINT requirement_questions_pk PRIMARY KEY (requirement_id),
    CONSTRAINT requirement_questions_behaviors_fk FOREIGN KEY (behavior_id) REFERENCES fhtw_mt.behaviors (behavior_id),
    CONSTRAINT requirement_questions_human_components_fk FOREIGN KEY (human_component_id) REFERENCES fhtw_mt.human_components (component_id)
);

-- TABLE WITH ANSWERS ON THE QUESTIONS
CREATE TABLE IF NOT EXISTS question_answers
(
    answer_id               serial4      NOT NULL,
    requirement_question_id varchar(255) NOT NULL,
    description             varchar(255) NOT NULL,
    answer_value            int4 NULL,
    CONSTRAINT question_answers_pk PRIMARY KEY (answer_id),
    CONSTRAINT question_answers_requirement_questions_fk FOREIGN KEY (requirement_question_id) REFERENCES requirement_questions (requirement_id) ON DELETE CASCADE ON UPDATE CASCADE
);

--rollback DROP TABLE IF EXISTS question_answers;
--rollback DROP TABLE requirement_questions;
--rollback DROP TABLE behaviors
--rollback DROP TABLE human_components