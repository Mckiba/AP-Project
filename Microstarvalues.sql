INSERT INTO Customers (customer_id, full_name, email, contact, service_address, complaint_code, account_number, password)
VALUES (1004, 'Christopher Wilks', 'test', 8765209032, 'Hopewell Hanover',3411,1000077217,'password'),
        (1005, 'Combo Boss Wilks', 'test', 8765209032, 'MayPen Clarendon',3412,1000077218,'password');


INSERT INTO Accounts(account_id, payment_status, amount_due, payment_due_date, created_at)
Values (1004, 'Payment Overdue', 4300, '2020-01-01 10:10:10',  '2020-01-01 15:10:10');

INSERT INTO customerRep (rep_id, full_name, email, complaint_code, password)
Values (1200, 'Kiana Wright', 'kianawright@microstar.com', '2121', 'kianamicrostarrep2021'),
         (1201, 'Shanice Gordon', 'gordonShanice@microstar.com', '4876', 'gordonshanmicrostarrep2011'),
         (1202, 'Kemar Hycon', 'kemarHy@microstar.com', '3411', 'Kemhyconmicrostarrep2021');

INSERT INTO Technician (staff_id, full_name, email, complaint_code, password)
Values (1811, 'Roberto Jones', 'robertojones@microstar.com', 2121, 'robertojones2018'),
       (1812, 'Brianna Nicole', 'briannanicole@microstar.com', 4876, 'nicolebri2020'),
       (1813, 'Myles Cunningham', 'Cunninghammyles21@microstar.com', 3411, 'mmylescunning2011');


