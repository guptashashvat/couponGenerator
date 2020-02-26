create table if not exists UserData(
userId varchar(10) not null,
name varchar(50) not null,
userPhone varchar(10) not null
);
create table if not exists CouponData(
couponId varchar(30) not null,
startDate date not null,
endDate date not null,
status varchar(10) not null,
amount bigint not null
);
create table if not exists MerchantData(
merchantId varchar(10) not null,
merchantName varchar(50) not null
);
create table if not exists CouponMapping(
couponId varchar(30) not null,
userId varchar(10) not null
);
alter table couponMapping add foreign key(couponId) references CouponData(couponId);
alter table couponMapping add foreign key(userId) references UserData(userId);