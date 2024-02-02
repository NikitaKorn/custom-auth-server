INSERT INTO roles (role_name) VALUES ('ROLE_ADMIN');


INSERT INTO users (email, login, password) VALUES ('test@mail.ru', 'user', '123'),
                                                   ('test2@mail.ru', 'admin', '123');

INSERT INTO user_role (role_id, user_id) VALUES ('1', '2');