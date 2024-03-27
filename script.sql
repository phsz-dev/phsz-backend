CREATE TABLE "user"
(
    user_id     SERIAL      NOT NULL PRIMARY KEY,
    user_name   VARCHAR(20) NOT NULL,
    password    VARCHAR(20) NOT NULL,
    authority   VARCHAR(20) NOT NULL,
    phone       VARCHAR(11) NOT NULL,
    email       VARCHAR(30) NOT NULL,
    gender      VARCHAR(2)  NOT NULL,
    information TEXT        NOT NULL
);

CREATE TABLE assay
(
    assay_id    SERIAL      NOT NULL PRIMARY KEY,
    type        VARCHAR(40) NOT NULL,
    sample      TEXT        NOT NULL,
    description TEXT        NOT NULL,
    price       NUMERIC     NOT NULL,
);

CREATE TABLE medicine
(
    medicine_id   SERIAL      NOT NULL PRIMARY KEY,
    medicine_name VARCHAR(20) NOT NULL,
    type          VARCHAR(50) NOT NULL,
    batch_number  VARCHAR(20) NOT NULL,
    validity      VARCHAR(20) NOT NULL,
    usage         VARCHAR(40) NOT NULL,
    price         NUMERIC     NOT NULL
);

CREATE TABLE vaccine
(
    vaccine_id   SERIAL      NOT NULL PRIMARY KEY,
    type         VARCHAR(50) NOT NULL,
    batch_number VARCHAR(20) NOT NULL,
    validity     VARCHAR(20) NOT NULL,
    usage        VARCHAR(40) NOT NULL,
    price        NUMERIC     NOT NULL
);

CREATE TABLE charge
(
    charge_id    SERIAL       NOT NULL PRIMARY KEY,
    case_id      SERIAL       NOT NULL,
    charge_value NUMERIC      NOT NULL,
    medicine_id  SERIAL       NOT NULL REFERENCES medicine (medicine_id),
    vaccine_id   SERIAL       NOT NULL REFERENCES vaccine (vaccine_id),
    assay_id     SERIAL       NOT NULL REFERENCES assay (assay_id),
    others       NUMERIC,
    description  TEXT,
);

CREATE TABLE office
(
    office_id      SERIAL      NOT NULL PRIMARY KEY,
    office_name    TEXT        NOT NULL,
    position       VARCHAR(30) NOT NULL,
    responsibility TEXT        NOT NULL,
    service_time   VARCHAR(30) NOT NULL,
    chairman       VARCHAR(20) NOT NULL
);

CREATE TABLE illness
(
    illness_id   SERIAL      NOT NULL PRIMARY KEY,
    illness_name VARCHAR(20),
    office_id    SERIAL      NOT NULL REFERENCES office (office_id),
);

CREATE TABLE clerk
(
    clerk_id     SERIAL      NOT NULL PRIMARY KEY,
    clerk_name   VARCHAR(20) NOT NULL,
    office_id    SERIAL      NOT NULL REFERENCES office (office_id),
    phone        VARCHAR(11) NOT NULL,
    email        VARCHAR(30) NOT NULL,
    service_time VARCHAR(30) NOT NULL,
    authority    VARCHAR(20) NOT NULL
);

CREATE TABLE "case"
(
    case_id      SERIAL       NOT NULL PRIMARY KEY,
    case_name    VARCHAR(30)  NOT NULL,
    case_time    TIMESTAMP    NOT NULL,
    illness_id   SERIAL       NOT NULL REFERENCES illness (illness_id),
    description  TEXT         NOT NULL,
    medicine_id  SERIAL       NOT NULL REFERENCES medicine (medicine_id),
    vaccine_id   SERIAL       NOT NULL REFERENCES vaccine (vaccine_id),
    assay_id     SERIAL       NOT NULL REFERENCES assay (assay_id),
    charge_id    SERIAL       NOT NULL REFERENCES charge (charge_id),
    charge_value NUMERIC      NOT NULL,
    doctor_id    SERIAL       NOT NULL REFERENCES clerk (clerk_id),
);

CREATE TABLE document
(
    document_id      VARCHAR(20) NOT NULL,
    document_content TEXT        NOT NULL
);

CREATE TABLE examination
(
    examination_id    VARCHAR(10)   NOT NULL PRIMARY KEY,
    paper_id          VARCHAR(10)   NOT NULL,
    examination_time  VARCHAR(20)   NOT NULL,
    examination_user  TEXT          NOT NULL,
    examination_state VARCHAR(10)   NOT NULL
);

CREATE TABLE examination_result
(
    user_id     VARCHAR(10)  NOT NULL,
    paper_id    VARCHAR(10)  NOT NULL,
    answer      VARCHAR(100) NOT NULL,
    total_score INTEGER
);

CREATE TABLE hospitalization
(
    hospitalization_id   SERIAL      NOT NULL PRIMARY KEY,
    hospitalization_name VARCHAR(20) NOT NULL,
    hospitalization_time VARCHAR(20) NOT NULL,
    ward                 VARCHAR(10) NOT NULL,
    discharge            VARCHAR(20) NOT NULL
);

CREATE TABLE map
(
    map_id SERIAL      NOT NULL PRIMARY KEY,
    url    TEXT        NOT NULL
);

CREATE TABLE paper
(
    paper_id        SERIAL      NOT NULL PRIMARY KEY,
    total_score     INTEGER     NOT NULL,
    content         TEXT        NOT NULL,
    time_limit      INTEGER     NOT NULL,
    question_number INTEGER     NOT NULL
);

CREATE TABLE question
(
    question_id SERIAL      NOT NULL PRIMARY KEY,
    type        VARCHAR(10) NOT NULL,
    content     TEXT        NOT NULL,
    answer      TEXT        NOT NULL,
    score       INTEGER     NOT NULL
);

CREATE TABLE paper_content
(
    paper_id    SERIAL      NOT NULL REFERENCES paper (paper_id),
    order       INTEGER     NOT NULL,
    question_id SERIAL      NOT NULL REFERENCES question (question_id)
);

CREATE TABLE play
(
    play_id      VARCHAR(3) NOT NULL,
    play_content TEXT       NOT NULL
);