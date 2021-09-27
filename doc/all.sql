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
)engine=innodb default charset=utf8mb4 comment='ebook';

insert into `ebook`(id, name, description) values(1, 'Spring Boot Introduction', 'Spring Boot Introduction Book Description');
insert into `ebook`(id, name, description) values(2, 'Python Introduction', 'Python Introduction Book Description');
insert into `ebook`(id, name, description) values(3, 'Vue Introduction', 'Vue Introduction Book Description');
insert into `ebook`(id, name, description) values(4, 'Oracle Introduction', 'Oracle Introduction Book Description');
insert into `ebook`(id, name, description) values(5, 'Ajax Introduction', 'Oracle Introduction Book Description');
insert into `ebook`(id, name, description) values(6, 'Python Introduction', 'Oracle Introduction Book Description');
insert into `ebook`(id, name, description) values(7, 'Eclipse Introduction', 'Oracle Introduction Book Description');
insert into `ebook`(id, name, description) values(8, 'Tensor Flow Introduction', 'Oracle Introduction Book Description');