create table `t_uac_user`
(
    `id`           BIGINT       not null COMMENT '主键ID',
    `user_id`      VARCHAR(64)  not null COMMENT '用户ID',
    `name`         VARCHAR(64)  not null COMMENT '姓名',
    `email`        varchar(32)  not null COMMENT '邮箱号',
    `password`     varchar(128) not null COMMENT '密码',
    `gender`       int          not null COMMENT '性别 {[0:保密] [1:男] [2:女]}',

    `created_by`   VARCHAR(64) default null COMMENT '创建人',
    `created_time` DATETIME(3)  default null COMMENT '创建时间',
    `updated_by`   VARCHAR(64) default null COMMENT '更新人',
    `updated_time` DATETIME(3)  default null COMMENT '更新时间',
    `deleted`      BIT(1)      default b'0' COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    `deleted_time` DATETIME(3)  default null COMMENT '删除时间',
    primary key (`id`) using BTREE,
    unique index `uniq_user_id` (`user_id` asc) using BTREE COMMENT '用户ID唯一索引'
) ENGINE = INNODB
  default CHARSET = utf8mb4
  collate = utf8mb4_general_ci
  ROW_FORMAT = dynamic COMMENT = '用户表';
