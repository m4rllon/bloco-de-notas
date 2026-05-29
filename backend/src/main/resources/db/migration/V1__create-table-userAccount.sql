CREATE TABLE IF NOT EXISTS `user_account`(
    `id_user` BIGINT AUTO_INCREMENT,
    `username` MEDIUMTEXT NOT NULL,
    `email` MEDIUMTEXT NOT NULL,
    `password` MEDIUMTEXT NOT NULL,
    `role` TINYINT NOT NULL,
    `created_at` DATETIME NOT NULL,
    CONSTRAINT PK_USERS_IDUSER PRIMARY KEY (`id_user`, `username`(255), `email`(255))
) ENGINE InnoDB;