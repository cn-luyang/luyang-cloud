CREATE TABLE `t_client`
(
    `id`                     BIGINT       NOT NULL COMMENT '主键ID',
    `client_id`              VARCHAR(64)  NOT NULL COMMENT '客户端ID',
    `client_name`            VARCHAR(64)  NOT NULL COMMENT '应用名',
    `client_secret`          VARCHAR(128) NOT NULL COMMENT '客户端密钥',
    `client_secret_plain`    VARCHAR(128) NOT NULL COMMENT '客户端密钥明文(仅初始化时使用)',
    `access_token_validity`  INT           DEFAULT 3600 COMMENT '访问令牌有效期(秒)',
    `refresh_token_validity` INT           DEFAULT 86400 COMMENT '刷新令牌有效期(秒)',
    `grant_types`            VARCHAR(256) NOT NULL COMMENT '支持的授权类型(authorization_code,password,client_credentials,refresh_token,sms)',
    `redirect_uri`           VARCHAR(2048) DEFAULT NULL COMMENT '重定向URI,多个URI用逗号分隔',
    `auto_approve`           BIT(1)        DEFAULT b '0' COMMENT '是否自动批准(跳过授权页面) {[1:是:true] [0:否:false]}',
    `description`            VARCHAR(255)  DEFAULT NULL COMMENT '应用描述',
    `created_by`             VARCHAR(64)   DEFAULT NULL COMMENT '创建人',
    `created_time`           DATETIME (3) DEFAULT NULL COMMENT '创建时间',
    `updated_by`             VARCHAR(64)   DEFAULT NULL COMMENT '更新人',
    `updated_time`           DATETIME (3) DEFAULT NULL COMMENT '更新时间',
    `deleted`                BIT(1)        DEFAULT b '0' COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    PRIMARY key (`id`) USING BTREE,
    UNIQUE index `uniq_client_id` (`client_id` asc) USING BTREE COMMENT '客户端ID唯一索引'
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC COMMENT = '客户端表';

CREATE TABLE `t_token`
(
    `id`                         BIGINT      NOT NULL COMMENT '主键ID',
    `client_id`                  VARCHAR(64) NOT NULL COMMENT '客户端ID',
    `user_id`                    VARCHAR(64) NOT NULL COMMENT '用户编号',
    `user_info`                  JSON        NOT NULL COMMENT '用户信息',
    `access_token`               VARCHAR(64) NOT NULL COMMENT '访问令牌',
    `refresh_token`              VARCHAR(64) NOT NULL COMMENT '刷新令牌',
    `access_token_expires_time`  DATETIME (3) NOT NULL COMMENT 'access_token 过期时间',
    `refresh_token_expires_time` DATETIME (3) NOT NULL COMMENT 'access_token 过期时间',
    `created_by`                 VARCHAR(64) DEFAULT NULL COMMENT '创建人',
    `created_time`               DATETIME (3) DEFAULT NULL COMMENT '创建时间',
    `updated_by`                 VARCHAR(64) DEFAULT NULL COMMENT '更新人',
    `updated_time`               DATETIME (3) DEFAULT NULL COMMENT '更新时间',
    `deleted`                    BIT(1)      DEFAULT b '0' COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE index `uniq_access_token` (`access_token` ASC) USING BTREE COMMENT 'access_token唯一索引',
    UNIQUE index `uniq_refresh_token` (`refresh_token` ASC) USING BTREE COMMENT 'refresh_token唯一索引'
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC COMMENT = 'Token表';
