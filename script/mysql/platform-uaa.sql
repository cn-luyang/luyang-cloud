CREATE TABLE `client`
(
    `client_id`       VARCHAR(64)  NOT NULL COMMENT '客户端ID',
    `client_name`     VARCHAR(64)  NOT NULL COMMENT '客户端名称',
    `client_secret`   VARCHAR(128) NOT NULL COMMENT '客户端密钥',
    `grant_types`     VARCHAR(255)  DEFAULT NULL COMMENT '授权类型，多个用逗号分隔',
    `redirect_uris`   VARCHAR(2048) DEFAULT NULL COMMENT '授权回调地址，多个URI用逗号分隔',
    `scopes`          VARCHAR(255) NOT NULL COMMENT '授权范围，多个用空格分隔',
    `client_settings` JSON          DEFAULT NULL COMMENT '客户端配置信息(JSON格式)',
    `token_settings`  JSON          DEFAULT NULL COMMENT '令牌配置信息(JSON格式)',
    `description`     VARCHAR(256)  DEFAULT NULL COMMENT '应用描述',
    `created_by`      VARCHAR(64)   DEFAULT NULL COMMENT '创建人',
    `created_time`    DATETIME (3) DEFAULT NULL COMMENT '创建时间',
    `updated_by`      VARCHAR(64)   DEFAULT NULL COMMENT '更新人',
    `updated_time`    DATETIME (3) DEFAULT NULL COMMENT '更新时间',
    `deleted`         BOOLEAN       DEFAULT FALSE COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = INNODB COMMENT = '客户端表';

CREATE TABLE `token`
(
    `id`                         VARCHAR(64) NOT NULL COMMENT '主键ID',
    `client_id`                  VARCHAR(64) NOT NULL COMMENT '客户端ID',
    `user_id`                    VARCHAR(64) NOT NULL COMMENT '用户ID',
    `attached_info`              JSON        NOT NULL COMMENT '附带信息',
    `access_token`               VARCHAR(64) NOT NULL COMMENT '访问令牌',
    `refresh_token`              VARCHAR(64) NOT NULL COMMENT '刷新令牌',
    `access_token_expires_time`  DATETIME (3) NOT NULL COMMENT 'access_token 过期时间',
    `refresh_token_expires_time` DATETIME (3) NOT NULL COMMENT 'access_token 过期时间',
    `created_by`                 VARCHAR(64) DEFAULT NULL COMMENT '创建人',
    `created_time`               DATETIME (3) DEFAULT NULL COMMENT '创建时间',
    `updated_by`                 VARCHAR(64) DEFAULT NULL COMMENT '更新人',
    `updated_time`               DATETIME (3) DEFAULT NULL COMMENT '更新时间',
    `deleted`                    BOOLEAN     DEFAULT FALSE COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_access_token` (`access_token` ASC) USING BTREE COMMENT 'access_token唯一索引',
    UNIQUE INDEX `uk_refresh_token` (`refresh_token` ASC) USING BTREE COMMENT 'refresh_token唯一索引'
) ENGINE = INNODB COMMENT = 'Token表';

