# 此表是数据库的地基,只是一个可能的版本,如果写着写着发现数据库设计不合理,和组长联系修改

CREATE DATABASE IF NOT EXISTS novelnook;
USE novelnook;

DROP TABLE IF EXISTS borrow;
DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS staff;
DROP TABLE IF EXISTS patron;
DROP TABLE IF EXISTS admin;
DROP TABLE IF EXISTS superuser;

#第一次release后变动

#要求只输一个book id来还书，只在借的时候生成borrow id，还书也输入borrow id；book表的remain依旧是实际剩余数，id依旧是一种书一个。
# 最终book表的更改就是新增了location；由于book id依然存在，借书的时候展示的是book name，实际数据库还是跟book id关联

#关于用户，要求是id、password自动生成后再给用户展示
#登录就用user id + passowrd，选择哪个身份就查哪个表


#具体更改
#book表 bookid自增 添加location字段;
#patron表 userid自增 删掉username
#staff表 username改成userid，自增
#删除user表
#新建 admin表
#新建 superuser表
#brorrw、reservation表参照的都是patron的userid



CREATE  TABLE IF NOT EXISTS staff(
  userid int PRIMARY KEY AUTO_INCREMENT,
  password varchar(255),
  firstname varchar(255),
  lastname varchar(255),
  email varchar(255),
  telephone varchar(255),
  avatar varchar(255)
);


CREATE  TABLE IF NOT EXISTS patron(
    userid int PRIMARY KEY AUTO_INCREMENT,
    password VARCHAR(255),
    firstname varchar(255),
    lastname varchar(255),
    email VARCHAR(255),
    telephone varchar(255),
    avatar varchar(255)
);


CREATE  TABLE IF NOT EXISTS superuser(
    userid int PRIMARY KEY AUTO_INCREMENT,
    password VARCHAR(255),
    firstname varchar(255),
    lastname varchar(255),
    email VARCHAR(255),
    telephone varchar(255),
    avatar varchar(255)
);


CREATE  TABLE IF NOT EXISTS admin(
    userid int PRIMARY KEY AUTO_INCREMENT,
    password VARCHAR(255),
    firstname varchar(255),
    lastname varchar(255),
    email VARCHAR(255),
    telephone varchar(255),
    avatar varchar(255)
);


CREATE TABLE IF NOT EXISTS book(
                                   bookid int PRIMARY KEY AUTO_INCREMENT,
                                   bookname VARCHAR(255),
                                   press VARCHAR(255),
                                   author VARCHAR(255),
                                   publishtime DATE,
                                   catagory VARCHAR(255),
                                   remain int,
                                   introduction TEXT,
                                   location varchar(255) #格式 房间号-书架号-第几层-编号（2位）
);
CREATE TABLE IF NOT EXISTS borrow(
                                     borrowid VARCHAR(255),
                                     userid VARCHAR(255),
                                     bookid VARCHAR(255),
                                     borrowtime DATE,
                                     deadline DATE,
                                     status ENUM('returned','borrowing'),
                                     FOREIGN KEY(userid) REFERENCES patron(userid) ON UPDATE CASCADE ON DELETE CASCADE ,
                                     FOREIGN KEY(bookid) REFERENCES book(bookid) ON UPDATE CASCADE ON DELETE CASCADE ,
                                     PRIMARY KEY (borrowid)
);

CREATE TABLE IF NOT EXISTS reservation(
                                          reservationid VARCHAR(255),
                                          userid VARCHAR(255),
                                          bookid VARCHAR(255),
                                          reservationtime DATE,
                                          status ENUM('canceled','waiting','satisfied'),
                                          FOREIGN KEY(userid) REFERENCES patron(userid) ON UPDATE CASCADE ON DELETE CASCADE ,
                                          FOREIGN KEY(bookid) REFERENCES book(bookid) ON UPDATE CASCADE ON DELETE CASCADE ,
                                          PRIMARY KEY (reservationid)
);

# CREATE TABLE IF NOT EXISTS user(
#                                    userid VARCHAR(255) PRIMARY KEY,
#                                    username VARCHAR(255),
#                                    password VARCHAR(255),
#                                    avatarlocation VARCHAR(255),
#                                    userrole ENUM('admin','patron','staff','super user'),
#                                    email VARCHAR(255),
#                                    PHONE VARCHAR(20)
# );


#下面是一些测试用的数据,由GPT生成,感谢GPT的劳动^~^
# From GPT

INSERT INTO book(bookname, press, author, publishtime, catagory, remain, introduction, location) VALUES
('The Great Gatsby', 'Scribner', 'F. Scott Fitzgerald', '1925-04-10', 'Fiction', 10, 'The story of the mysterious Jay Gatsby and his love for the beautiful Daisy Buchanan.', '101-1-1-01'),
('To Kill a Mockingbird', 'J. B. Lippincott & Co.', 'Harper Lee', '1960-07-11', 'Fiction', 8, 'The story of young Scout Finch and her father, a lawyer who defends a black man in a small southern town.', '101-1-2-06'),
('1984', 'Secker & Warburg', 'George Orwell', '1949-06-08', 'Fiction', 12, 'The story of Winston Smith and his rebellion against a totalitarian regime that controls every aspect of citizen lives.', '101-2-5-42'),
('The Catcher in the Rye', 'Little, Brown and Company', 'J.D. Salinger', '1951-07-16', 'Fiction', 6, 'The story of Holden Caulfield, a teenage boy struggling with alienation and loss.', '105-3-4-14'),
('Pride and Prejudice', 'T. Egerton, Whitehall', 'Jane Austen', '1813-01-28', 'Fiction', 14, 'The story of the Bennet sisters and their search for love in early 19th century England.', '203-16-3-56'),
('The Hobbit', 'George Allen & Unwin', 'J.R.R. Tolkien', '1937-09-21', 'Fantasy', 9, 'The story of Bilbo Baggins and his adventures with a group of dwarves to reclaim their stolen treasure.', '203-16-3-57'),
('The Lord of the Rings', 'George Allen & Unwin', 'J.R.R. Tolkien', '1954-07-29', 'Fantasy', 11, 'The story of Frodo Baggins and his quest to destroy the One Ring, an evil artifact created by the Dark Lord Sauron.', '203-16-3-58'),
('The Hunger Games', 'Scholastic Corporation', 'Suzanne Collins', '2008-09-14', 'Science Fiction', 15, 'The story of Katniss Everdeen, a young girl forced to compete in a televised battle to the death in a post-apocalyptic world.', '203-16-3-59'),
('The Da Vinci Code', 'Doubleday', 'Dan Brown', '2003-03-18', 'Mystery', 3, 'The story of Robert Langdon, a symbologist who must solve a murder and decipher clues related to the Holy Grail.', '204-15-1-34'),
('The Girl with the Dragon Tattoo', 'Norstedts förlag', 'Stieg Larsson', '2005-08-01', 'Mystery', 4, 'The story of Lisbeth Salander, a hacker who assists journalist Mikael Blomkvist in his investigation into a wealthy family dark secrets.', '204-15-1-38'),
('The Picture of Dorian Gray', 'Lippincott Monthly Magazine', 'Oscar Wilde', '1890-07-01', 'Fiction', 6, 'The story of a young man who makes a Faustian bargain to remain forever young and beautiful, while his portrait ages and shows the effects of his moral decay.', '504-5-1-34'),
('The Road', 'Alfred A. Knopf', 'Cormac McCarthy', '2006-09-26', 'Fiction', 9, 'The story of a father and his young son journeying through a post-apocalyptic world, facing danger and desperation at every turn.', '324-5-1-34'),
('The Martian', 'Crown Publishing Group', 'Andy Weir', '2011-09-27', 'Science Fiction', 12, 'The story of Mark Watney, an astronaut stranded on Mars who must use his knowledge and resourcefulness to survive and find a way home.', '514-2-1-34'),
('The Fault in Our Stars', 'Dutton Books', 'John Green', '2012-01-10', 'Young Adult', 5, 'The story of Hazel and Gus, two teenagers with terminal cancer who fall in love and embark on a journey of hope and heartbreak.', '121-12-3-55');






-- 生成20条随机数据 patron
INSERT INTO patron (password, firstname, lastname, email, telephone, avatar)
SELECT
  CONCAT('pwd', LPAD(FLOOR(RAND() * 10000), 4, '0')) AS password,
  CONCAT('fn', LPAD(FLOOR(RAND() * 100), 2, '0')) AS firstname,
  CONCAT('ln', LPAD(FLOOR(RAND() * 100), 2, '0')) AS lastname,
  CONCAT('em', LPAD(FLOOR(RAND() * 100), 2, '0'), '@example.com') AS email,
  CONCAT('', LPAD(FLOOR(RAND() * 100000000), 8, '0')) AS telephone,
  CONCAT('avatars/', LPAD(FLOOR(RAND() * 10) + 1, 2, '0'), '.svg') AS avatar
FROM INFORMATION_SCHEMA.TABLES
LIMIT 20;