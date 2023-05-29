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

#5.19  book表的publishtime改为vachar，因为从isbn接口拿到的出版日期不一定完整。对程序没什么影响，本来book类的publishtime就是String
#      新增isbn_bookid表
#      添加book数据

#5.25 新增book_realid表
#     新增booklimit


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
                                   publishtime VARCHAR(255),
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


#5.19
CREATE TABLE IF NOT EXISTS isbn_bookid(
                                          isbn VARCHAR(255) PRIMARY KEY,
                                          bookid int,
                                          FOREIGN KEY(bookid) REFERENCES book(bookid) ON UPDATE CASCADE ON DELETE CASCADE
);

#5.25
CREATE TABLE IF NOT EXISTS booklimit(
                                        id int PRIMARY KEY AUTO_INCREMENT,
                                        limitnum int
);
#5.25
CREATE TABLE IF NOT EXISTS book_realid(
                                          realid VARCHAR(255) PRIMARY KEY,
                                          bookid int,
                                          borrowid VARCHAR(255),
                                          FOREIGN KEY(bookid) REFERENCES book(bookid) ON UPDATE CASCADE ON DELETE CASCADE
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



INSERT INTO fine(money) VALUES
    (2);

INSERT INTO booklimit(limitnum) VALUES
    (5);


#==============重新生成book信息============================
#http://localhost:8080/staff/book/addByISBN/9780142437247/11/102-2-2-11
#http://localhost:8080/staff/book/addByISBN/9780316769488/9/102-2-3-06
#http://localhost:8080/staff/book/addByISBN/9780156907392/6/211-1-1-24
#http://localhost:8080/staff/book/addByISBN/9780618640157/7/303-2-1-01
#http://localhost:8080/staff/book/addByISBN/9780060850524/8/103-2-6-07
#或：
insert into novelnook.book (bookname, press, author, publishtime, catagory, remain, introduction, location)
values  ('Moby-Dick or, The Whale', '', 'Herman Melville', '2003-2-21', 'novel', 11, '&#34;It is the horrible texture of a fabric that should be woven of ships&#39; cables and hawsers. A Polar wind blows through it, and birds of prey hover over it.&#34;    So Melville wrote of his masterpiece, one of the greatest works of imagination in literary history. In part, Moby-Dick is the story of an eerily compelling madman pursuing an unholy war against a creature as vast and dangero...', '102-2-2-11'),
        ('The Catcher in the rye', 'Little Brown and Company', 'Salinger', '2014-01-01', 'novel', 9, 'The hero-narrator of The Catcher in the Rye is an ancient child of sixteen, a native New Yorker named Holden Caulfield. Through circumstances that tend to preclude adult, secondhand description, he leaves his prep school in Pennsylvania and goes underground in New York City for three days. The boy himself is at once too simple and too complex for us to make any final comment about him or his story. Perhaps the safest thing we can say about Holden is that he was born in the world not just strongly attracted to beauty but, almost, hopelessly impaled on it. There are many voices in this novel: children''s voices, adult voices, underground voices-but Holden''s voice is the most eloquent of all. Transcending his own vernacular, yet remaining marvelously faithful to it, he issues a perfectly articulated cry of mixed pain and pleasure. However, like most lovers and clowns and poets of the higher orders, he keeps most of the pain to, and for, himself. The pleasure he gives away, or sets aside, with all his heart. It is there for the reader who can handle it to keep.
J.D. Salinger''s classic novel of teenage angst and rebellion was first published in 1951. The novel was included on Time''s 2005 list of the 100 best English-language novels written since 1923. It was named by Modern Library and its readers as one of the 100 best English-language novels of the 20th century. It has been frequently challenged in the court for its liberal use of profanity and portrayal of sexuality and in the 1950''s and 60''s it was the novel that every teenage boy wants to read.', '102-2-3-06'),
        ('To The Lighthouse', '', 'Virginia Woolf', '1989', 'novel', 6, '', '211-1-1-24'),
        ('The Lord of the Rings', '', 'J.R.R. Tolkien', '2005-10-12', 'novel', 7, 'One Ring to rule them all, One Ring to find them, One Ring to bring them all and in the darkness bind them In ancient times the Rings of Power were crafted by the Elven-smiths, and Sauron, the Dark Lord, forged the One Ring, filling it with his own power so that he could rule all others. But the One Ring was taken from him, and though he sought it throughout Middle-earth, it re...', '303-2-1-01'),
        ('Brave New World', '', 'Aldous Huxley', '2006-10-17', 'novel', 8, 'Aldous Huxley&#39;s tour de force, &#34;Brave New World&#34; is a darkly satiric vision of a &#34;utopian&#34; future where humans are genetically bred and pharmaceutically anesthetized to passively serve a ruling order. A powerful work of speculative fiction that has enthralled and terrified readers for generations, it remains remarkably relevant to this day as both a warning to be heeded as we h...', '103-2-6-07');

insert into novelnook.isbn_bookid (isbn, bookid)
values  ('9780142437247', 1),
        ('9780316769488', 2),
        ('9780156907392', 3),
        ('9780618640157', 4),
        ('9780060850524', 5);

insert into novelnook.book_realid (realid, bookid, borrowid)
values  ('100001', 1, null),
        ('1000010', 1, null),
        ('1000011', 1, null),
        ('100002', 1, null),
        ('100003', 1, null),
        ('100004', 1, null),
        ('100005', 1, null),
        ('100006', 1, null),
        ('100007', 1, null),
        ('100008', 1, null),
        ('100009', 1, null),
        ('200001', 2, null),
        ('200002', 2, null),
        ('200003', 2, null),
        ('200004', 2, null),
        ('200005', 2, null),
        ('200006', 2, null),
        ('200007', 2, null),
        ('200008', 2, null),
        ('200009', 2, null),
        ('300001', 3, null),
        ('300002', 3, null),
        ('300003', 3, null),
        ('300004', 3, null),
        ('300005', 3, null),
        ('300006', 3, null),
        ('400001', 4, null),
        ('400002', 4, null),
        ('400003', 4, null),
        ('400004', 4, null),
        ('400005', 4, null),
        ('400006', 4, null),
        ('400007', 4, null),
        ('500001', 5, null),
        ('500002', 5, null),
        ('500003', 5, null),
        ('500004', 5, null),
        ('500005', 5, null),
        ('500006', 5, null),
        ('500007', 5, null),
        ('500008', 5, null);
