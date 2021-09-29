drop table if exists `test`	;
create table `test` (
	`id` bigint not null comment 'id',
    `name` varchar(50) comment 'name',
    `password` varchar(50) comment 'password',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='test';

insert into `test` (id, name, password) values(1, 'test','password');

drop table if exists `demo`	;
create table `demo` (
                        `id` bigint not null comment 'id',
                        `name` varchar(50) comment 'name',
                        primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='test';
insert into `demo` (id, name) values(1, 'demouser');

#ebook
drop table if exists `ebook`;
create table `ebook`(
        `id` bigint not null comment 'id',
        `name` varchar(50) comment 'name',
        `category1_id` bigint comment 'category1',
        `category2_id` bigint comment 'category2',
        `description` varchar(200) comment 'description',
        `cover` varchar(200) comment 'cover',
        `doc_count` int comment 'doc count',
        `view_count` int comment 'view count',
        `vote_count` int comment 'vote count',
        primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='ebook';

insert into `ebook`(id, name, description) values(1, 'Spring Boot Introduction', 'Spring Boot Introduction Book Description');
insert into `ebook`(id, name, description) values(2, 'Python Introduction', 'Python Introduction Book Description');
insert into `ebook`(id, name, description) values(3, 'Vue Introduction', 'Vue Introduction Book Description');
insert into `ebook`(id, name, description) values(4, 'Oracle Introduction', 'Oracle Introduction Book Description');
insert into `ebook`(id, name, description) values(5, 'Ajax Introduction', 'Oracle Introduction Book Description');
insert into `ebook`(id, name, description) values(6, 'Python Introduction', 'Oracle Introduction Book Description');
insert into `ebook`(id, name, description) values(7, 'Eclipse Introduction', 'Oracle Introduction Book Description');
insert into `ebook`(id, name, description) values(8, 'Tensor Flow Introduction', 'Oracle Introduction Book Description');

drop table if exists `category`;
create table  `category` (
    `id` bigint not null comment 'id',
    `parent` bigint not null default 0 comment 'parent id',
    `name` varchar(50) not null comment 'name',
    `sort` int comment 'sort',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='category';

insert into `category` (id, parent, name, sort) values(100, 000, 'Front Development', 100);
insert into `category` (id, parent, name, sort) values(101, 100, 'Vue', 101);
insert into `category` (id, parent, name, sort) values(102, 100, 'HTML & CSS', 102);
insert into `category` (id, parent, name, sort) values(200, 000, 'Java', 200);
insert into `category` (id, parent, name, sort) values(201, 200, 'Basic Application', 201);
insert into `category` (id, parent, name, sort) values(202, 200, 'Framework Application', 202);
insert into `category` (id, parent, name, sort) values(300, 000, 'Python', 300);
insert into `category` (id, parent, name, sort) values(301, 300, 'Basic Application', 301);
insert into `category` (id, parent, name, sort) values(302, 300, 'Next Level Application', 302);
insert into `category` (id, parent, name, sort) values(400, 000, 'Database', 400);
insert into `category` (id, parent, name, sort) values(401, 400, 'MySQL', 401);
insert into `category` (id, parent, name, sort) values(500, 000, 'Other', 500);
insert into `category` (id, parent, name, sort) values(501, 500, 'Server', 501);
insert into `category` (id, parent, name, sort) values(502, 500, 'Development Tool', 502);
insert into `category` (id, parent, name, sort) values(503, 500,'Hot Server Language', 503);

