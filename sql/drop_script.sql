-- Sale table

DROP INDEX IF EXISTS i_sale__product_id;
DROP TABLE IF EXISTS sale;

-- Payment table

DROP TABLE IF EXISTS payment;

-- Supply table

DROP TABLE IF EXISTS supply;

-- Special Offer to Product table

DROP INDEX IF EXISTS i_special_offer_to_product__product_id;
DROP TABLE IF EXISTS special_offer_to_product;

-- Special Offer table

DROP TABLE IF EXISTS special_offer;

-- Cart table

DROP TABLE IF EXISTS cart;

-- User table

DROP TABLE IF EXISTS "user";

-- Product to Group table

DROP INDEX IF EXISTS i_product_to_group__product_group_id;
DROP TABLE IF EXISTS product_to_group;

-- Product table

DROP TABLE IF EXISTS product;

-- Product Group table

DROP TABLE IF EXISTS product_group;
