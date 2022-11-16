-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 09, 2021 at 02:23 PM
-- Server version: 5.7.24
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `stock`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `code` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lable` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `code`, `lable`) VALUES
(4, '50', 'Moteur'),
(5, 'echa', 'Échappement'),
(6, 'tR', 'Train roulant'),
(7, 'Ecl', 'Éclairage'),
(8, 'clim', 'Climatisation'),
(9, 'Frei', 'Freinage'),
(10, '80', 'auto');

-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

CREATE TABLE `clients` (
  `id` int(11) NOT NULL,
  `firstname` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lastname` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tel` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `clients`
--

INSERT INTO `clients` (`id`, `firstname`, `lastname`, `tel`, `email`) VALUES
(2, 'ayoubttes', 'margoum', '0613656554', 'gthfgfg'),
(3, 'ayoub', 'margoum', '0613656554', 'gthfgfg'),
(4, 'adfdf', 'regre', '06136556554', 'dfggreg'),
(5, 'adf0df', 'regrtre', '061365t56554', 'dfggrtreg'),
(7, 'adf0df', 'regrtre', '061365t56554', 'dfggrtreg'),
(10, 'adf0df', 'regrtre', '061365t56554', 'dfggrtreg'),
(12, 'adf0df', 'regrtre', '061365t56554', 'dfggrtreg'),
(16, 'adf0df', 'regrtre', '061365t56554', 'dfggrtreg'),
(17, 'adf0df', 'regrtre', '061365t56554', 'dfggrtreg'),
(18, 'cli1', 'cl2', '0601087607', 'fff');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `client_id` int(11) DEFAULT NULL,
  `ref` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `tax` decimal(10,0) NOT NULL,
  `TTC_price` decimal(10,0) NOT NULL,
  `status` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `application_date` date NOT NULL,
  `delivery_date` date NOT NULL,
  `delivery_vendor` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `client_id`, `ref`, `price`, `tax`, `TTC_price`, `status`, `application_date`, `delivery_date`, `delivery_vendor`, `user_id`) VALUES
(1, 3, '02541654', '6970', '10', '6980', 'delivred', '2019-08-07', '2020-09-09', 'fedx', 2),
(2, 4, '025f', '9210', '10', '9220', 'working', '2021-06-22', '2021-06-22', 'fedx', 3),
(3, 5, '025', '27450', '10', '27460', 'working', '2019-06-22', '2021-02-22', 'fedx', 4),
(4, 7, '0254r', '136275', '10', '136285', 'working', '2021-06-22', '2021-06-22', 'fedx', 6),
(5, 10, '919r', '25770', '10', '25780', 'working', '2021-06-22', '2021-06-17', 'fedx', 9),
(6, 12, '651f', '136275', '10', '136285', 'working', '2021-06-22', '2021-06-22', 'fedx', 11),
(7, 3, '165g', '136275', '10', '136285', 'working', '2020-06-22', '2021-06-22', 'fedx', 15),
(8, 17, '496h', '136275', '10', '136285', 'working', '2020-06-22', '2021-06-22', 'fedx', 16),
(9, 17, '496h', '136275', '10', '136285', 'working', '2020-06-22', '2021-06-22', 'fedx', 16),
(10, 7, '0254r', '136275', '10', '136285', 'working', '2021-06-22', '2021-06-22', 'fedx', 6),
(11, 4, '025f', '118980', '10', '118990', 'working', '2021-06-22', '2021-06-22', 'fedx', 3);

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `id` int(11) NOT NULL,
  `ref` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `payment_date` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `trade_id` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `ref` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `categorie_id` int(11) DEFAULT NULL,
  `price` decimal(10,0) NOT NULL,
  `quantity` int(11) NOT NULL,
  `lable` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `ref`, `categorie_id`, `price`, `quantity`, `lable`) VALUES
(1, 'moteur', 4, '350', 50, 'Embrayage'),
(2, 'moteur', 4, '220', 45, 'turbo'),
(3, 'echap', 5, '1350', 119, 'silencieux'),
(4, 'tR', 4, '270', 64, 'Rotules'),
(5, 'ecl', 7, '30', 55, 'ampoule'),
(6, 'clim', 8, '987', 79, 'Compresseur'),
(7, 'frei', 9, '570', 67, 'Tambour'),
(8, 'frei', 9, '460', 83, 'disque'),
(9, '00021', 5, '0', 5, 'aaaaa'),
(10, '00001', 5, '0', 5, 'car');

-- --------------------------------------------------------

--
-- Table structure for table `product_order`
--

CREATE TABLE `product_order` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `product_order`
--

INSERT INTO `product_order` (`id`, `product_id`, `order_id`, `quantity`) VALUES
(394, 4, 3, 2),
(395, 7, 3, 1),
(397, 7, 5, 1),
(398, 3, 5, 1),
(405, 1, 2, 1),
(406, 4, 2, 7),
(407, 1, 1, 13),
(408, 2, 1, 11);

--
-- Triggers `product_order`
--
DELIMITER $$
CREATE TRIGGER `product_update_quantity_delete` AFTER DELETE ON `product_order` FOR EACH ROW BEGIN
	UPDATE products set quantity = quantity +  OLD.quantity 
    WHERE id = OLD.product_id;
    
    UPDATE orders set price = 0
    WHERE id = OLD.order_id;
    
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `product_update_quantity_insert` AFTER INSERT ON `product_order` FOR EACH ROW BEGIN
	UPDATE products set quantity = quantity -  NEW.quantity 
    WHERE id = NEW.product_id;
    
        SELECT price into @prodyctprice from products WHERE id = NEW.product_id;
        UPDATE orders set price =( price + (@prodyctprice * NEW.quantity)) ;
        
         UPDATE orders set ttc_price =( price + tax);
    
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `id` int(11) NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tel` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `adresse` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`id`, `email`, `tel`, `adresse`, `name`, `type`) VALUES
(1, 'dacia@dacia.dacia', '321325', 'casablanca rue masira n236 b2 ', 'dacia', 'entreprise'),
(2, 'melhaoui@melh', '516545654', 'oujda rue bruxele', 'melhaoui', 'individual');

-- --------------------------------------------------------

--
-- Table structure for table `trades`
--

CREATE TABLE `trades` (
  `id` int(11) NOT NULL,
  `ref` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `supplier_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `unit_price` decimal(10,0) NOT NULL,
  `quantity` int(11) NOT NULL,
  `TTC_price` decimal(10,0) NOT NULL,
  `application_date` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `delivery_date` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `delivery_vendor` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `firstname` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lastname` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `firstname`, `lastname`) VALUES
(1, 'med', '123456', 'ayoub', 'margoum'),
(2, 'lion007', 'lion', 'lion', 'lion'),
(3, 'lion0007', 'lion1', 'lion1', 'lion1'),
(4, 'lion0t0007', 'lion155', 'lion121', 'lion13f'),
(6, 'lion0t000yr7', 'lion155', 'lion121', 'lion13f'),
(9, 'lion0t000yrr7', 'lion155', 'lion121', 'lion13f'),
(11, 'lion0t000yrre7', 'lion155', 'lion121', 'lion13f'),
(15, 'lion0t000yrrjjjje7', 'lion155', 'lion121', 'lion13f'),
(16, 'lion0t000yrrjgggjjje7', 'lion155', 'lion121', 'lion13f');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Indexes for table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Orders_fk0` (`client_id`),
  ADD KEY `Orders_fk1` (`user_id`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Payments_fk0` (`trade_id`),
  ADD KEY `Payments_fk1` (`order_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Products_fk0` (`categorie_id`);

--
-- Indexes for table `product_order`
--
ALTER TABLE `product_order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_order_fk0` (`product_id`),
  ADD KEY `product_order_fk1` (`order_id`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `trades`
--
ALTER TABLE `trades`
  ADD PRIMARY KEY (`id`),
  ADD KEY `commend_supplier_fk0` (`supplier_id`),
  ADD KEY `commend_supplier_fk1` (`product_id`),
  ADD KEY `commend_supplier_fk2` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `clients`
--
ALTER TABLE `clients`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `product_order`
--
ALTER TABLE `product_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=409;

--
-- AUTO_INCREMENT for table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `trades`
--
ALTER TABLE `trades`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `Orders_fk0` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`),
  ADD CONSTRAINT `Orders_fk1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `Payments_fk0` FOREIGN KEY (`trade_id`) REFERENCES `trades` (`id`),
  ADD CONSTRAINT `Payments_fk1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `Products_fk0` FOREIGN KEY (`categorie_id`) REFERENCES `categories` (`id`);

--
-- Constraints for table `product_order`
--
ALTER TABLE `product_order`
  ADD CONSTRAINT `product_order_fk0` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `product_order_fk1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Constraints for table `trades`
--
ALTER TABLE `trades`
  ADD CONSTRAINT `commend_supplier_fk0` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`),
  ADD CONSTRAINT `commend_supplier_fk1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `commend_supplier_fk2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
