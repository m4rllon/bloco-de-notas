CREATE TABLE IF NOT EXISTS `notes`(
    `id_notes` BIGINT AUTO_INCREMENT,
    `title` TEXT NOT NULL,
    `body` TEXT NOT NULL,
    `username` VARCHAR(255) NOT NULL,
    `created_at` DATETIME NOT NULL,
    CONSTRAINT PK_NOTES_IDNOTES PRIMARY KEY (`id_notes`, `username`),
    CONSTRAINT FK_NOTES_USERACCOUNT FOREIGN KEY (`username`) REFERENCES `user_account`(`username`)
) ENGINE InnoDB;