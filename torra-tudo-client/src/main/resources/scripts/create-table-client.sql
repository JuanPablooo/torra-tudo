CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE IF NOT EXISTS client(
    id uuid DEFAULT uuid_generate_v4 (),
    name VARCHAR(200),
    document_number VARCHAR(200) NOT NULL,
    email VARCHAR(200),
    birthday DATE,
    createAt DATE,
    updateAt DATE,
    PRIMARY KEY (id)
);