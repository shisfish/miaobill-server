-- 创建数据库
CREATE DATABASE IF NOT EXISTS miaobill DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE miaobill;

-- 创建记账记录表
CREATE TABLE IF NOT EXISTS record (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(10) NOT NULL COMMENT '类型：income 或 expense',
    amount DECIMAL(10,2) NOT NULL COMMENT '金额',
    category VARCHAR(50) NOT NULL COMMENT '分类',
    description VARCHAR(255) COMMENT '描述',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_type (type),
    INDEX idx_category (category),
    INDEX idx_create_time (create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='记账记录表';

-- 插入测试数据
INSERT INTO record (type, amount, category, description, create_time) VALUES
('income', 1000.00, '工资', '月工资', NOW()),
('expense', 200.00, '餐饮', '午餐', NOW()),
('expense', 50.00, '交通', '打车', NOW()),
('income', 500.00, '奖金', '项目奖金', NOW()),
('expense', 100.00, '购物', '日用品', NOW());
