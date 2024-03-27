#SET SQL_SAFE_UPDATES = 0;
#delete from spms.book_category;
#delete from spms.book;
#delete from spms.category;
#delete  from spms.author;
#SET SQL_SAFE_UPDATES = 1;
INSERT INTO author (BIOGRAPHY, NAME) VALUES (CONCAT('Biography', FLOOR(RAND() * 1000) + 1), CONCAT('Name', FLOOR(RAND() * 1000) + 1));
INSERT INTO category (NAME) VALUES (CONCAT('category', FLOOR(RAND() * 1000) + 1)); 