USE `library`;
DROP procedure IF EXISTS `search_book`;
DELIMITER $$ CREATE DEFINER = `root` @`localhost` PROCEDURE `search_book`(title String) BEGIN
SELECT DISTINCT s.bookID,
    s.title,
    s.authorID,
    s.language,
    s.publisherID,
    s.publishDate,
    s.genreID
from book b
where b.title = title;
END $$ DELIMITER;