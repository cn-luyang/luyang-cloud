create table `t_auth_request`
(
    `id`                    BIGINT not null COMMENT '主键ID',
    `client_id`             VARCHAR(128) default null COMMENT '客户端ID',
    `response_type`         VARCHAR(64)  default null COMMENT '授权类型',
    `redirect_uri`          VARCHAR(512) default null COMMENT '重定向URI',
    `scope`                 VARCHAR(512  default null COMMENT '权限范围',
    `state`                 VARCHAR(64)  default null COMMENT '防止CSRF攻击的状态参数',
    `code_challenge`        VARCHAR(255) default null COMMENT 'PKCE 码',
    `code_challenge_method` VARCHAR(16)  default null COMMENT 'PKCE 码，加密方式',
    `expires_time`          DATETIME (3) default null COMMENT '过期时间',
    primary key (`id`) using BTREE
) ENGINE = INNODB default CHARSET = utf8mb4 collate = utf8mb4_general_ci ROW_FORMAT = dynamic COMMENT = '认证请求表';

create table `t_login_token`
(
    `id`                   BIGINT       not null COMMENT '主键ID',
    `authorize_request_id` VARCHAR(128) not null COMMENT '授权请求ID t_authorize_request.authorize_request_id',
    `user_id`              VARCHAR(64)  not null COMMENT '用户ID',
    `login_token`          VARCHAR(64)  not null COMMENT '登录Token',
    `expires_at`           DATETIME (3) default null COMMENT '登录Token过期时间',
    `ip_address`           VARCHAR(128) not null COMMENT '登录IP',
    `user_agent`           VARCHAR(255) default 3600 COMMENT '浏览器 UA 信息',
    `deleted`              BIT(1)       default b '0' COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    primary key (`id`) using BTREE,
    unique index `uniq_client_id` (`client_id` asc) using BTREE COMMENT '客户端ID唯一索引'
) ENGINE = INNODB default CHARSET = utf8mb4 collate = utf8mb4_general_ci ROW_FORMAT = dynamic COMMENT = '登录Token表';

create table `t_client`
(
    `id`                     BIGINT       not null COMMENT '主键ID',
    `client_id`              VARCHAR(64)  not null COMMENT '客户端ID',
    `client_name`            VARCHAR(64)  not null COMMENT '应用名',
    `client_secret`          VARCHAR(128) not null COMMENT '客户端密钥',
    `client_secret_plain`    VARCHAR(128) not null COMMENT '客户端密钥明文(仅初始化时使用)',
    `access_token_validity`  INT           default 3600 COMMENT '访问令牌有效期(秒)',
    `refresh_token_validity` INT           default 86400 COMMENT '刷新令牌有效期(秒)',
    `grant_types`            VARCHAR(256) not null COMMENT '支持的授权类型(authorization_code,password,client_credentials,refresh_token,sms)',
    `redirect_uri`           VARCHAR(2048) default null COMMENT '重定向URI,多个URI用逗号分隔',
    `auto_approve`           BIT(1)        default b '0' COMMENT '是否自动批准(跳过授权页面) {[1:是:true] [0:否:false]}',
    `description`            VARCHAR(255)  default null COMMENT '应用描述',
    `created_by`             VARCHAR(64)   default null COMMENT '创建人',
    `created_time`           DATETIME (3) default null COMMENT '创建时间',
    `updated_by`             VARCHAR(64)   default null COMMENT '更新人',
    `updated_time`           DATETIME (3) default null COMMENT '更新时间',
    `deleted`                BIT(1)        default b '0' COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    primary key (`id`) using BTREE,
    unique index `uniq_client_id` (`client_id` asc) using BTREE COMMENT '客户端ID唯一索引'
) ENGINE = INNODB default CHARSET = utf8mb4 collate = utf8mb4_general_ci ROW_FORMAT = dynamic COMMENT = 'OAuth2 客户端表';
