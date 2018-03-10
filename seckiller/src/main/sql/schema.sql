---创建数据库
CREATE DATABASE seckiller;

--使用数据库
use seckiller;

--创建秒杀库存表
CREATE TABLE seckill(
`seckiller_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
`name` VARCHAR(120) NOT NULL COMMENT '商品名称',
`number` INTEGER NOT NULL COMMENT '库存数量',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀创建时间',
`start_time` TIMESTAMP NOT NULL COMMENT '秒杀开始时间',
`end_time` TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
PRIMARY KEY (seckiller_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT '秒杀库存表';

--初始化数据
insert INTO
  seckill(name,number,start_time,end_time)
VALUES
  ('1000元秒杀iphone6',100,'2018-03-10 00:00:00','2018-03-11 00:00:00'),
  ('500元秒杀ipad2',300,'2018-03-10 00:00:00','2018-03-11 00:00:00'),
  ('300元秒杀小米6',400,'2018-03-10 00:00:00','2018-03-11 00:00:00'),
  ('200元秒杀红米note3',200,'2018-03-10 00:00:00','2018-03-11 00:00:00');

--秒杀成功明细表

create table success_killed(
`seckiller_id` bigint NOT NULL COMMENT '商品ID',
`user_phone` bigint NOT NULL COMMENT '用户手机号',
`state` tinyint NOT NULL DEFAULT -1 COMMENT '状态表示：-1 无效，0 成功，1 已付款，2 已发货',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (seckiller_id,user_phone),/*联合主键*/
key idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '秒杀成功明细';

