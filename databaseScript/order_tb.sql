CREATE TABLE `order_tb` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `trans_id` int,
  `tracking_number` varchar(20),
  `summary_amount` decimal(10,2),
  `create_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
