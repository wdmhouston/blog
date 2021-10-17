create database blog default character set utf8mb4 collate utf8mb4_unicode_ci;

drop table if exists `mlfruit`;
create table  `mlfruit` (
                            `id` bigint not null default 0 comment 'id',
                            `timestamp` bigint not null default 0 comment 'timestamp',
                            `image` varchar(50) not null default '' comment 'image',
                            `image_url` varchar(50) not null default '' comment 'image_url',
                            `result` varchar(20) not null default '' comment 'predicton result',
                            `probability` float(4,3) not null default 0.0 comment 'probability',
                            `content` varchar(300) not null default '' comment 'json content',
                            `ip` varchar(39) not null default '' comment 'ip',
                            primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='mlfruit';