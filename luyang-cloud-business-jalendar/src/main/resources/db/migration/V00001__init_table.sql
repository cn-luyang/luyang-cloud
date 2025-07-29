-- 日历表
CREATE TABLE `calendar`
(
    `id`            BIGINT      NOT NULL COMMENT '主键ID',
    `calendar_id`   VARCHAR(64) NOT NULL COMMENT '日历ID',
    `user_id`       VARCHAR(64) NOT NULL COMMENT '日历拥有者用户ID',
    `default_name`  VARCHAR(16) NOT NULL COMMENT '日历默认名称，创建时的名称',
    `default_color` VARCHAR(16)  DEFAULT '#007bff' COMMENT '日历默认颜色，创建时的颜色',
    `type`          INT          DEFAULT 2 COMMENT '日历类型 {[1:主日历] [2:共享日历] [3:全员日历]}',
    `visibility`    INT          DEFAULT 3 COMMENT '日历公开范围 {[1:私密-不可自行订阅] [2:简览-可订阅，仅忙闲] [3:公开-可订阅，查看日程]}',
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
CREATE TABLE `calendar_subscribe`
(
    `user_id`       VARCHAR(64) NOT NULL COMMENT '日历订阅者用户ID',
    `calendar_id`   VARCHAR(64) NOT NULL COMMENT '日历ID',
    `permissions`   INT         NOT NULL COMMENT '订阅者对于日历的权限: {[1:忙闲] [2:查看详情] [3:编辑] [4:管理]}',
    `display_name`  VARCHAR(16) DEFAULT NULL COMMENT '日历名称(对于当前身份)',
    `display_color` VARCHAR(16) DEFAULT NULL COMMENT '日历颜色(对于当前身份)',
    `displayed`     BOOLEAN     DEFAULT TRUE COMMENT '是否显示日历(对于当前身份): {[1:显示:true] [0:隐藏:false]}',
    `unsubscribe`   BOOLEAN     DEFAULT FALSE COMMENT '是否取消订阅(对于当前身份): {[1:是:true] [0:否:false]}',

    `created_by`    VARCHAR(64) DEFAULT NULL COMMENT '创建人',
    `created_time`  DATETIME ( 3 ) DEFAULT NULL COMMENT '创建时间',
    `updated_by`    VARCHAR(64) DEFAULT NULL COMMENT '更新人',
    `updated_time`  DATETIME ( 3 ) DEFAULT NULL COMMENT '更新时间',
    `deleted`       BOOLEAN     DEFAULT FALSE COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    PRIMARY KEY (`user_id`, `calendar_id`, `deleted`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC COMMENT = '日历订阅表';

-- 日历管理员表
CREATE TABLE `calendar_admin`
(
    `id`           BIGINT      NOT NULL COMMENT '主键ID',
    `user_id`      VARCHAR(64) NOT NULL COMMENT '管理员用户ID',

    `created_by`   VARCHAR(64) DEFAULT NULL COMMENT '创建人',
    `created_time` DATETIME (3) DEFAULT NULL COMMENT '创建时间',
    `updated_by`   VARCHAR(64) DEFAULT NULL COMMENT '更新人',
    `updated_time` DATETIME (3) DEFAULT NULL COMMENT '更新时间',
    `deleted`      BOOLEAN     DEFAULT FALSE COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = dynamic COMMENT = '日历管理员表';


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
