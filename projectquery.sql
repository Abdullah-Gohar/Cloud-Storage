CREATE TABLE ADMIN(
UserName varchar(50) Primary Key,
Pass varchar(50) 
)
CREATE TABLE CLIENT(
UserName varchar(50) Primary Key,
Pass varchar(50) ,
Email varchar(50),
Purpose varchar(200),
ClientStatus int,
SpaceOcc int,
TotalSpace int Default(2097152)

)
INSERT INTO ADMIN VALUES('ayounas','kamkasjksajs')

Insert into Client
values
    ('abdullah01', 'password', 'ag@gmail.com', 'Shughal', 1, 0, default)