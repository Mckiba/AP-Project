CREATE TABLE `Customers` (
  `customer_id` int PRIMARY KEY AUTO_INCREMENT,
  `full_name` varchar(255),
  `email` varchar(255),
  `contact` varchar(255),
  `service_address` varchar(255),
  `complaint_code` int,
  `account_number` varchar(255),
  `password` varchar(255)
);

CREATE TABLE `CustomerRep` (
  `rep_id` int PRIMARY KEY AUTO_INCREMENT,
  `full_name` varchar(255),
  `email` varchar(255),
  `complaint_code` int,
  `password` varchar(255)
);

CREATE TABLE `Technician` (
  `staff_id` int PRIMARY KEY AUTO_INCREMENT,
  `full_name` varchar(255),
  `email` varchar(255),
  `complaint_code` int,
  `password` varchar(255)
);

CREATE TABLE `complaints` (
  `id` int PRIMARY KEY,
  `category` varchar(255),
  `response` varchar(255),
  `response_provider` varchar(255),
  `response_date` datetime,
  `issue_type` varchar(255),
  `issue_details` varchar(255)
);

CREATE TABLE `accounts` (
  `account_id` int PRIMARY KEY,
  `payment_status` varchar(255),
  `amount_due` float8,
  `payment_due_date` datetime,
  `status` varchar(255),
  `created_at` datetime DEFAULT (now())
);

ALTER TABLE `Customers` ADD FOREIGN KEY (`complaint_code`) REFERENCES `complaints` (`id`);

ALTER TABLE `accounts` ADD FOREIGN KEY (`account_id`) REFERENCES `Customers` (`account_number`);

ALTER TABLE `complaints` ADD FOREIGN KEY (`response_provider`) REFERENCES `Technician` (`staff_id`);
