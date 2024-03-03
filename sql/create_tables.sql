CREATE TYPE AS ENUM account_role('worker', 'manager', 'admin', 'lead')

CREATE TABLE IF NOT EXISTS accounts(
    id UUID NOT NULL,
    email VARCHAR NOT NULL,
    role account_role NOT NULL,
    active BOOLEAN NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tasks(
    id UUID NOT NULL,
    name VARCHAR NOT NULL,
    done BOOLEAN NOT NULL,
    PRIMARY KEY (id)
)
