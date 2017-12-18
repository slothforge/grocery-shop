-- Sale table

DROP INDEX IF EXISTS i_sale__product_id;
DROP TABLE IF EXISTS sale;

-- Payment table

DROP TABLE IF EXISTS payment;

-- Customer table

DROP TABLE IF EXISTS customer;

-- Supply table

DROP TABLE IF EXISTS supply;

-- Product to Group table

DROP INDEX IF EXISTS i_product_to_group__product_group_id;
DROP TABLE IF EXISTS product_to_group;

-- Product table

DROP TABLE IF EXISTS product;

-- Product Group table

DROP TABLE IF EXISTS product_group;
