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
#      新增了isbn_bookid表
#      添加了book数据

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

CREATE TABLE IF NOT EXISTS isbn_bookid(
                                 isbn VARCHAR(255) PRIMARY KEY,
                                 bookid int,
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

#==============重新生成book信息============================
insert into book (bookname, press, author, publishtime, catagory, remain, introduction, location)
values  ('1984', 'New American Library,', 'by George Orwell', '1977-01-01', 'Fiction', 12, 'a novel', '101-1-1-21'),
        ('The Great Gatsby', '', 'F. Scott Fitzgerald', '2004-9-30', 'Literature', 13, 'In 1922, F. Scott Fitzgerald announced his decision to write &#34;something new--something extraordinary and beautiful and simple and intricately patterned.&#34; That extraordinary, beautiful, intricately patterned, and above all, simple novel became The Great Gatsby, arguably Fitzgerald&#39;s finest work and certainly the book for which he is best known. A portrait of the Jazz Age in all ...', '202-1-2-11'),
        ('Moby-Dick or, The Whale', '', 'Herman Melville', '2003-2-21', 'novel', 11, '&#34;It is the horrible texture of a fabric that should be woven of ships&#39; cables and hawsers. A Polar wind blows through it, and birds of prey hover over it.&#34;    So Melville wrote of his masterpiece, one of the greatest works of imagination in literary history. In part, Moby-Dick is the story of an eerily compelling madman pursuing an unholy war against a creature as vast and dangero...', '102-1-1-07'),
        ('The Catcher in the rye', 'Little Brown and Company', 'Salinger', '2014-01-01', 'Literature', 22, 'The hero-narrator of The Catcher in the Rye is an ancient child of sixteen, a native New Yorker named Holden Caulfield. Through circumstances that tend to preclude adult, secondhand description, he leaves his prep school in Pennsylvania and goes underground in New York City for three days. The boy himself is at once too simple and too complex for us to make any final comment about him or his story. Perhaps the safest thing we can say about Holden is that he was born in the world not just strongly attracted to beauty but, almost, hopelessly impaled on it. There are many voices in this novel: children''s voices, adult voices, underground voices-but Holden''s voice is the most eloquent of all. Transcending his own vernacular, yet remaining marvelously faithful to it, he issues a perfectly articulated cry of mixed pain and pleasure. However, like most lovers and clowns and poets of the higher orders, he keeps most of the pain to, and for, himself. The pleasure he gives away, or sets aside, with all his heart. It is there for the reader who can handle it to keep.
J.D. Salinger''s classic novel of teenage angst and rebellion was first published in 1951. The novel was included on Time''s 2005 list of the 100 best English-language novels written since 1923. It was named by Modern Library and its readers as one of the 100 best English-language novels of the 20th century. It has been frequently challenged in the court for its liberal use of profanity and portrayal of sexuality and in the 1950''s and 60''s it was the novel that every teenage boy wants to read.', '103-2-1-21'),
        ('To The Lighthouse', '', 'Virginia Woolf', '1989', 'Fiction', 25, '', '201-2-1-01'),
        ('The Lord of the Rings', '', 'J.R.R. Tolkien', '2005-10-12', 'Literature', 18, 'One Ring to rule them all, One Ring to find them, One Ring to bring them all and in the darkness bind them In ancient times the Rings of Power were crafted by the Elven-smiths, and Sauron, the Dark Lord, forged the One Ring, filling it with his own power so that he could rule all others. But the One Ring was taken from him, and though he sought it throughout Middle-earth, it re...', '301-1-3-12'),
        ('Brave New World', '', 'Aldous Huxley', '2006-10-17', 'novel', 11, 'Aldous Huxley&#39;s tour de force, &#34;Brave New World&#34; is a darkly satiric vision of a &#34;utopian&#34; future where humans are genetically bred and pharmaceutically anesthetized to passively serve a ruling order. A powerful work of speculative fiction that has enthralled and terrified readers for generations, it remains remarkably relevant to this day as both a warning to be heeded as we h...', '101-1-1-13'),
        ('纳尼亚故事集', 'HarperTrophy', 'C. S. Lewis, Pauline Baynes（Illustrator）', '1994-07-01', 'Literature', 10, 'The Chronicles of Narnia， by C.S. Lewis， is one of the very few sets of books that should be read three times： in childhood， early adulthood， and late in life. In brief， four children travel repeatedly to a world in which they are far more than mere children and everything is far more than it seems. Richly told， populated with fascinating characters， perfectly realized in detail of world and pacing of plot， and profoundly allegorical， the story is infused throughout with the timeless issues of good and evil， faith and hope. This boxed set edition includes all seven volumes.：', '101-1-2-24'),
        ('The Hobbit; or, There and Back Again', '', 'J. R. R. Tolkien', '2012-9-18', 'novel', 14, 'J.R.R. Tolkien&#39;s classic prelude to his Lord of the Rings trilogy    Bilbo Baggins is a hobbit who enjoys a comfortable, unambitious life, rarely traveling any farther than his pantry or cellar. But his contentment is disturbed when the wizard Gandalf and a company of dwarves arrive on his doorstep one day to whisk him away on an adventure. They have launched a plot to raid the tr...', '104-1-1-03'),
        ('Jane Eyre', 'Penguin,', 'Charlotte, Bronte', '2006-01-01', 'Literature', 16, 'A novel of intense power and intrigue, Jane Eyre has dazzled generations of readers with its depiction of a woman''s quest for freedom. Having grown up an orphan in the home of her cruel aunt and at a harsh charity school, Jane Eyre becomes an independent and spirited survivor-qualities that serve her well as governess at Thornfield Hall. But when she finds love with her sardonic employer, Rochester, the discovery of his terrible secret forces her to make a choice. Should she stay with him whatever the consequences or follow her convictions, even if it means leaving her beloved? This updated Penguin Classics edition features a new introduction by Brontë scholar and award-winning novelist Stevie Davies, as well as comprehensive notes, a chronology, further reading, and an appendix.', '105-1-1-04'),
        ('Adventures of Huckleberry Finn', 'Penguin Classics', 'Mark Twain', '2014-11', 'novel', 20, '', '203-2-1-21'),
        ('Fahrenheit 451', 'Simon & Schuster', 'Ray Bradbury', '2013-6-10', 'Art', 23, 'Ray Douglas Bradbury (/ˈbrædˌbɛri/; August 22, 1920 – June 5, 2012) was an American author and screenwriter. He worked in a variety of genres, including fantasy, science fiction, horror, and mystery fiction.
Widely known for his dystopian novel Fahrenheit 451 (1953), and his science-fiction and horror-story collections, The Martian Chronicles (1950), The Illustrated Man (1951), and I Sing the Body Electric (1969), Bradbury was one of the most celebrated 20th- and 21st-century American writers.[2] While most of his best known work is in speculative fiction, he also wrote in other genres, such as the coming-of-age novel Dandelion Wine (1957) and the fictionalized memoir Green Shadows, White Whale (1992).
Recipient of numerous awards, including a 2007 Pulitzer Citation, Bradbury also wrote and consulted on screenplays and television scripts, including Moby Dick and It Came from Outer Space. Many of his works were adapted to comic book, television, and film formats.
Upon his death in 2012, The New York Times called Bradbury "the writer most responsible for bringing modern science fiction into the literary mainstream"', '101-2-2-19'),
        ('The Odyssey ', 'Penguin Books', 'Fagles Robert Homer', '1997-01-01', 'Art', 12, null, '101-2-3-08'),
        ('Frankenstein', 'Penguin Classics', 'Mary Shelley', '2003-5', 'novel', 22, '在线阅读本书

  Edited by Maurice Hindle.', '107-3-4-06'),
        ('Gone with the Wind', 'Pocket Books', 'Margaret Mitchell', '2008-01-01', 'Literature', 8, 'Margaret Mitchell''s epic novel of love and war won the Pulitzer Prize and went on to give rise to two authorized sequels and one of the most popular and celebrated movies of all time.  Many novels have been written about the Civil War and its aftermath. None take us into the burning fields and cities of the American South as  Gone With the Wind  does, creating haunting scenes and thrilling portraits of characters so vivid that we remember their words and feel their fear and hunger for the rest of our lives.  In the two main characters, the white-shouldered, irresistible Scarlett and the flashy, contemptuous Rhett, Margaret Mitchell not only conveyed a timeless story of survival under the harshest of circumstances, she also created two of the most famous lovers in the English-speaking world since Romeo and Juliet.', '401-1-1-05'),
        ('The Scarlet Letter (Dover Thrift Editions)', '', 'Nathaniel Hawthorne', '1994-05-02', 'Fiction', 31, 'For nearly a century and a half, Hawthorne&#39;s masterpiece has mesmerized readers and critics alike. One of the greatest American novels, its themes of sin, guilt, and redemption, woven through a story of adultery in the early days of the Massachusetts Colony, are revealed with remarkable psychological penetration and understanding of the human heart. New introductory Note.    ', '111-3-4-22'),
        ('The Grapes of Wrath', '', 'John Steinbeck', '2006-3-28', 'novel', 16, 'First published in 1939, Steinbeck’s Pulitzer Prize-winning epic of the Great Depression chronicles the Dust Bowl migration of the 1930s and tells the story of one Oklahoma farm family, the Joads—driven from their homestead and forced to travel west to the promised land of California. Out of their trials and their repeated collisions against the hard realities of an America div...', '302-2-1-12'),
        ('Animal Farm', '', '', '1996-4-1', 'Fiction', 24, 'Since its publication in 1946, George Orwell&#39;s fable of a workers&#39; revolution gone wrong has rivaled Hemingway&#39;s The Old Man and the Sea as the Shortest Serious Novel It&#39;s OK to Write a Book Report About. (The latter is three pages longer and less fun to read.) Fueled by Orwell&#39;s intense disillusionment with Soviet Communism, Animal Farm is a nearly perfect piece of writing, bo...', '501-2-1-15');

#============== bookid 替换成上面插入书后的实际bookid==========================
insert into novelnook.isbn_bookid (isbn, bookid)
values  ('9780451524935', 42),
        ('9780743273565', 47),
        ('9780142437247', 49),
        ('9780316769488', 50),
        ('9780156907392', 51),
        ('9780618640157', 53),
        ('9780060850524', 54),
        ('9780064471190', 55),
        ('9780547928227', 56),
        ('9780141441146', 57),
        ('9780143107323', 58),
        ('9781451673319', 59),
        ('9780140268867', 60),
        ('9780141439471', 61),
        ('9781416548942', 62),
        ('9780486280486', 63),
        ('9780143039433', 64),
        ('9780451526342', 65);