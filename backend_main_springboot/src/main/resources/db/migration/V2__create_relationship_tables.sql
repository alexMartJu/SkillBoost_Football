CREATE TABLE role_permissions (
    role_id int,
    permission_id int,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES roles(id),
    FOREIGN KEY (permission_id) REFERENCES permissions(id)
);

CREATE TABLE user_roles (
    user_id int,
    role_id int,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES usuarios(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE blacklist_token (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    usuario_id int NOT NULL,
    refresh_token varchar(255) NOT NULL,
    revoke_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
