CREATE TABLE `t_account`
(
    `id`           BIGINT UNSIGNED  NOT NULL COMMENT '主键ID',
    `user_id`      VARCHAR(64) NOT NULL COMMENT '用户ID',
    `username`     VARCHAR(64) NOT NULL COMMENT '登录账号',
    `account_type` TINYINT UNSIGNED NOT NULL COMMENT '账号类型: 1-用户名, 2-手机号, 3-邮箱号',
    `password_id`  VARCHAR(64) DEFAULT NULL COMMENT '密码ID',
    `status`       TINYINT UNSIGNED DEFAULT '1' COMMENT '账号状态: 1-未激活 2-正常',
    `created_by`   VARCHAR(64) DEFAULT NULL COMMENT '创建人',
    `created_time` DATETIME(3)      DEFAULT NULL COMMENT '创建时间',
    `updated_by`   VARCHAR(64) DEFAULT NULL COMMENT '更新人',
    `updated_time` DATETIME(3)      DEFAULT NULL COMMENT '更新时间',
    `deleted`      TINYINT     DEFAULT 0 COMMENT '删除标记: 0-未删除 1-删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '账户表';

CREATE TABLE `t_password`
(
    `id`            BIGINT UNSIGNED NOT NULL COMMENT '主键ID',
    `account_id`    VARCHAR(64)  NOT NULL COMMENT '账号ID',
    `password_hash` VARCHAR(255) NOT NULL COMMENT '密码哈希',
    `expire_time`   DATETIME(3) DEFAULT NULL COMMENT '密码过期时间',
    `create_by`     VARCHAR(64) DEFAULT NULL COMMENT '创建人',
    `create_time`   DATETIME(3) DEFAULT NULL COMMENT '创建时间',
    `update_by`     VARCHAR(64) DEFAULT NULL COMMENT '更新人',
    `update_time`   DATETIME(3) DEFAULT NULL COMMENT '更新时间',
    `deleted`       TINYINT     DEFAULT 0 COMMENT '删除标记: 0-未删除 1-删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '账号凭证表';

CREATE TABLE `t_user`
(
    `id`           BIGINT UNSIGNED NOT NULL COMMENT '主键ID',
    `user_id`      VARCHAR(64) NOT NULL COMMENT '用户ID',
    `cn_name`      VARCHAR(64) DEFAULT NULL COMMENT '中文名',
    `email`        VARCHAR(32) DEFAULT NULL COMMENT '邮箱号',
    `created_by`   VARCHAR(64) DEFAULT NULL COMMENT '创建人',
    `created_time` DATETIME(3) DEFAULT NULL COMMENT '创建时间',
    `updated_by`   VARCHAR(64) DEFAULT NULL COMMENT '更新人',
    `updated_time` DATETIME(3) DEFAULT NULL COMMENT '更新时间',
    `deleted`      TINYINT     DEFAULT 0 COMMENT '删除标记: 0-未删除 1-删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='用户表';
