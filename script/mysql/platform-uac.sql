CREATE TABLE `t_user`
(
    `user_id`      VARCHAR(64) NOT NULL COMMENT '用户ID',
    `cn_name`      VARCHAR(64)  DEFAULT NULL COMMENT '中文名',
    `email`        VARCHAR(32)  DEFAULT NULL COMMENT '邮箱号',
    `password`     VARCHAR(128) DEFAULT NULL COMMENT '密码',
    `created_by`   VARCHAR(64)  DEFAULT NULL COMMENT '创建人',
    `created_time` DATETIME (3) DEFAULT NULL COMMENT '创建时间',
    `updated_by`   VARCHAR(64)  DEFAULT NULL COMMENT '更新人',
    `updated_time` DATETIME (3) DEFAULT NULL COMMENT '更新时间',
    `deleted`      BOOLEAN      DEFAULT FALSE COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = INNODB COMMENT = '用户表';


CREATE TABLE `t_rsa_key`
(
    `key_id`       VARCHAR(64)  NOT NULL COMMENT '密钥ID',
    `public_key`   VARCHAR(2048) NOT NULL COMMENT '公钥',
    `private_key`  VARCHAR(2048) NOT NULL COMMENT '私钥',
    `expire_time`  DATETIME (3)   NOT NULL COMMENT '过期时间',
    `created_by`   VARCHAR(64) DEFAULT NULL COMMENT '创建人',
    `created_time` DATETIME (3) DEFAULT NULL COMMENT '创建时间',
    `updated_by`   VARCHAR(64) DEFAULT NULL COMMENT '更新人',
    `updated_time` DATETIME (3) DEFAULT NULL COMMENT '更新时间',
    `deleted`      BOOLEAN     DEFAULT FALSE COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    PRIMARY KEY (`key_id`) USING BTREE
) ENGINE = INNODB COMMENT='RSA密钥';
