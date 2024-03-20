create table assay
(
    assay_id    varchar(20) not null,
    type        varchar(40) not null,
    sample      text        not null,
    description text        not null,
    price       float       not null
);

create table `case`
(
    case_id      varchar(10)  not null
        primary key,
    case_name    varchar(30)  not null,
    case_time    varchar(20)  not null,
    illness_id   varchar(50)  not null,
    description  text         not null,
    medicine_id  varchar(200) null,
    vaccine_id   varchar(100) null,
    assay_id     varchar(40)  null,
    charge_id    int          not null,
    charge_value float        not null,
    doctor_id    varchar(100) not null,
    doctor_name  varchar(100) not null
);

create table charge
(
    charge_id    varchar(10)  not null
        primary key,
    case_id      varchar(10)  not null,
    charge_value float        not null,
    medicine_id  varchar(200) null,
    vaccine_id   varchar(40)  null,
    assay_id     varchar(40)  null,
    others       float        null,
    description  text         null
);

create table clerk
(
    clerk_id     varchar(20) not null
        primary key,
    clerk_name   varchar(20) not null,
    office_id    varchar(20) not null,
    phone        varchar(11) not null,
    email        varchar(30) not null,
    service_time varchar(30) not null,
    authority    varchar(20) not null
);

create table document
(
    document_id      varchar(20) not null,
    document_content text        not null
);

create table examination
(
    examination_id    varchar(10)   not null
        primary key,
    paper_id          varchar(10)   not null,
    examination_time  varchar(20)   not null,
    examination_user  varchar(5000) not null,
    examination_state varchar(10)   not null
);

create table examination_result
(
    user_id     varchar(10)  not null,
    paper_id    varchar(10)  not null,
    answer      varchar(100) not null,
    total_score int          null
);

create table hospitalization
(
    hospitalization_id   varchar(10) not null,
    hospitalization_name varchar(20) not null,
    hospitalization_time varchar(20) not null,
    ward                 varchar(10) not null,
    discharge            varchar(20) not null
);

create table illness
(
    illness_id   varchar(10) null,
    illness_name varchar(20) null,
    office_id    int         null
);

create table map
(
    map_id varchar(10) not null
        primary key,
    url    int         not null
);

create table medicine
(
    medicine_id   varchar(30) not null
        primary key,
    medicine_name varchar(20) not null,
    type          varchar(50) not null,
    batch_number  varchar(20) not null,
    validity      varchar(20) not null,
    `usage`       varchar(40) not null,
    price         float       not null
);

create table office
(
    office_id      varchar(20) not null,
    office_name    int         not null,
    position       varchar(30) not null,
    responsibility text        not null,
    service_time   varchar(30) not null,
    chairman       varchar(20) not null
);

create table paper
(
    paper_id        varchar(10)   not null
        primary key,
    total_score     int           not null,
    content         varchar(1000) not null,
    time_limit      varchar(20)   not null,
    question_number int           not null
);

create table paper_content
(
    paper_id    varchar(10) not null,
    `order`     int         not null,
    question_id varchar(10) not null
);

create table play
(
    play_id      varchar(3) not null,
    play_content text       not null
);

create table question
(
    question_id varchar(10) not null
        primary key,
    type        varchar(10) not null,
    content     text        not null,
    answer      text        not null,
    score       int         not null
);

create table user
(
    user_id    varchar(20) not null
        primary key,
    user_name  varchar(20) not null,
    password   varchar(20) not null,
    authority  varchar(20) not null,
    phone      varchar(11) not null,
    email      varchar(30) not null,
    gender     varchar(2)  not null,
    infomation text        not null
);

create table vaccine
(
    vaccine_id   varchar(20) not null
        primary key,
    type         varchar(50) not null,
    batch_number varchar(20) not null,
    validity     varchar(20) not null,
    `usage`      varchar(40) not null,
    price        float       not null
);


