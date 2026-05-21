CREATE TABLE `notes`(
    `id_notes` BIGINT AUTO_INCREMENT,
    `title` TEXT NOT NULL,
    `body` TEXT NOT NULL,
    `id_user` BIGINT NOT NULL,
    `created_at` DATETIME NOT NULL,
    CONSTRAINT PK_NOTES_IDNOTES PRIMARY KEY (`id_notes`),
    CONSTRAINT FK_NOTES_USERACCOUNT FOREIGN KEY (`id_user`) REFERENCES `user_account`(`id_user`)
) ENGINE InnoDB;