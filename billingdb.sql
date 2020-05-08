create database billing;

\c billing

create table service (id serial primary key, name text, is_rated boolean, type text);

create table time_pkg (id serial primary key, name text ,start timestamp, finish timestamp, day text);

create table tarrif_zone (id serial primary key, name text, is_samenet boolean, is_local boolean, is_roaming boolean);

create table rate_plan (id serial primary key, name text, monthly_fees numeric);

create table svc_pkg (id serial primary key, rate_plan_id int, service_id int, time_id int, tarrif_id int, free_units int, rate numeric);

create table customer (id serial primary key, rate_plan_id int, name text, phone text, email text, address text, billing_date date,nid text);

create table cust_svc (cust_id int, service_id int);