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

-- Product to Product Group table

CREATE TABLE product_to_group (
  product_id       BIGINT NOT NULL REFERENCES product (id),
  product_group_id BIGINT NOT NULL REFERENCES product_group (id),
  CONSTRAINT product_to_group_pk PRIMARY KEY (product_id, product_group_id)
);

CREATE INDEX i_product_to_group__product_group_id
  ON product_to_group (product_group_id);

-- User table

CREATE TABLE "user" (
  id            BIGSERIAL NOT NULL PRIMARY KEY,
  email         TEXT      NOT NULL UNIQUE,
  password_hash INT       NOT NULL, -- TODO specify type
  real_name     TEXT      NOT NULL,
  role          TEXT      NOT NULL
);

-- Cart table

CREATE TABLE cart (
  id          BIGSERIAL NOT NULL PRIMARY KEY,
  customer_id BIGINT    NOT NULL REFERENCES "user" (id),
  product_id  BIGINT    NOT NULL REFERENCES product (id),
  quantity    INT       NOT NULL,
  UNIQUE (customer_id, product_id)
);

-- Special Offer table

CREATE TABLE special_offer (
  id          BIGSERIAL                NOT NULL PRIMARY KEY,
  name        TEXT                     NOT NULL,
  priority    INT                      NOT NULL,
  start_date  TIMESTAMP WITH TIME ZONE NOT NULL, -- TODO take into account "with time zone"
  ending_date TIMESTAMP WITH TIME ZONE NOT NULL
);

-- Special Offer to Product table

CREATE TABLE special_offer_to_product (
  special_offer_id BIGINT NOT NULL REFERENCES special_offer (id),
  product_id       BIGINT NOT NULL REFERENCES product (id),
  CONSTRAINT special_offer_to_product_pk PRIMARY KEY (special_offer_id, product_id)
);

CREATE INDEX i_special_offer_to_product__product_id
  ON special_offer_to_product (product_id);

-- Supply table

CREATE TABLE supply (
  id           BIGSERIAL                   NOT NULL PRIMARY KEY,
  supply_date  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  product_id   BIGINT                      NOT NULL REFERENCES product (id),
  supply_price REAL                        NOT NULL,
  quantity     INT                         NOT NULL DEFAULT 1
);

-- Payment table

CREATE TABLE payment (
  id           BIGSERIAL                   NOT NULL PRIMARY KEY,
  customer_id  BIGINT                      NOT NULL REFERENCES "user" (id),
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