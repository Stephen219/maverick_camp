create schema if not exists maverick;
use maverick;
DROP TABLE IF EXISTS bookings;
DROP TABLE IF EXISTS rooms;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS enquiries;


CREATE TABLE bookings (
  id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
  room_type VARCHAR(255) NOT NULL,
  bed_type VARCHAR(255)  default 'Single',
  check_in_date DATE NOT NULL,
  check_out_date DATE NOT NULL,
  adults INT NOT NULL,
    children INT NOT NULL,
  total_price DECIMAL(10,2) NOT NULL,
    meal_plan VARCHAR(255) NOT NULL,
    comments VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);
DROP TABLE IF EXISTS rooms;
CREATE TABLE rooms (
id INT NOT NULL AUTO_INCREMENT,
room_type VARCHAR(255) NOT NULL,
bed_type VARCHAR(255) NOT NULL,
number_of_rooms INT NOT NULL,
number_of_guests INT NOT NULL,
price DECIMAL(10,2) NOT NULL,
PRIMARY KEY (id)
);
drop table if exists events;

CREATE  TABLE IF NOT EXISTS events (
   id INT NOT NULL AUTO_INCREMENT,
   title VARCHAR(255) NOT NULL,
   start_date_time DATETIME NOT NULL,
   end_date_time DATETIME NOT NULL,
   itinerary Text NOT NULL,
    image VARCHAR(255) NOT NULL,
    cost DECIMAL(10,2) NOT NULL,
    location VARCHAR(255) NOT NULL,


   description VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

   PRIMARY KEY (id)
);


create Table if NOT EXISTS enquiries(
    Id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    PRIMARY KEY (id)
);
DROP TABLE IF EXISTS blogs;


create TABLE IF NOT EXISTS blogs(
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    content LONGTEXT NOT NULL,
    description VARCHAR(255) NOT NULL,
    date_created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    image VARCHAR(255) NOT NULL,

    PRIMARY KEY (id)
);


Drop TABLE IF EXISTS comments;

create TABLE IF NOT EXISTS comments(
    id INT NOT NULL AUTO_INCREMENT,
    blog_id INT NOT NULL,
    comment LONGTEXT NOT NULL,
    date_created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
drop table if exists gallery;

CREATE TABLE IF NOT EXISTS gallery(
    id INT NOT NULL AUTO_INCREMENT,
    image Text NOT NULL,
    PRIMARY KEY (id)
);

drop table if exists Event_Participants;

CREATE TABLE IF NOT EXISTS Event_Participants(
    id INT NOT NULL AUTO_INCREMENT,
    event_id INT NOT NULL,
    attendee VARCHAR(255) NOT NULL,
    mpesa_code VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    message_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (event_id) REFERENCES events(id),
    PRIMARY KEY (id)
);

-- adding is showing in events with default true
ALTER TABLE events ADD COLUMN is_showing BOOLEAN DEFAULT TRUE;
