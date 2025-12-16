CREATE TABLE `oauth2_client`
(
    `client_id`              VARCHAR(64)  NOT NULL COMMENT '客户端ID',
    `client_name`            VARCHAR(64)  NOT NULL COMMENT '客户端名称',
    `client_secret`          VARCHAR(128) NOT NULL COMMENT '客户端密钥',
    `grant_types`            JSON         NOT NULL COMMENT '授权类型列表',
    `redirect_uris`          JSON         DEFAULT NULL COMMENT '授权回调地址列表',
    `scopes`                 JSON         NOT NULL COMMENT '授权范围列表',
    `access_token_validity`  INT          DEFAULT 3600 COMMENT '访问令牌有效期(秒)',
    `refresh_token_validity` INT          DEFAULT 86400 COMMENT '刷新令牌有效期(秒)',
    `description`            VARCHAR(256) DEFAULT NULL COMMENT '应用描述',
    `created_by`             VARCHAR(64)  DEFAULT NULL COMMENT '创建人',
    `created_time`           DATETIME(3)  DEFAULT NULL COMMENT '创建时间',
    `updated_by`             VARCHAR(64)  DEFAULT NULL COMMENT '更新人',
    `updated_time`           DATETIME(3)  DEFAULT NULL COMMENT '更新时间',
    `deleted`                BOOLEAN      DEFAULT FALSE COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = INNODB COMMENT = '客户端表';

CREATE TABLE `oauth2_token`
(
    `id`                         VARCHAR(64) NOT NULL COMMENT '主键ID',
    `client_id`                  VARCHAR(64) NOT NULL COMMENT '客户端ID',
    `user_id`                    VARCHAR(64) NOT NULL COMMENT '用户ID',
    `attached_info`              JSON        NOT NULL COMMENT '附带信息',
    `access_token`               VARCHAR(64) NOT NULL COMMENT '访问令牌',
    `refresh_token`              VARCHAR(64) NOT NULL COMMENT '刷新令牌',
    `access_token_expires_time`  DATETIME(3) NOT NULL COMMENT 'access_token 过期时间',
    `refresh_token_expires_time` DATETIME(3) NOT NULL COMMENT 'access_token 过期时间',
    `created_by`                 VARCHAR(64) DEFAULT NULL COMMENT '创建人',
    `created_time`               DATETIME(3) DEFAULT NULL COMMENT '创建时间',
    `updated_by`                 VARCHAR(64) DEFAULT NULL COMMENT '更新人',
    `updated_time`               DATETIME(3) DEFAULT NULL COMMENT '更新时间',
    `deleted`                    BOOLEAN     DEFAULT FALSE COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_access_token` (`access_token` ASC) USING BTREE COMMENT 'access_token唯一索引',
    UNIQUE INDEX `uk_refresh_token` (`refresh_token` ASC) USING BTREE COMMENT 'refresh_token唯一索引'
) ENGINE = INNODB COMMENT = 'Token表';

CREATE TABLE `oauth2_code`
(
    `code`                  VARCHAR(64)  NOT NULL COMMENT '授权码',
    `client_id`             VARCHAR(64)  NOT NULL COMMENT '客户端ID',
    `user_id`               VARCHAR(64)  NOT NULL COMMENT '用户ID',
    `scopes`                JSON         NOT NULL COMMENT '授权范围列表',
    `redirect_uri`          VARCHAR(255) NOT NULL COMMENT '授权回调地址',
    `nonce`                 VARCHAR(64)  NOT NULL COMMENT '防重放攻击的随机值',
    `code_challenge`        VARCHAR(64)  NOT NULL COMMENT 'PKCE码',
    `code_challenge_method` VARCHAR(16)  NOT NULL COMMENT 'PKCE算法',
    `expires_time`          DATETIME(3)  NOT NULL COMMENT '过期时间（通常 10 分钟）',
    `used`                  BOOLEAN     DEFAULT FALSE COMMENT '是否已使用',
    `used_time`             DATETIME(3) DEFAULT NULL COMMENT '使用时间',
    `created_by`            VARCHAR(64) DEFAULT NULL COMMENT '创建人',
    `created_time`          DATETIME(3) DEFAULT NULL COMMENT '创建时间',
    `updated_by`            VARCHAR(64) DEFAULT NULL COMMENT '更新人',
    `updated_time`          DATETIME(3) DEFAULT NULL COMMENT '更新时间',
    `deleted`               BOOLEAN     DEFAULT FALSE COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    PRIMARY KEY (`code`) USING BTREE
) ENGINE = INNODB COMMENT = '授权码表';
