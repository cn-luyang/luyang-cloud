create table `t_authorize_log`
(
    `id`          BIGINT       not null COMMENT '主键ID',
    `client_id`   VARCHAR(128) not null COMMENT '客户端ID',
    `user_id`     VARCHAR(64)  not null COMMENT '用户ID',
    `login_token` VARCHAR(64)  not null COMMENT '登录Token',
    `expires_at`  DATETIME (3) default null COMMENT '登录Token过期时间',
    `ip_address`  VARCHAR(128) not null COMMENT '登录IP',
    `user_agent`  VARCHAR(255) default 3600 COMMENT '浏览器 UA 信息',
    `deleted`     BIT(1)       default b '0' COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
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

create table `t_client`
(
    `id`                     BIGINT       not null COMMENT '主键ID',
    `client_id`              VARCHAR(64)  not null COMMENT '客户端ID',
    `login_token`            VARCHAR(64)  not null COMMENT '登录Token',
    `client_secret`          VARCHAR(128) not null COMMENT '失效时间',
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
