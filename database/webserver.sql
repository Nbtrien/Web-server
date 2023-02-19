-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th2 19, 2023 lúc 10:47 AM
-- Phiên bản máy phục vụ: 10.4.17-MariaDB
-- Phiên bản PHP: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `webserver`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `directories`
--

CREATE TABLE `directories` (
  `id` int(11) NOT NULL,
  `directory` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `directories`
--

INSERT INTO `directories` (`id`, `directory`) VALUES
(20, 'f:/Web Server/trien@gmail.com'),
(31, 'f:/Web Server/trien@gmail.com/acmovie'),
(33, 'f:/Web Server/trien@gmail.com/thekids'),
(34, 'f:/Web Server/trien@gmail.com/videoblog'),
(35, 'f:/Web Server/abc@gmail.com'),
(36, 'f:/Web Server/trien@gmail.com/yoga'),
(37, 'f:/Web Server/tr1234@gmail.com'),
(38, 'f:/Web Server/tr1234@gmail.com/zulu'),
(39, 'f:/Web Server/tr1234@gmail.com/savory'),
(40, 'f:/Web Server/tr1234@gmail.com/bakery'),
(41, 'f:/Web Server/trien@gmail.com/univers');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` int(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `directory_id` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`, `directory_id`) VALUES
(37, 'trien', 'trien@gmail.com', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 20),
(38, 'abc', 'abc@gmail.com', '7d824ad37e366f330ef3d3bafb8dc8b18a5b07622e2830eac5966339d98a94b0', 35),
(39, 'tr', 'tr1234@gmail.com', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 37);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `websites`
--

CREATE TABLE `websites` (
  `id` int(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `domain` varchar(255) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  `user_id` int(20) NOT NULL,
  `directory_id` int(20) DEFAULT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `websites`
--

INSERT INTO `websites` (`id`, `name`, `domain`, `url`, `user_id`, `directory_id`, `password`) VALUES
(35, 'acmovie', NULL, 'http://localhost:8080/acmovie', 37, 31, '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4'),
(37, 'thekids', NULL, 'http://localhost:8080/thekids', 37, 33, '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4'),
(38, 'videoblog', NULL, 'http://localhost:8080/videoblog', 37, 34, '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4'),
(39, 'yoga', NULL, 'http://localhost:8080/yoga', 37, 36, '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4'),
(40, 'zulu', NULL, 'http://localhost:8080/zulu', 39, 38, '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4'),
(41, 'savory', NULL, 'http://localhost:8080/savory', 39, 39, '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4'),
(42, 'bakery', NULL, 'http://localhost:8080/bakery', 39, 40, '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4'),
(43, 'univers', NULL, 'http://localhost:8080/univers', 37, 41, '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `directories`
--
ALTER TABLE `directories`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `users_directory_id_foreign` (`directory_id`);

--
-- Chỉ mục cho bảng `websites`
--
ALTER TABLE `websites`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`,`domain`),
  ADD KEY `website_user_id_foreign` (`user_id`),
  ADD KEY `website_directory_id_foreign` (`directory_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `directories`
--
ALTER TABLE `directories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT cho bảng `websites`
--
ALTER TABLE `websites`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_directory_id_foreign` FOREIGN KEY (`directory_id`) REFERENCES `directories` (`id`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `websites`
--
ALTER TABLE `websites`
  ADD CONSTRAINT `website_directory_id_foreign` FOREIGN KEY (`directory_id`) REFERENCES `directories` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `website_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
