create table `calendar`
(
    `calendar_id`   varchar(64) not null comment '日历ID',
    `owner_user_id` varchar(64) not null comment '日历所有者用户ID',
    `default_name`  varchar(16) not null comment '日历默认名称，创建时的名称',
    `default_color` varchar(8)   default '#007bff' comment '日历默认颜色，创建时的颜色',
    `type`          varchar(16)  default 'SHARED' comment '日历类型 [MAIN:主日历] [SHARED:共享日历] [GLOBAL:全员日历]',
    `visibility`    varchar(16)  default 'PUBLIC' comment '日历公开范围 [PRIVATE:私密-不可自行订阅] [GUEST:简览-可订阅，仅忙闲] [PUBLIC:公开-可订阅，查看日程]',
    `description`   varchar(256) default null comment '日历描述',
    `created_by`    varchar(64)  default null comment '创建人',
    `created_time`  datetime (3) default null comment '创建时间',
    `updated_by`    varchar(64)  default null comment '更新人',
    `updated_time`  datetime (3) default null comment '更新时间',
    `deleted`       boolean      default false comment '是否删除 [1:删除:true] [0:未删除:false]',
    primary key (`calendar_id`)
) engine = innodb  comment = '日历表';

create table `calendar_subscribe`
(
    `subscribe_id`  varchar(64) not null comment '主键ID',
    `user_id`       varchar(64) not null comment '日历订阅者用户ID',
    `calendar_id`   varchar(64) not null comment '日历ID',
    `permissions`   varchar(16) not null comment '订阅者对于日历的权限 [BUSY_FREE:忙闲] [VIEW_DETAILS:查看详情] [EDIT:编辑] [ADMIN:管理]',
    `display_name`  varchar(16) default null comment '日历名称(对于当前身份)',
    `display_color` varchar(16) default null comment '日历颜色(对于当前身份)',
    `selected`      boolean     default false comment '是否勾选日历(对于当前身份) [1:显示:true] [0:隐藏:false]',
    `unsubscribe`   boolean     default false comment '是否取消订阅(对于当前身份) [1:是:true] [0:否:false]',
    `created_by`    varchar(64) default null comment '创建人',
    `created_time`  datetime ( 3 ) default null comment '创建时间',
    `updated_by`    varchar(64) default null comment '更新人',
    `updated_time`  datetime ( 3 ) default null comment '更新时间',
    `deleted`       boolean     default false comment '是否删除 [1:删除:true] [0:未删除:false]',
    primary key (`subscribe_id`)
) engine = innodb comment = '日历订阅表';

create table `calendar_admin`
(
    `id`           bigint      not null comment '主键id',
    `user_id`      varchar(64) not null comment '管理员用户id',
    `created_by`   varchar(64) default null comment '创建人',
    `created_time` datetime (3) default null comment '创建时间',
    `updated_by`   varchar(64) default null comment '更新人',
    `updated_time` datetime (3) default null comment '更新时间',
    `deleted`      boolean     default false comment '是否删除: {[1:删除:true] [0:未删除:false]}',
    primary key (`id`) using btree
) ENGINE = INNODB COMMENT = '日历管理员表';

create table `schedule`
(
    `id`           bigint       not null comment '主键id',
    `schedule_id`  varchar(64)  not null comment '日程id',
    `title`        varchar(64)  not null comment '日程标题',
    `all_day`      bit(1)      default b '0' comment '全天日程: {[1:是:true] [0:否:false]}',
    `start_time`   datetime ( 3 ) default null comment '开始时间',
    `end_time`     datetime ( 3 ) default null comment '结束时间',
    `description`  varchar(256) not null comment '日程描述',
    `created_by`   varchar(64) default null comment '创建人',
    `created_time` datetime ( 3 ) default null comment '创建时间',
    `updated_by`   varchar(64) default null comment '更新人',
    `updated_time` datetime ( 3 ) default null comment '更新时间',
    `deleted`      bit(1)      default b '0' comment '是否删除: {[1:删除:true] [0:未删除:false]}',
    primary key (`id`) using btree,
    unique index `uniq_client_id` ( `client_id` asc ) using btree comment '客户端id唯一索引'
) ENGINE = INNODB COMMENT = '日程表';

create table `recurrence`
(
    `id`            bigint       not null comment '主键id',
    `recurrence_id` varchar(64)  not null comment '规则id',
    `rule`          varchar(256) not null comment '规则',
    `all_day`       bit(1)      default b '0' comment '全天日程: {[1:是:true] [0:否:false]}',
    `start_time`    datetime ( 3 ) default null comment '开始时间',
    `end_time`      datetime ( 3 ) default null comment '结束时间',
    `description`   varchar(256) not null comment '日程描述',
    `created_by`    varchar(64) default null comment '创建人',
    `created_time`  datetime ( 3 ) default null comment '创建时间',
    `updated_by`    varchar(64) default null comment '更新人',
    `updated_time`  datetime ( 3 ) default null comment '更新时间',
    `deleted`       bit(1)      default b '0' comment '是否删除: {[1:删除:true] [0:未删除:false]}',
    primary key (`id`) using btree,
    unique index `uniq_client_id` ( `client_id` asc ) using btree comment '客户端id唯一索引'
) ENGINE = INNODB COMMENT = '重复规则表';
