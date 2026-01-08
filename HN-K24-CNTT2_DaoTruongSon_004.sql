CREATE database hackathon;
USE hackathon;
-- PHẦN 1: Tạo CSDL và các bảng
-- Tạo bảng (10 điểm) Tạo 4 bảng User, Product, Order, Order_Detail với cấu trúc và kiểu dữ liệu hợp lý. Đảm bảo có các khóa chính (PK) và khóa ngoại (FK) để liên kết các bảng.
CREATE table User(
	user_id VARCHAR(5) PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    user_email VARCHAR(100) NOT NULL UNIQUE,
    user_phone VARCHAR(15) NOT NULL UNIQUE
);

CREATE table Product(
	product_id VARCHAR(5) PRIMARY KEY,
    product_name VARCHAR(150) NOT NULL,
    product_price DECIMAL(10,2) NOT NULL,
    stock_quantity INT NOT NULL
);

CREATE table Orders(
	order_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(5),
    foreign key(user_id) references User(user_id),
    order_date DATE NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    order_status VARCHAR(20) NOT NULL
);

CREATE table Order_Detail(
	order_detail_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT,
    product_id VARCHAR(5),
    foreign key(order_id) references Orders(order_id) ON DELETE SET NULL,
    foreign key(product_id) references Product(product_id),
    quantity INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL
);

-- Chèn dữ liệu (8 điểm) Thêm dữ liệu vào 4 bảng đã tạo
-- User
INSERT INTO User(user_id,user_name,user_email,user_phone) VALUES
('U001','Nguyễn Văn An','an.nguyen@gmail.com','0912345678'),
('U002','Trần Thị Bích','bich.tran@gmail.com','0923456789'),
('U003','Lê Hoàng Minh','minh.le@gmail.com','0934567890'),
('U004','Phạm Thu Hà','ha.pham@gmail.com','0945678901'),
('U005','Võ Quốc Huy','huy.vo@gmail.com','0956789012');

-- Product
INSERT INTO Product(product_id,product_name,product_price,stock_quantity) VALUES
('P001','Áo thun nam',199000,50),
('P002','Quần jean nữ',399000,40),
('P003','Giày sneaker',899000,30),
('P004','Túi xách thời trang',599000,20),
('P005','Đồng hồ đeo tay',1299000,15);


-- Order
INSERT INTO Orders(order_id,user_id,order_date,total_price,order_status) VALUES
(1,'U001','2025-03-01',1098000,'Completed'),
(2,'U002','2025-03-02',399000,'Completed'),
(3,'U003','2025-03-03',1798000,'Processing'),
(4,'U001','2025-03-04',599000,'Cancelled'),
(5,'U004','2025-03-05',1299000,'Pending');

-- Order_Detail
INSERT INTO Order_Detail(order_detail_id,order_id,product_id,quantity,unit_price) VALUES
(1,1,'P001',2,199000),
(2,2,'P003',1,899000),
(3,3,'P002',1,399000),
(4,4,'P005',1,1299000),
(5,5,'P004',1,599000);

SELECT * FROM Orders;
SELECT * FROM User;
SELECT * FROM Order_Detail;
SELECT * FROM Product;

DELETE FROM Orders;
DELETE FROM User;
DELETE FROM Order_Detail;
DELETE FROM Product;

-- Cập nhật thông tin người dùng. Hãy viết câu lệnh cập nhật số điện thoại của người dùng có user_id = 'U003' thành "096532628".
UPDATE User
SET user_phone = '096532628'
WHERE user_id = 'U003';

-- Do khách hàng đã huỷ đơn hàng có  order_id = 3 bị huỷ, Hãy viết câu lệnh cập nhật order_status thành "Cancelled".
UPDATE Orders
SET order_status = 'Cancelled'
WHERE order_id = 3;

-- Viết câu lệnh xóa tất cả các bản ghi trong bảng Order có order_status là "Cancelled" và order_date trước ngày "2025-03-04".
DELETE FROM Orders
WHERE order_status = 'Cancelled' AND order_date < '2025-03-04';

-- PHẦN 2: Truy vấn dữ liệu cơ bản
-- Liệt kê danh sách các đơn hàng gồm các cột: order_id, order_date, order_status có trạng thái là 'Completed' và ngày đặt hàng sau ngày “2025-03-01”.
SELECT order_id,order_date,order_status
FROM Orders
WHERE order_status = 'Completed' AND order_date > '2025-03-01';

-- Lấy thông tin user_name, user_phone, user_email, của những người dùng có số điện thoại bắt đầu bằng “09”.
SELECT user_name,user_phone,user_email
FROM User
WHERE user_phone LIKE '09%';

-- Hiển thị danh sách tất cả các đơn hàng gồm: order_id, user_id, order_date. Kết quả sắp xếp theo (order_date) giảm dần.
SELECT order_id,user_id,order_date
FROM Orders
ORDER BY order_date DESC;

-- Lấy 3 bản ghi đầu tiên trong bảng Order có order_status là 'Completed'.
SELECT order_id,order_date,total_price,order_status
FROM Orders
WHERE order_status = 'Completed'
LIMIT 3;

-- Hiển thị thông tin gồm mã người dùng (user_id) và tên người dùng (user_name) từ bảng User, bỏ qua 2 bản ghi đầu tiên và lấy 3 bản ghi tiếp theo (sử dụng LIMIT và OFFSET).
SELECT user_id,user_name
FROM User
LIMIT 3 OFFSET 2;


-- PHẦN 3: Truy vấn dữ liệu nâng cao
-- Hiển thị danh sách đơn hàng gồm: order_id, user_name (từ bảng User), order_date và total_price. Chỉ lấy những đơn hàng có order_status = 'Completed'.
SELECT order_id,user_name,order_date,total_price
FROM Orders o
JOIN User u ON u.user_id = o.user_id
WHERE o.order_status = 'Completed';

-- Liệt kê tất cả các sản phẩm trong hệ thống gồm: product_id, product_name và order_id tương ứng (nếu có). Kết quả phải bao gồm cả những sản phẩm chưa từng được bán.
SELECT p.product_id,p.product_name,oi.order_id
FROM Product p
LEFT JOIN Order_Detail oi ON oi.product_id = p.product_id;

-- Tính tổng số đơn hàng theo từng (order_status). Kết quả hiển thị 2 cột: order_status và Total_Order.
SELECT o.order_status,COUNT(order_id) Total_Order
FROM Orders o
GROUP BY order_status;

-- Thống kê số lượng đơn hàng của mỗi người dùng. Hiển thị user_id và Count_Order. Chỉ hiện những người dùng có từ 2 đơn hàng trở lên.
SELECT u.user_id,COUNT(o.user_id) Count_Order
FROM Orders o
JOIN User u ON u.user_id = o.user_id
GROUP BY user_id
HAVING COUNT(o.user_id) >= 2;

-- Lấy thông tin các đơn hàng gồm: (order_id, order_date, total_price) có total_price lớn hơn giá trị trung bình của tất cả các đơn hàng trong bảng Order.
SELECT order_id,order_date,total_price
FROM Orders
WHERE total_price > (
	SELECT AVG(total_price)
	FROM Orders
);

-- Hiển thị user_name và user_phone ủa những người dùng đã từng mua sản phẩm có product_name là “Giày sneaker”.
-- Gợi ý: Truy vấn product_id từ bảng Product kết hợp với Order_Detail và Order để lấy danh sách user_id.
SELECT u.user_name, u.user_phone
FROM Product p
JOIN Order_Detail od ON od.product_id = p.product_id
JOIN Orders o ON o.order_id = od.order_id
JOIN User u ON u.user_id = o.user_id
WHERE p.product_name = 'Giày sneaker';

-- Hiển thị thông tin tổng hợp gồm: order_id, user_name, product_name, quantity và unit_price.
-- Gợi ý : Yêu cầu cần kết hợp dữ liệu từ cả 4 bảng: User, Order, Order_Detail, Product.
SELECT o.order_id, u.user_name, p.product_name, od.quantity, od.unit_price
FROM Orders o
JOIN User u ON u.user_id = o.user_id
JOIN Order_Detail od ON od.order_id = o.order_id
JOIN Product p ON p.product_id = od.product_id;

