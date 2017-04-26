CREATE TABLE IF NOT EXISTS category (
	category_id         BIGSERIAL PRIMARY KEY,
	category_name       VARCHAR(250), 
	category_class	 	VARCHAR(250),
	category_subclass   VARCHAR(250), 
	category_code	 	VARCHAR(20),
	description		 	VARCHAR(250)
);

CREATE TABLE IF NOT EXISTS truck_plan(
    truck_plan_id BIGSERIAL PRIMARY KEY,
    plan_date TIMESTAMP,
    start_place VARCHAR(250),
    destination VARCHAR(250),
    truck_name VARCHAR(50)
);

