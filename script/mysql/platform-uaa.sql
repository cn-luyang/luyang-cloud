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
    `created_time`           DATETIME (3) DEFAULT NULL COMMENT '创建时间',
    `updated_by`             VARCHAR(64)  DEFAULT NULL COMMENT '更新人',
    `updated_time`           DATETIME (3) DEFAULT NULL COMMENT '更新时间',
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

CREATE TABLE `oauth2_code`
(
    `code`                  VARCHAR(64)  NOT NULL COMMENT '授权码',
    `client_id`             VARCHAR(64)  NOT NULL COMMENT '客户端ID',
    `user_id`               VARCHAR(64)  NOT NULL COMMENT '用户ID',
    `scope`                 VARCHAR(255) NOT NULL COMMENT '授权范围，多个用空格分隔',
    `redirect_uri`          VARCHAR(255) NOT NULL,
    `nonce`                 VARCHAR(64)  NOT NULL,
    `code_challenge`        VARCHAR(64)  NOT NULL,
    `code_challenge_method` VARCHAR(16)  NOT NULL DEFAULT 'S256',
    `authenticated_at`      DATETIME(3)   NOT NULL COMMENT '认证时间',
    `expires_at`            DATETIME(3)   NOT NULL COMMENT '过期时间（通常 10 分钟）',
    `consumed`              TINYINT(1)    NOT NULL DEFAULT 0 COMMENT '是否已兑换（一次性）',
    `consumed_at`           DATETIME(3)            DEFAULT NULL,
    PRIMARY KEY (`code`) USING BTREE,
) ENGINE = INNODB COMMENT = '授权码表';

