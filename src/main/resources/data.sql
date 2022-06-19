INSERT INTO tb_user (name, email, password) VALUES ('Alex Brown', 'alex@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Maria Green', 'maria@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Bob Black', 'bob@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_VISITOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_STUDENT');
INSERT INTO tb_role (authority) VALUES ('ROLE_INSTRUCTOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 3);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 4);

INSERT INTO tb_offer (name, img_Uri, description, edition, start_Moment, end_Moment) VALUES ('Java + Spring', 'https://svgshare.com/i/hW1.svg', 'Curso completo da ferramenta Java com seu mais ilustre ecossistema de frameworks: o Spring Boot!', '1.0', TIMESTAMP WITH TIME ZONE '2022-05-12T18:06:00Z', TIMESTAMP WITH TIME ZONE '2023-05-12T18:06:00Z');

INSERT INTO tb_offer (name, img_Uri, description, edition, start_Moment, end_Moment) VALUES ('React + Spring', 'https://svgshare.com/i/h8P.svg', 'Curso completo das ferramentas Spring no Backend com React + TS no Frontend!', '1.0', TIMESTAMP WITH TIME ZONE '2022-05-12T18:06:00Z', TIMESTAMP WITH TIME ZONE '2023-05-12T18:06:00Z');

INSERT INTO tb_enrollment (enroll_Moment, refund_Moment, available, only_Update, user_id, offer_id) VALUES (TIMESTAMP WITH TIME ZONE '2022-05-12T18:34:41Z', null, true, false, 1, 1);
INSERT INTO tb_enrollment (enroll_Moment, refund_Moment, available, only_Update, user_id, offer_id) VALUES (TIMESTAMP WITH TIME ZONE '2022-05-12T18:35:12Z', null, true, false, 1, 2);

INSERT INTO tb_content (title, description, video_Uri, offer_id) VALUES ('Modelagem', 'Modelagem de domínio em nível conceitual com UML', 'https://www.youtube.com/embed/-X9aL2rqKhM', 1);
INSERT INTO tb_content (title, description, video_Uri, offer_id) VALUES ('Leitura de arquivos', 'Como ler arquivo texto CSV em Java', 'https://www.youtube.com/embed/xLDViuYlqGM', 1);
INSERT INTO tb_content (title, description, video_Uri, offer_id) VALUES ('Orientação à Objetos', 'OO e List em Java', 'https://www.youtube.com/embed/Xj-osdBe3TE', 1);
INSERT INTO tb_content (title, description, video_Uri, offer_id) VALUES ('Composição de Objetos', 'OO e Composição de objetos em Java', 'https://www.youtube.com/embed/gj80JEqk5ms', 1);
INSERT INTO tb_content (title, description, video_Uri, offer_id) VALUES ('Primeiro projeto', 'Seu primeiro projeto Java web no Spring Boot', 'https://www.youtube.com/embed/nQr_X62vq-k', 1);
INSERT INTO tb_content (title, description, video_Uri, offer_id) VALUES ('JPA e Hibernate', 'Introdução JPA e Hibernate', 'https://www.youtube.com/embed/CAP1IPgeJkw', 1);

INSERT INTO tb_content (title, description, video_Uri, offer_id) VALUES ('Primeiros passos', 'ReactJS primeiros passos', 'https://www.youtube.com/embed/IOJoJGDowEY', 2);
INSERT INTO tb_content (title, description, video_Uri, offer_id) VALUES ('Projeto DSPlayer', 'ReactJS e CSS - Projeto player de música DSPlayer', 'https://www.youtube.com/embed/eD2rEVSQaU8', 2);