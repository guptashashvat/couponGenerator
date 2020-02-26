delete from UserData;
delete from CouponData;
delete from MerchantData;
delete from CouponMapping;

Insert into UserData (userId, name, userPhone) values ('SHAS12', 'Test1', '9123456780');
Insert into UserData (userId, name, userPhone) values ('SHAS13', 'Test2', '9234567890');
Insert into UserData (userId, name, userPhone) values ('SHAS14', 'Test3', '9034567890');
Insert into UserData (userId, name, userPhone) values ('SHAS15', 'Test4', '9401567890');

Insert into CouponData (couponId, startDate, endDate, status, amount) values ('1582653118910-20513', curDate(),  curDate()+30, 'ACTIVE', 100);
Insert into CouponData (couponId, startDate, endDate, status, amount) values ('1582653178908-36995', curDate()-1,  curDate()+29, 'ACTIVE', 200);
Insert into CouponData (couponId, startDate, endDate, status, amount) values ('1582653238908-16024', curDate()-31,  curDate()-1, 'EXPIRED', 200);
Insert into CouponData (couponId, startDate, endDate, status, amount) values ('1582653298910-28998', curDate()-40,  curDate()-10, 'EXPIRED', 100);
Insert into CouponData (couponId, startDate, endDate, status, amount) values ('1582653358909-33215', curDate()-10,  curDate()+20, 'ACTIVE', 250);

Insert into MerchantData (merchantId, merchantName) values ('AMA1', 'Amazon');
Insert into MerchantData (merchantId, merchantName) values ('ZOM1', 'Zomato');