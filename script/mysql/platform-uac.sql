CREATE TABLE `user`
(
    `user_id`      VARCHAR(64) NOT NULL COMMENT '用户ID',
    `zh_name`      VARCHAR(64)  DEFAULT NULL COMMENT '中文名',
    `en_name`      VARCHAR(64)  DEFAULT NULL COMMENT '英文名',
    `email`        VARCHAR(32)  DEFAULT NULL COMMENT '邮箱号',
    `password`     VARCHAR(128) DEFAULT NULL COMMENT '密码',
    `gender`       INT          DEFAULT NULL COMMENT '性别 {[[1:男] [2:女]}',
    `created_by`   VARCHAR(64)  DEFAULT NULL COMMENT '创建人',
    `created_time` DATETIME (3) DEFAULT NULL COMMENT '创建时间',
    `updated_by`   VARCHAR(64)  DEFAULT NULL COMMENT '更新人',
    `updated_time` DATETIME (3) DEFAULT NULL COMMENT '更新时间',
    `deleted`      BOOLEAN      DEFAULT FALSE COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = INNODB COMMENT = '用户表';
