利用 Spring 技術 實現 restful 的完整CRUD
http://localhost:8081/booking/ 此網址可以進行查詢 後面加上序號可以進行指定預約紀錄查詢
並且可以在 insomnia 進行 post, put, delete 來實現資料庫調整

以下附上我的資料庫建立 sql 語法

drop table if exists booking;
drop table if exists court;
drop table if exists club_member;
-- 建立 court
create table if not exists court(
	court_id int primary key,
	court_name varchar(50) not null unique
);
-- 建立 club_member
create table if not exists club_member(
	club_member_id int auto_increment primary key,
    club_member_username varchar(50) not null unique,
    club_member_password varchar(50) not null,
	club_member_name varchar(50) not null,
    club_member_birth date not null,
    club_member_booking_record_id int
);

-- 建立 booking
create table if not exists booking(
    booking_id int auto_increment primary key,
    court_id int not null,
    club_member_id int not null,
    use_date timestamp not null,
    create_date timestamp default current_timestamp,
    foreign key (court_id) references court(court_id),
    foreign key (club_member_id) references club_member(club_member_id),
    constraint unique_court_id_and_use_date unique(court_id, use_date)
);

-- 建立預設資料
insert into court(court_id, court_name) values(1, '東側球場');
insert into court(court_id, court_name) values(2, '西側球場');
insert into court(court_id, court_name) values(3, '南側球場');
insert into court(court_id, court_name) values(4, '北側球場');

insert into club_member(club_member_username, club_member_password, club_member_name, club_member_birth, club_member_booking_record_id) values('john@gmail.com', '12345678', 'John', '1992-01-14', 1);
insert into club_member(club_member_username, club_member_password, club_member_name, club_member_birth, club_member_booking_record_id) values('mary@gmail.com', '12345678', 'mary', '1994-11-11', 2);
insert into club_member(club_member_username, club_member_password, club_member_name, club_member_birth, club_member_booking_record_id) values('ken@gmail.com', '12345678', 'ken', '1990-05-07', 3);

insert into booking(court_id, club_member_id, use_date) values(2, 1, '2024-02-01 13:00:00');
insert into booking(court_id, club_member_id, use_date) values(3, 2, '2023-02-02 14:00:00');
insert into booking(court_id, club_member_id, use_date) values(1, 3, '2023-02-03 15:00:00');