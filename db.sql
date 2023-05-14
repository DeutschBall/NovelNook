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

#关于用户，老师要求是id、password自动生成后再给用户展示， 所以：user表全拆开，userid都是自增
#登录就用user id + passowrd，选择哪个身份就查哪个表

#第一次release后变动5.3版

#删除user表   		新建 admin表、superuser表
#	patron表 	    userid自增   删掉username
#	staff表 		 username改成userid，自增
#book表              bookid自增 添加location字段;   添加book测试数据
#brorrw表、reservation表       参照的都是patron的userid

#5.4更改  新增returned表
#5.5 borrow表 resvation表用的userid bookid还是varchar，改成int

insert into staff values (123,'123','1','2','3','qewqe','asdad');
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

#insert into admin values (123,'123','1','2','3','qewqe','asdad');
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
                                     userid int,
                                     bookid int,
                                     borrowtime DATE,
                                     deadline DATE,
                                     status ENUM('returned','borrowing'),
                                     FOREIGN KEY(userid) REFERENCES patron(userid) ON UPDATE CASCADE ON DELETE CASCADE ,
                                     FOREIGN KEY(bookid) REFERENCES book(bookid) ON UPDATE CASCADE ON DELETE CASCADE ,
                                     PRIMARY KEY (borrowid)
);

CREATE TABLE IF NOT EXISTS reservation(
                                          reservationid VARCHAR(255),
                                          userid int,
                                          bookid int,
                                          reservationtime DATE,
                                          status ENUM('canceled','waiting','satisfied'),
                                          FOREIGN KEY(userid) REFERENCES patron(userid) ON UPDATE CASCADE ON DELETE CASCADE ,
                                          FOREIGN KEY(bookid) REFERENCES book(bookid) ON UPDATE CASCADE ON DELETE CASCADE ,
                                          PRIMARY KEY (reservationid)
);

CREATE TABLE IF NOT EXISTS returned(
                                     borrowid VARCHAR(255) PRIMARY KEY ,
                                     returntime DATE, #还书的实际时间，还书的ddl在borrow表中
                                     fineamount numeric(10,2), #罚款金额
                                     ispay boolean, #是否交过罚款
                                     FOREIGN KEY(borrowid) REFERENCES borrow(borrowid) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS fine(
                                 id int PRIMARY KEY AUTO_INCREMENT,
                                 money numeric(10,2)
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
INSERT INTO patron (password, firstname, lastname, email, telephone, avatar) VALUES
('s3cr3tp@ss', 'John', 'Doe', 'johndoe@example.com', '+1-234-567-8901', 'avatars/7.svg'),
('7yN7UBG@vk', 'Jane', 'Smith', 'janesmith@example.com', '+1-345-678-9012', 'avatars/3.svg'),
('p@ssw0rd123', 'Bob', 'Johnson', 'bobjohnson@example.com', '+1-456-789-0123', 'avatars/2.svg'),
('pa$sw0rd456', 'Sarah', 'Lee', 'sarahlee@example.com', '+1-567-890-1234', 'avatars/6.svg'),
('Qwerty123!', 'David', 'Chen', 'davidchen@example.com', '+1-678-901-2345', 'avatars/10.svg'),
('myp@ssw0rd', 'Linda', 'Wang', 'lindawang@example.com', '+1-789-012-3456', 'avatars/5.svg'),
('Passw0rd', 'Eric', 'Kim', 'erickim@example.com', '+1-890-123-4567', 'avatars/8.svg'),
('starbucks', 'Emily', 'Jones', 'emilyjones@example.com', '+1-901-234-5678', 'avatars/1.svg'),
('Hello123', 'Mark', 'Davis', 'markdavis@example.com', '+1-012-345-6789', 'avatars/9.svg'),
('P@ssword123', 'Melissa', 'Lopez', 'melissalopez@example.com', '+1-123-456-7890', 'avatars/4.svg'),
('sunshine', 'Chris', 'Brown', 'chrisbrown@example.com', '+1-234-567-8901', 'avatars/2.svg'),
('pa$$word', 'Ava', 'Taylor', 'avataylor@example.com', '+1-345-678-9012', 'avatars/3.svg'),
('qwertyuiop', 'Lucas', 'Wilson', 'lucaswilson@example.com', '+1-456-789-0123', 'avatars/6.svg'),
('myPassword', 'Sophia', 'Martin', 'sophiamartin@example.com', '+1-567-890-1234', 'avatars/7.svg'),
('baseball', 'Ethan', 'Flores', 'ethanflores@example.com', '+1-678-901-2345', 'avatars/10.svg'),
('password1', 'Isabella', 'Garcia', 'isabellagarcia@example.com', '+1-789-012-3456', 'avatars/1.svg'),
('qwerty123', 'Michael', 'Bailey', 'michaelbailey@example.com', '+1-890-123-4567', 'avatars/8.svg'),
('hunter2', 'Daniel', 'Nguyen', 'danielnguyen@example.com', '+1-901-234-5678', 'avatars/4.svg'),
('iloveme', 'Olivia', 'Hernandez', 'oliviahernandez@example.com', '+1-012-345-6789', 'avatars/9.svg'),
('1234567890', 'Matthew', 'Allen', 'matthewallen@example.com', '+1-123-456-7890', 'avatars/5.svg');

INSERT INTO staff (password, firstname, lastname, email, telephone, avatar) VALUES
('123456', 'aaa', 'bbb', '123@ex.com', '74184561', 'avatars/5.svg');

INSERT INTO borrow (borrowid, userid, bookid, borrowtime, deadline, status) VALUES
('brid001', '1', '1', '2023-05-05', '2023-06-05', 'borrowing'),
('brid002', '1', '2', '2023-05-05', '2023-06-05', 'borrowing'),
('brid003', '1', '3', '2023-05-05', '2023-06-05', 'returned'),
('brid004', '2', '4', '2023-05-05', '2023-06-05', 'borrowing'),
('brid005', '3', '5', '2023-05-05', '2023-06-05', 'returned');

INSERT INTO returned (borrowid, returntime, fineamount, ispay) VALUES
('brid003', '2023-06-08', 3.5, 0),
('brid005', '2020-06-09', 4.4, 0);


SELECT COUNT(*) FROM borrow WHERE status='borrowing';

SELECT SUM(returned.fineamount) AS total_fine
FROM returned
WHERE returned.ispay = 0;

SELECT borrow.borrowid, borrow.userid, book.bookname, borrow.borrowtime, borrow.deadline, returned.returntime, returned.fineamount
FROM book
JOIN borrow ON book.bookid = borrow.bookid
JOIN returned ON borrow.borrowid = returned.borrowid
WHERE borrow.status = 'returned' and returned.ispay = 0;


INSERT INTO fine(money) VALUES
(2);
