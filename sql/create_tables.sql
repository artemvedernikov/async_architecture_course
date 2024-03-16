CREATE TABLE IF NOT EXISTS accounts(
    id SERIAL PRIMARY KEY,
    public_id UUID NOT NULL,
    name VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    role VARCHAR NOT NULL,
    active BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS tasks(
    id SERIAL PRIMARY KEY,
    public_id UUID NOT NULL,
    title VARCHAR NOT NULL,
    jira_id VARCHAR,
    assignee_id UUID NOT NULL,
    done BOOLEAN NOT NULL,
    CONSTRAINT fk_assignee
        FOREIGN KEY(assignee_id)
            REFERENCES accounts(public_id)
)

CREATE TABLE IF NOT EXISTS accounts_billing(
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    public_id UUID NOT NULL,
    email VARCHAR NOT NULL,
    role VARCHAR NOT NULL,
    active BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS tasks_billing(
    id SERIAL PRIMARY KEY,
    public_id UUID NOT NULL,
    title VARCHAR NOT NULL,
    jira_id VARCHAR,
    assignee_id UUID NOT NULL,
    done BOOLEAN NOT NULL,
    price DOUBLE NOT NULL,
    CONSTRAINT fk_assignee
        FOREIGN KEY(assignee_id)
            REFERENCES accounts(public_id)
)
