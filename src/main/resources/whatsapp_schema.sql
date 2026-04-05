CREATE TABLE IF NOT EXISTS `users` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL UNIQUE,
  `password` VARCHAR(255) NOT NULL,
  `pic` VARCHAR(500) DEFAULT 'https://icon-library.com/images/anonymous-avatar-icon/anonymous-avatar-icon-25.jpg',
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- USERS table already exists

--CREATE TABLE IF NOT EXISTS `chats` (
--    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
--    `chat_name` VARCHAR(255),
--    `is_group_chat` BOOLEAN DEFAULT false,
--    `latest_message_id` BIGINT,
--    `group_admin_id` INT,
--    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--    FOREIGN KEY (latest_message_id) REFERENCES messages(id),
--    FOREIGN KEY (group_admin_id) REFERENCES users(id)
--);

--CREATE TABLE IF NOT EXISTS `messages` (
--    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
--    `sender_id` BIGINT,
--    `content` TEXT,
--    `img_url` VARCHAR(500),
--    `chat_id` BIGINT,
--    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--    FOREIGN KEY (sender_id) REFERENCES users(id),
--    FOREIGN KEY (chat_id) REFERENCES chats(id)
--);


CREATE TABLE IF NOT EXISTS `chats` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `chat_name` VARCHAR(255),
    `is_group_chat` BOOLEAN DEFAULT false,
    `latest_message_id` BIGINT,
    `group_admin_id` INT,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (group_admin_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS `messages` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `sender_id` INT,
    `content` TEXT,
    `img_url` VARCHAR(500),
    `chat_id` BIGINT,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES users(id),
    FOREIGN KEY (chat_id) REFERENCES chats(id)
);

ALTER TABLE `chats`
ADD CONSTRAINT fk_latest_message
FOREIGN KEY (`latest_message_id`) REFERENCES `messages`(`id`);

CREATE TABLE IF NOT EXISTS `chat_users` (
    `chat_id` BIGINT,
    `user_id` INT,
    PRIMARY KEY (`chat_id`, `user_id`),
    FOREIGN KEY (chat_id) REFERENCES chats(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS `notifications` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,

    `curr_message_id` BIGINT,
    `user_id` INT,

    CONSTRAINT fk_notifications_message
        FOREIGN KEY (curr_message_id) REFERENCES messages(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_notifications_user
        FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS `roles` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL,
   PRIMARY KEY (`role_id`)
);

ALTER TABLE users
ADD COLUMN role_id INT NOT NULL,
ADD CONSTRAINT fk_users_roles FOREIGN KEY (role_id) REFERENCES roles(role_id);

INSERT INTO `roles` (`role_name`,`created_at`)
  VALUES ('ADMIN',CURDATE());

INSERT INTO `roles` (`role_name`,`created_at`)
  VALUES ('USER',CURDATE());

INSERT INTO `users` (`name`,`email`,`password`,`created_at`,`role_id`)
  VALUES ('Admin','admin@eazyschool.com','$2a$10$XhU4UcSxDPb5G0I0fT/CZ.Lfj2VW2fkLkUP5cOEM.xM8EzyUQXaD2',CURDATE(),1);


-- 1. Create join table
CREATE TABLE IF NOT EXISTS `user_roles` (
    `user_id` INT NOT NULL,
    `role_id` INT NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(role_id) ON DELETE CASCADE
);

-- 2. Copy old data
INSERT INTO `user_roles` (`user_id`, `role_id`)
SELECT `id`, `role_id` FROM `users` WHERE `role_id` IS NOT NULL;

-- 3. Drop old foreign key and column
ALTER TABLE users DROP FOREIGN KEY fk_users_roles;
ALTER TABLE users DROP COLUMN role_id;

-- First, delete from user_roles table
DELETE FROM user_roles WHERE user_id = 1;

-- Then, delete from users table
DELETE FROM users WHERE id = 1;


