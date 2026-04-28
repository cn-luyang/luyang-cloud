CREATE TABLE `oauth2_client`
(
    `client_id`                   VARCHAR(64)  NOT NULL COMMENT '客户端ID',
    `client_name`                 VARCHAR(64)  NOT NULL COMMENT '客户端名称',
    `client_secret`               VARCHAR(128) NOT NULL COMMENT '客户端密钥',
    `redirect_uris`               JSON         DEFAULT NULL COMMENT '授权回调地址列表',
    `access_token_validity`       INT          DEFAULT NULL COMMENT '访问令牌有效期 (默认2小时)',
    `refresh_token_validity`      INT          DEFAULT NULL COMMENT '刷新令牌有效期 (默认7天)',
    `description`                 VARCHAR(256) DEFAULT NULL COMMENT '应用描述',
    `created_by`                  VARCHAR(64)  DEFAULT NULL COMMENT '创建人',
    `created_time`                DATETIME(3)  DEFAULT NULL COMMENT '创建时间',
    `updated_by`                  VARCHAR(64)  DEFAULT NULL COMMENT '更新人',
    `updated_time`                DATETIME(3)  DEFAULT NULL COMMENT '更新时间',
    `deleted`                     BOOLEAN      DEFAULT FALSE COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = INNODB COMMENT = '客户端表';


CREATE TABLE `t_token`
(
    `id`                         VARCHAR(64) NOT NULL COMMENT '主键ID',
    `user_id`                    VARCHAR(64) DEFAULT NULL COMMENT '用户ID',
    `access_token`               VARCHAR(64) NOT NULL COMMENT '访问令牌',
    `refresh_token`              VARCHAR(64) DEFAULT NULL COMMENT '刷新令牌',
    `token_issued_time`          DATETIME(3) NOT NULL COMMENT 'Token 签发时间',
    `access_token_expires_time`  DATETIME(3) NOT NULL COMMENT 'Access Token 过期时间',
    `refresh_token_expires_time` DATETIME(3) DEFAULT NULL COMMENT 'Refresh Token 过期时间',
    `extra_info`                 JSON        DEFAULT NULL COMMENT '附加信息',
    `created_by`                 VARCHAR(64) DEFAULT NULL COMMENT '创建人',
    `created_time`               DATETIME(3) DEFAULT NULL COMMENT '创建时间',
    `updated_by`                 VARCHAR(64) DEFAULT NULL COMMENT '更新人',
    `updated_time`               DATETIME(3) DEFAULT NULL COMMENT '更新时间',
    `deleted`                    BOOLEAN     DEFAULT FALSE COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_access_token` (`access_token` ASC) USING BTREE COMMENT 'access_token唯一索引',
    UNIQUE INDEX `uk_refresh_token` (`refresh_token` ASC) USING BTREE COMMENT 'refresh_token唯一索引'
) ENGINE = INNODB COMMENT = 'Token表';
