create database order_db;

use order_db;

CREATE TABLE `orders` (
                          `id` varchar(36) NOT NULL,
                          `user_id` varchar(36) DEFAULT NULL,
                          `status` varchar(255) DEFAULT NULL,
                          `total_amount` decimal(19,2) DEFAULT NULL,
                          `deleted` tinyint(1) DEFAULT NULL,
                          `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
                          `created_by` varchar(255) DEFAULT NULL,
                          `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          `updated_by` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`id`)
);

CREATE TABLE `order_items` (
                               `id` varchar(36) NOT NULL,
                               `order_id` varchar(36) DEFAULT NULL,
                               `product_id` varchar(36) DEFAULT NULL,
                               `price` decimal(19,2) DEFAULT NULL,
                               `quantity` int DEFAULT NULL,
                               `deleted` tinyint(1) DEFAULT NULL,
                               `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
                               `created_by` varchar(255) DEFAULT NULL,
                               `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               `updated_by` varchar(255) DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               KEY `order_id` (`order_id`),
                               CONSTRAINT `order_items_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
);