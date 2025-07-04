-- Create hotel table
CREATE TABLE hotels (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    hotel_name VARCHAR(255),
    hotel_rating DECIMAL(2,1),
    city VARCHAR(100),
    feature_1 VARCHAR(100),
    feature_2 VARCHAR(100),
    feature_3 VARCHAR(100),
    feature_4 VARCHAR(100),
    feature_5 VARCHAR(100),
    feature_6 VARCHAR(100),
    feature_7 VARCHAR(100),
    feature_8 VARCHAR(100),
    feature_9 VARCHAR(100),
    hotel_price DECIMAL(10,2)
);

-- Insert 10 hotel entries (id auto-increment; do not provide id in insert)
INSERT INTO hotels (
    hotel_name, hotel_rating, city,
    feature_1, feature_2, feature_3, feature_4, feature_5, feature_6, feature_7, feature_8, feature_9,
    hotel_price
) VALUES
('Crowne Plaza', 4.6, 'Chandigarh', '5-star hotel', 'Free breakfast', 'Free Wi-Fi', 'Free parking', 'Pool', 'Hot tub', 'Air conditioning', 'Fitness center', 'Spa', 8854.0),
('Trident Hotel Cochin', 4.5, 'kochi', '5-star hotel', 'Free breakfast', 'Wi-Fi', 'Free parking', 'Pool', 'Air conditioning', 'Fitness center', 'Spa', 'Restaurant', 6441.0),
('The Galaxy Suites', 3.8, 'Mohali', 'Apartment', 'Sleeps 10', 'Free parking', 'Free Wi-Fi', 'No air conditioning', 'No airport shuttle', 'No beach access', 'No elevator', 'No fireplace', 831.0),
('The Renai cochin', 4.2, 'kochi', '4-star hotel', 'Free breakfast', 'Free Wi-Fi', 'Free parking', 'Pool', 'Air conditioning', 'Fitness center', 'Spa', 'Bar', 2768.0),
('Ramada by Wyndham Kochi', 4.5, 'kochi', '5-star hotel', 'Breakfast', 'Free Wi-Fi', 'Free parking', 'Pool', 'Air conditioning', 'Fitness center', 'Spa', 'Bar', 8938.0),
('Radisson Blu Hotel', 4.3, 'New Delhi', '5-star hotel', 'Breakfast', 'Free Wi-Fi', 'Free parking', 'Pool', 'Hot tub', 'Air conditioning', 'Fitness center', 'Spa', 6061.0),
('Holiday Inn, an IHG Hotel', 4.4, 'Mumbai', '5-star hotel', 'Breakfast', 'Free Wi-Fi', 'Free parking', 'Pool', 'Air conditioning', 'Fitness center', 'Bar', 'Restaurant', 5689.0),
('OAK FIELD INN', 3.8, 'Shimla', 'Free breakfast', 'Wi-Fi', 'Free parking', 'Air conditioning', 'Restaurant', 'Kitchen', 'Full-service laundry', 'Kid-friendly', NULL, 819.0),
('Grand Hyatt', 4.7, 'Chennai', '5-star hotel', 'Breakfast', 'Free Wi-Fi', 'Free parking', 'Pool', 'Hot tub', 'Air conditioning', 'Fitness center', 'Spa', 14282.0),
('Hotel South Gate Residency', 3.9, 'Bangalore', '3-star hotel', 'Breakfast', 'Free Wi-Fi', 'Free parking', 'Air conditioning', 'Restaurant', 'Airport shuttle', 'Full-service laundry', 'Kid-friendly', 1051.0);

-- Create users table based on Users model class
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    full_name VARCHAR(100),
    phone_number VARCHAR(20),
    address VARCHAR(255),
    created_at TIMESTAMP
);

-- Insert 5 demo users (do NOT include id column, it is auto-generated)
INSERT INTO users (email, full_name, phone_number, address, created_at) VALUES
('alice@example.com', 'Alice Johnson', '1234567890', '123 Main St, Kochi', CURRENT_TIMESTAMP),
('bob@example.com', 'Bob Smith', '0987654321', '456 Park Ave, Trivandrum', CURRENT_TIMESTAMP),
('charlie@example.com', 'Charlie Lee', '1122334455', '789 Beach Rd, Goa', CURRENT_TIMESTAMP),
('david@example.com', 'David Brown', '2233445566', '321 Lakeview, Pune', CURRENT_TIMESTAMP),
('eva@example.com', 'Eva Green', '3344556677', '654 Riverfront, Chennai', CURRENT_TIMESTAMP);