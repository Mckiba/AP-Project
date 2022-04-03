INSERT INTO Customers (customer_id, full_name, email, contact, service_address, complaint_code, account_number, password)

VALUE (1004, 'Christopher Wilks', 'test', 8765209032, 'Hopewell Hanover',3411,1000077217,'password');

INSERT INTO Accounts(account_id, payment_status, amount_due, payment_due_date, created_at)
Values (1004, 'Payment Overdue', 4300, '2020-01-01 10:10:10',  '2020-01-01 15:10:10');