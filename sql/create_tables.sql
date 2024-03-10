CREATE TABLE IF NOT EXISTS accounts(
    id UUID NOT NULL,
    email VARCHAR NOT NULL,
    role VARCHAR NOT NULL,
    active BOOLEAN NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tasks(
    id UUID NOT NULL,
    name VARCHAR NOT NULL,
    assignee_id UUID NOT NULL,
    done BOOLEAN NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_assignee
        FOREIGN KEY(assignee_id)
            REFERENCES accounts(id)
)
