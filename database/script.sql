use
estate;

INSERT INTO role(code, name)
values ('MANAGER', 'Quản trị hệ thống');
INSERT INTO role(code, name)
values ('STAFF', 'Nhân viên');

INSERT INTO users(username, password, full_name, status, phone, email)
VALUES ('nguyenvana', '$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG', 'nguyen van a', 1, '012345678',
        'nguyenvana1@gmail.com'),
       ('nguyenvanb', '$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG', 'nguyen van b', 1, '02357897',
        'nguyenvanb1@gmail.com'),
       ('nguyenvanc', '$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG', 'nguyen van c', 1, '02357897',
        'nguyenvanc1@gmail.com'),
       ('nguyenvand', '$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG', 'nguyen van d', 1, '02357897',
        'nguyenvand1@gmail.com');

INSERT INTO user_role(user_id, role_id)
VALUES (1, 1);
INSERT INTO user_role(user_id, role_id)
VALUES (2, 2);
INSERT INTO user_role(user_id, role_id)
VALUES (3, 2);
INSERT INTO user_role(user_id, role_id)
VALUES (4, 2);

INSERT INTO buildings(building_name, number_of_basement, district_code, building_types, street, ward, structure,
                      floor_area, direction, level, rent_price, rent_price_description, service_fee, car_fee,
                      motorbike_fee, overtime_fee, water_fee, electricity_fee, deposit, payment, rent_time,
                      decoration_time, brokerage_fee, note, link_of_building, map, image_name, manager_phone,
                      manager_name, rent_area_description)
VALUES ('Nam Giao Building Tower', 3, 'Q1', 'TANG_TRET,NGUYEN_CAN', '59 Phan Xích Long', 'Phường 1', 'To', 300,
        'Hướng nam', 'A', 300, '300 triệu/tháng', 10, 20, 10, 50, 10, 10, 100, 'Thanh toán', '20/10/2020', '20/11/2020',
        30, 'Toà nhà hạng A', 'link', 'map', 'nam-giao-building-tower.jpg', '0335466', 'Nguyễn Văn Tài',
        '100m2, 200m2, 300m2'),
       ('ACM Tower', 5, 'Q2', 'NGUYEN_CAN,NOI_THAT', '96 Cao Thắng', 'Phường 2', 'Vừa', 500, 'Hướng tây', 'B', 500,
        '300 triệu/tháng', 10, 20, 10, 50, 10, 10, 100, 'Thanh toán', '20/11/2020', '20/12/2020', 30, 'Toà nhà hạng B',
        'link', 'map', 'acm-building-tower.jpg', '03244456', 'Dương Thu Thảo', '100m2, 200m2, 700m2'),
       ('Alpha Building Tower', 3, 'Q3', 'NGUYEN_CAN', '153 Nguyễn Đình Chiểu', 'Phường 3', 'To', 400, 'Hướng đông',
        'A', 400, '350 triệu/tháng', 10, 20, 10, 50, 10, 10, 100, 'Thanh toán', '14/10/2020', '14/11/2020', 30,
        'Toà nhà hạng A', 'link', 'map', 'alpha-tower.jpg', '03264494', 'Bùi Hải Anh', '500m2, 1000m2'),
       ('IDD Building', 3, 'Q1', 'TANG_TRET,NGUYEN_CAN,NOI_THAT', '111 Lý Chính Thắng', 'Phường 1', 'Nhỏ', 200,
        'Hướng nam', 'A', 200, '200 triệu/tháng', 10, 20, 10, 50, 10, 10, 100, 'Thanh toán', '15/10/2020', '15/11/2020',
        30, 'Toà nhà hạng A', 'link', 'map', 'idd-building.jpg', '012345678', 'Lê Trọng Kim', '400m2'),
       ('BMC Tower', 3, 'Q3', 'NGUYEN_CAN', '64 Hà Huy Tập', 'Phường 2', 'Vừa', 250, 'Hướng bắc', 'C', 100,
        '100 triệu/tháng', 10, 20, 10, 50, 10, 10, 100, 'Thanh toán', '15/10/2020', '20/11/2020', 30, 'Toà nhà hạng A',
        'link', 'map', 'bmc-building-tower.jpg', '96036143', 'Đặng Thị Anh Thư', null),
       ('Mplaza Tower', 7, 'Q2', null, '73 Triều Vũ', 'Phường', 'Vừa', 250, 'Hướng bắc', 'C', 100, '100 triệu/tháng',
        10, 20, 10, 50, 10, 10, 100, 'Thanh toán', '15/10/2020', '20/11/2020', 30, 'Toà nhà hạng A', 'link', 'map',
        'mplaza-tower.jpg', '96036143', 'Đặng Thị Anh Thư', null),
       ('Saigon Centre Tower', 7, 'Q2', null, '73 Triều Vũ', 'Phường', 'Vừa', 250, 'Hướng bắc', 'C', 100,
        '100 triệu/tháng', 10, 20, 10, 50, 10, 10, 100, 'Thanh toán', '15/10/2020', '20/11/2020', 30, 'Toà nhà hạng A',
        'link', 'map', 'saigon-centre-tower.jpg', '96036143', 'Đặng Thị Anh Thư', null),
       ('Saigon Trade Building', 7, 'Q2', null, '73 Triều Vũ', 'Phường', 'Vừa', 250, 'Hướng bắc', 'C', 100,
        '100 triệu/tháng', 10, 20, 10, 50, 10, 10, 100, 'Thanh toán', '15/10/2020', '20/11/2020', 30, 'Toà nhà hạng A',
        'link', 'map', 'saigon-trade-tower.jpg', '96036143', 'Đặng Thị Anh Thư', null),
       ('Vietcombank Building Tower', 7, 'Q2', null, '73 Triều Vũ', 'Phường', 'Vừa', 250, 'Hướng bắc', 'C', 100,
        '100 triệu/tháng', 10, 20, 10, 50, 10, 10, 100, 'Thanh toán', '15/10/2020', '20/11/2020', 30, 'Toà nhà hạng A',
        'link', 'map', 'vietcombank-tower.jpg', '96036143', 'Đặng Thị Anh Thư', null);
INSERT INTO rent_area(value, building_id)
VALUES (100, 1),
       (200, 1),
       (300, 1),
       (100, 2),
       (200, 2),
       (700, 2),
       (500, 3),
       (1000, 3),
       (400, 4);

INSERT INTO assignment_building(building_id, user_id)
VALUES (1, 2),
       (1, 3),
       (1, 4),
       (2, 2),
       (2, 3),
       (3, 2),
       (3, 3);

INSERT INTO customers(full_name, phone, email, company, demand, note, status)
VALUES ('Nguyễn Việt Hoàng', '0981212', 'viethoang@gmail.com', 'ABC BANK', '100m2', 'Khách tiềm năng', 1),
       ('Bùi Linh Chi', '00654646', 'linhchi@gmail.com', 'FPT software', '140m2', 'Khách bình thường', 1),
       ('Đồ Ngọc Đại', '321321321', 'ngocdai@gmail.com', 'Techkid', '200m2', 'Khách tiềm năng', 1),
       ('Hoàng Thu thảo', '013445656', 'thuthao@gmail.com', 'ABC BANK', '300m2', 'Khách tiềm năng', 1),
       ('Đường Bá Hổ', '03136144', 'baho@gmail.com', 'Viettel', '300m2', 'Khách tiềm năng', 1);


INSERT INTO assignment_customer(staff_id, customer_id)
VALUES (2, 1),
       (3, 1),
       (2, 2),
       (3, 3),
       (4, 3);

INSERT INTO transactions(code, note, customer_id, created_date)
VAlUES ('TRANSACTION_1', 'đi chơi với khách', 1, '2022-08-31 12:02:20'),
       ('TRANSACTION_1', 'đi ăn với khách', 1, '2022-08-31 10:02:20'),
       ('TRANSACTION_2', 'Đến toà nhà A', 1, '2022-08-30 9:00:20'),
       ('TRANSACTION_3', 'Ký hợp đồng 50tr', 1, '2022-08-29 9:10:30');
