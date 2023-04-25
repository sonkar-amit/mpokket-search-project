CREATE TABLE `search_schema`.`document1` (
  `id` INT NOT NULL auto_increment,
  `title` VARCHAR(45) NOT NULL,
  `author` VARCHAR(45) NOT NULL,
  `content` VARCHAR(45) NOT NULL,
  `created_dt` timestamp not null default current_timestamp,
  PRIMARY KEY (`id`));
  
CREATE INDEX doc_content_idx ON document(content(255));