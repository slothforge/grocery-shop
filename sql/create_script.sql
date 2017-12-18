-- Product Group table

CREATE TABLE product_group (
  id          BIGSERIAL NOT NULL PRIMARY KEY,
  name        TEXT      NOT NULL,
  description TEXT      NOT NULL DEFAULT ''
);

-- Product table

CREATE TABLE product (
  id             BIGSERIAL NOT NULL PRIMARY KEY,
  name           TEXT      NOT NULL,
  unit           TEXT      NOT NULL,
  price_per_unit REAL      NOT NULL
);

-- Product to Group table

CREATE TABLE product_to_group (
  product_id       BIGSERIAL NOT NULL REFERENCES product (id),
  product_group_id BIGSERIAL NOT NULL REFERENCES product_group (id),
  CONSTRAINT product_to_group_pk PRIMARY KEY (product_id, product_group_id)
);

CREATE INDEX i_product_to_group__product_group_id
  ON product_to_group (product_group_id);

-- Supply table

CREATE TABLE supply (
  id           BIGSERIAL                   NOT NULL PRIMARY KEY,
  supply_date  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  product_id   BIGINT                      NOT NULL REFERENCES product (id),
  supply_price REAL                        NOT NULL,
  quantity     INT                         NOT NULL DEFAULT 1
);

-- Customer table

CREATE TABLE customer (
  id         BIGSERIAL NOT NULL PRIMARY KEY,
  first_name TEXT      NOT NULL,
  last_name  TEXT      NOT NULL
);

-- Payment table

CREATE TABLE payment (
  id           BIGSERIAL                   NOT NULL PRIMARY KEY,
  customer_id  BIGINT                      NOT NULL REFERENCES customer (id),
  payment_date TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

-- Sale table

CREATE TABLE sale (
  payment_id BIGINT NOT NULL REFERENCES payment (id),
  product_id BIGINT NOT NULL REFERENCES product (id),
  sale_price REAL   NOT NULL,
  quanitity  INT    NOT NULL,
  CONSTRAINT sales_pk PRIMARY KEY (payment_id, product_id)
);

CREATE INDEX i_sale__product_id
  ON sale (product_id);