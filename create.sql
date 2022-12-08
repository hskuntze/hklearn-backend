
    create table tb_content (
       id  bigserial not null,
        description varchar(255),
        title varchar(255),
        video_uri varchar(255),
        offer_id int8,
        primary key (id)
    );

    create table tb_enrollment (
       available boolean not null,
        enroll_moment TIMESTAMP WITHOUT TIME ZONE,
        only_update boolean not null,
        refund_moment TIMESTAMP WITHOUT TIME ZONE,
        offer_id int8 not null,
        user_id int8 not null,
        primary key (offer_id, user_id)
    );

    create table tb_offer (
       id  bigserial not null,
        description varchar(255),
        edition varchar(255),
        end_moment timestamp,
        img_uri varchar(255),
        name varchar(255),
        start_moment timestamp,
        primary key (id)
    );

    create table tb_role (
       id  bigserial not null,
        authority varchar(255),
        primary key (id)
    );

    create table tb_user (
       id  bigserial not null,
        email varchar(255),
        enabled boolean not null,
        name varchar(255),
        password varchar(255),
        verification_code varchar(64),
        primary key (id)
    );

    create table tb_user_role (
       user_id int8 not null,
        role_id int8 not null,
        primary key (user_id, role_id)
    );

    alter table tb_content 
       add constraint FKflbg54trlbfdla111qfb18g7f 
       foreign key (offer_id) 
       references tb_offer;

    alter table tb_enrollment 
       add constraint FKjtgmmofyyh79cpnndwbohooxg 
       foreign key (offer_id) 
       references tb_offer;

    alter table tb_enrollment 
       add constraint FKq4hfr2xc40iew8vo04h7h9s3w 
       foreign key (user_id) 
       references tb_user;

    alter table tb_user_role 
       add constraint FKea2ootw6b6bb0xt3ptl28bymv 
       foreign key (role_id) 
       references tb_role;

    alter table tb_user_role 
       add constraint FK7vn3h53d0tqdimm8cp45gc0kl 
       foreign key (user_id) 
       references tb_user;
