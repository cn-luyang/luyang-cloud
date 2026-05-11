CREATE TABLE `t_account`
(
    `id`           VARCHAR(64) NOT NULL COMMENT '主键ID',
    `user_id`      VARCHAR(64) NOT NULL COMMENT '用户ID',
    `account`      VARCHAR(64) NOT NULL COMMENT '账号',
    `account_type` VARCHAR(16) NOT NULL COMMENT '账号类型: {[USER_NAME:用户名] [PHONE:手机号] [EMAIL:邮箱号]}',
    `password_id`  VARCHAR(64) DEFAULT NULL COMMENT '凭证ID',
    `status`       VARCHAR(16) NOT NULL COMMENT '账号状态: {[INACTIVE:未激活] [ACTIVE:未激活] [LOCKED:锁定] [DISABLED:停用]}',
    `created_by`   VARCHAR(64) DEFAULT NULL COMMENT '创建人',
    `created_time` DATETIME (3) DEFAULT NULL COMMENT '创建时间',
    `updated_by`   VARCHAR(64) DEFAULT NULL COMMENT '更新人',
    `updated_time` DATETIME (3) DEFAULT NULL COMMENT '更新时间',
    `deleted`      TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标记: {[1:删除:true] [0:未删除:false]}',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_account` (`account`),
) ENGINE = INNODB COMMENT = '账户表';

CREATE TABLE `t_password`
(
    `id`               VARCHAR(64)  NOT NULL COMMENT '主键ID',
    `account_id`       VARCHAR(64)  NOT NULL COMMENT '账号ID',
    `password_hash`    VARCHAR(255) NOT NULL COMMENT '密码哈希',
    `salt`             VARCHAR(64) DEFAULT NULL COMMENT '盐值',
    `expire_time`      DATETIME(3) DEFAULT NULL COMMENT '密码过期时间',
    `last_change_time` DATETIME(3) DEFAULT NULL COMMENT '上次修改时间',
    `create_by`        VARCHAR(64) DEFAULT NULL COMMENT '创建人',
    `create_time`      DATETIME(3) DEFAULT NULL COMMENT '创建时间',
    `update_by`        VARCHAR(64) DEFAULT NULL COMMENT '更新人',
    `update_time`      DATETIME(3) DEFAULT NULL COMMENT '更新时间',
    `deleted`          TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标记: {[1:删除:true] [0:未删除:false]}',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB COMMENT = '账号凭证表';

CREATE TABLE `t_user`
(
    `id`           VARCHAR(64) NOT NULL COMMENT '主键ID',
    `user_id`      VARCHAR(64) NOT NULL COMMENT '用户ID',
    `cn_name`      VARCHAR(64) DEFAULT NULL COMMENT '中文名',
    `phone`        VARCHAR(16) DEFAULT NULL COMMENT '手机号',
    `email`        VARCHAR(32) DEFAULT NULL COMMENT '邮箱号',
    `created_by`   VARCHAR(64) DEFAULT NULL COMMENT '创建人',
    `created_time` DATETIME (3) DEFAULT NULL COMMENT '创建时间',
    `updated_by`   VARCHAR(64) DEFAULT NULL COMMENT '更新人',
    `updated_time` DATETIME (3) DEFAULT NULL COMMENT '更新时间',
    `deleted`      TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标记: {[1:删除:true] [0:未删除:false]}',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_account` (`user_id`)
) ENGINE = INNODB COMMENT = '用户表';

CREATE TABLE `t_rsa_key`
(
    `key_id`       VARCHAR(64)   NOT NULL COMMENT '密钥ID',
    `public_key`   VARCHAR(2048) NOT NULL COMMENT '公钥',
    `private_key`  VARCHAR(2048) NOT NULL COMMENT '私钥',
    `expire_time`  DATETIME (3)   NOT NULL COMMENT '过期时间',
    `created_by`   VARCHAR(64) DEFAULT NULL COMMENT '创建人',
    `created_time` DATETIME (3) DEFAULT NULL COMMENT '创建时间',
    `updated_by`   VARCHAR(64) DEFAULT NULL COMMENT '更新人',
    `updated_time` DATETIME (3) DEFAULT NULL COMMENT '更新时间',
    `deleted`      TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标记: {[1:删除:true] [0:未删除:false]}',
    PRIMARY KEY (`key_id`) USING BTREE
) ENGINE = INNODB COMMENT='RSA密钥';
