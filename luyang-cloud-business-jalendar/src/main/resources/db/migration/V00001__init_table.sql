-- 日历表
CREATE TABLE `calendar`
(
    `id`            BIGINT      NOT NULL COMMENT '主键ID',
    `calendar_id`   VARCHAR(64) NOT NULL COMMENT '日历ID',
    `user_id`       VARCHAR(64) NOT NULL COMMENT '用户ID',
    `calendar_name` VARCHAR(64) NOT NULL COMMENT '日历名称',
    `default_color` VARCHAR(16)  DEFAULT '#007bff' COMMENT '默认显示颜色 (HEX格式)',
    `calendar_type` INT          DEFAULT 2 COMMENT '日历类型 {[1:主日历] [2:共享日历]}',
    `description`   VARCHAR(256) DEFAULT NULL COMMENT '日历描述',

    `created_by`    VARCHAR(64)  DEFAULT NULL COMMENT '创建人',
    `created_time`  DATETIME (3) DEFAULT NULL COMMENT '创建时间',
    `updated_by`    VARCHAR(64)  DEFAULT NULL COMMENT '更新人',
    `updated_time`  DATETIME (3) DEFAULT NULL COMMENT '更新时间',
    `deleted`       BOOLEAN      DEFAULT FALSE COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uniq_calendar_id` ( `calendar_id` ASC ) USING BTREE COMMENT '日历ID唯一索引'
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = dynamic COMMENT = '日历表';

-- 日历订阅表
CREATE TABLE `calendar_subscriptions`
(
    `user_id`       INT NOT NULL COMMENT '用户ID',
    `calendar_id`   INT NOT NULL COMMENT '日历ID',
    `display_name`  VARCHAR(16) DEFAULT NULL COMMENT '日历显示名称(仅对自己生效)',
    `display_color` VARCHAR(16) DEFAULT NULL COMMENT '日历显示颜色(仅对自己生效)',
    `is_visible`    BOOLEAN     DEFAULT TRUE COMMENT '是否显示日历(仅对自己生效): {[1:显示:true] [0:隐藏:false]}',

    `created_by`    VARCHAR(64) DEFAULT NULL COMMENT '创建人',
    `created_time`  DATETIME ( 3 ) DEFAULT NULL COMMENT '创建时间',
    `updated_by`    VARCHAR(64) DEFAULT NULL COMMENT '更新人',
    `updated_time`  DATETIME ( 3 ) DEFAULT NULL COMMENT '更新时间',
    `deleted`       BOOLEAN     DEFAULT FALSE COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    PRIMARY KEY (`user_id`, `calendar_id`, `deleted`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC COMMENT = '日历订阅表';

-- 日历访问控制表
CREATE TABLE `calendar_acl`
(
    `id`           BIGINT      NOT NULL COMMENT '主键ID',
    `user_id`      VARCHAR(64) NOT NULL COMMENT '用户ID',
    `calendar_id`  VARCHAR(64) NOT NULL COMMENT '日历ID',
    `display_name` VARCHAR(64) DEFAULT NULL COMMENT '订阅者自定义的日历显示名称',
    `color`        VARCHAR(16) DEFAULT '#3a87ad' COMMENT '订阅者自定义显示颜色',
    `created_by`   VARCHAR(64) DEFAULT NULL COMMENT '创建人',
    `created_time` DATETIME ( 3 ) DEFAULT NULL COMMENT '创建时间',
    `updated_by`   VARCHAR(64) DEFAULT NULL COMMENT '更新人',
    `updated_time` DATETIME ( 3 ) DEFAULT NULL COMMENT '更新时间',
    `deleted`      BIT(1)      DEFAULT b '0' COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uniq_user_calendar` ( `user_id`, `calendar_id` ) USING BTREE COMMENT '同一用户不能重复订阅同一日历', ,
    INDEX          `idx_user_id` ( `user_id` ) USING BTREE COMMENT '用户ID普通索引',
    INDEX          `idx_calendar_id` ( `calendar_id` ) USING BTREE COMMENT '日历ID普通索引'
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC COMMENT = '日历访问控制表';

CREATE TABLE `schedule`
(
    `id`           BIGINT       NOT NULL COMMENT '主键ID',
    `schedule_id`  VARCHAR(64)  NOT NULL COMMENT '日程ID',
    `title`        VARCHAR(64)  NOT NULL COMMENT '日程标题',
    `all_day`      BIT(1)      DEFAULT b '0' COMMENT '全天日程: {[1:是:true] [0:否:false]}',
    `start_time`   DATETIME ( 3 ) DEFAULT NULL COMMENT '开始时间',
    `end_time`     DATETIME ( 3 ) DEFAULT NULL COMMENT '结束时间',
    `description`  VARCHAR(256) NOT NULL COMMENT '日程描述',
    `created_by`   VARCHAR(64) DEFAULT NULL COMMENT '创建人',
    `created_time` DATETIME ( 3 ) DEFAULT NULL COMMENT '创建时间',
    `updated_by`   VARCHAR(64) DEFAULT NULL COMMENT '更新人',
    `updated_time` DATETIME ( 3 ) DEFAULT NULL COMMENT '更新时间',
    `deleted`      BIT(1)      DEFAULT b '0' COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uniq_client_id` ( `client_id` ASC ) USING BTREE COMMENT '客户端ID唯一索引'
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC COMMENT = '日程表';

CREATE TABLE `recurrence`
(
    `id`            BIGINT       NOT NULL COMMENT '主键ID',
    `recurrence_id` VARCHAR(64)  NOT NULL COMMENT '规则ID',
    `rule`          VARCHAR(256) NOT NULL COMMENT '规则',
    `all_day`       BIT(1)      DEFAULT b '0' COMMENT '全天日程: {[1:是:true] [0:否:false]}',
    `start_time`    DATETIME ( 3 ) DEFAULT NULL COMMENT '开始时间',
    `end_time`      DATETIME ( 3 ) DEFAULT NULL COMMENT '结束时间',
    `description`   VARCHAR(256) NOT NULL COMMENT '日程描述',
    `created_by`    VARCHAR(64) DEFAULT NULL COMMENT '创建人',
    `created_time`  DATETIME ( 3 ) DEFAULT NULL COMMENT '创建时间',
    `updated_by`    VARCHAR(64) DEFAULT NULL COMMENT '更新人',
    `updated_time`  DATETIME ( 3 ) DEFAULT NULL COMMENT '更新时间',
    `deleted`       BIT(1)      DEFAULT b '0' COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uniq_client_id` ( `client_id` ASC ) USING BTREE COMMENT '客户端ID唯一索引'
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC COMMENT = '重复规则表';
