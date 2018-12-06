create table patient(
    id int(6) unsigned auto_increment primary key,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    address varchar(200) not null,
    gender varchar(10) not null,
    blood_group varchar(5),
    phone_number varchar(15),
    created_at timestamp not null default now(),
    updated_at timestamp not null default now() on update now()
);

create table user(
    id int(6) unsigned auto_increment primary key,
    username varchar(50) not null,
    password varchar(100) not null
);

alter table user
    add constraint uc_username unique(username);

create table authorities(
    id int(6)  unsigned auto_increment primary key,
    username varchar(50) not null,
    authority varchar(50) not null,
    foreign key (username) references user(username)
);

create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

create table oauth_client_token (
  token_id VARCHAR(256),
  token LONG VARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  username VARCHAR(256),
  client_id VARCHAR(256)
);

    create table oauth_access_token (
      token_id VARCHAR(256),
      token LONG VARBINARY,
      authentication_id VARCHAR(256) PRIMARY KEY,
      username VARCHAR(256),
      client_id VARCHAR(256),
      authentication LONG VARBINARY,
      refresh_token VARCHAR(256)
    );

    create table oauth_refresh_token (
      token_id VARCHAR(256),
      token LONG VARBINARY,
      authentication LONG VARBINARY
    );

    create table oauth_code (
      code VARCHAR(256), authentication LONG VARBINARY
    );

    insert into user(username, password)
        values("jnan", "jnan123");
    insert into authorities(username, authority)
        values("jnan", "ROLE_USER");

    INSERT INTO oauth_client_details
       (client_id, client_secret, scope, authorized_grant_types,
       authorities, access_token_validity, refresh_token_validity)
    VALUES
       ('jnan', 'jnan123', 'read,write,trust', 'password,refresh_token',
       'ROLE_CLIENT,ROLE_TRUSTED_CLIENT', 900, 2592000);