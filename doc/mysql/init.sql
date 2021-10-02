CREATE DATABASE `hami` CHARACTER SET 'utf8mb4';

CREATE TABLE `hami_order` (
                            `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
                            `order_no` varchar(50) NOT NULL COMMENT '订单号',
                            `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                            `price` decimal(10,2) NOT NULL COMMENT '订单金额',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            PRIMARY KEY (`id`),
                            KEY `uk_order_no` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into `hami_order`(order_no, user_id, price) VALUE('000001', 1, 100), ('000002', 2, 99.88)