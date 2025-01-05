-- Table: advertisement
CREATE TABLE ticketing_schema.advertisement
(
    id                 UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    created_date       TIMESTAMP    NOT NULL DEFAULT now(),
    last_modified_date TIMESTAMP    NOT NULL,
    created_by         UUID,
    last_modified_by   UUID,
    version            BIGINT                DEFAULT 0,
    is_deleted         BOOLEAN               DEFAULT FALSE,
    title              VARCHAR(64)  NOT NULL UNIQUE,
    description        VARCHAR(255) NOT NULL
);

-- Table: person
CREATE TABLE ticketing_schema.person
(
    id                 UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    created_date       TIMESTAMP    NOT NULL DEFAULT now(),
    last_modified_date TIMESTAMP    NOT NULL,
    created_by         UUID,
    last_modified_by   UUID,
    version            BIGINT                DEFAULT 0,
    is_deleted         BOOLEAN               DEFAULT FALSE,
    fullName           VARCHAR(255) NOT NULL UNIQUE,
    phone_primary      VARCHAR(15)  NOT NULL,
    phone_secondary    VARCHAR(15),
    email              VARCHAR(100),
    telegram           VARCHAR(200),
    advertisement      UUID REFERENCES ticketing_schema.advertisement (id),
    client_status      VARCHAR(128) NOT NULL,
    comment            VARCHAR(200)
);

-- Table: office
CREATE TABLE ticketing_schema.office
(
    id                 UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    created_date       TIMESTAMP    NOT NULL DEFAULT now(),
    last_modified_date TIMESTAMP    NOT NULL,
    created_by         UUID,
    last_modified_by   UUID,
    version            BIGINT                DEFAULT 0,
    is_deleted         BOOLEAN               DEFAULT FALSE,
    shortname          VARCHAR(5)   NOT NULL,
    name               VARCHAR(255) NOT NULL
);

-- Table: user
CREATE TABLE ticketing_schema.user
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name       VARCHAR(64) NOT NULL,
    is_working BOOLEAN          DEFAULT TRUE,
    person_id  UUID REFERENCES ticketing_schema.person (id),
    office_id  UUID REFERENCES ticketing_schema.office (id)
);

-- Table: ticket_stage
CREATE TABLE ticketing_schema.ticket_stage
(
    id                 UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    created_date       TIMESTAMP    NOT NULL DEFAULT now(),
    last_modified_date TIMESTAMP    NOT NULL,
    created_by         UUID,
    last_modified_by   UUID,
    version            BIGINT                DEFAULT 0,
    is_deleted         BOOLEAN               DEFAULT FALSE,
    title              VARCHAR(64)  NOT NULL UNIQUE,
    description        VARCHAR(255) NOT NULL
);

-- Table: ticket
CREATE TABLE ticketing_schema.ticket
(
    id                 UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_date       TIMESTAMP    NOT NULL DEFAULT now(),
    last_modified_date TIMESTAMP NOT NULL,
    created_by         UUID,
    last_modified_by   UUID,
    version            BIGINT           DEFAULT 0,
    is_deleted         BOOLEAN          DEFAULT FALSE,
    number             BIGINT    NOT NULL,
    year               INT       NOT NULL,
    created_at         TIMESTAMP,
    receiver_user      UUID REFERENCES ticketing_schema.user (id),
    executor_user      UUID REFERENCES ticketing_schema.user (id),
    person_id          UUID REFERENCES ticketing_schema.person (id),
    CONSTRAINT unique_ticket_year_number UNIQUE (year, number)
);

-- Table: ticket_stage_change
CREATE TABLE ticketing_schema.ticket_stage_change
(
    id                 UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_date       TIMESTAMP    NOT NULL DEFAULT now(),
    last_modified_date TIMESTAMP NOT NULL,
    created_by         UUID,
    last_modified_by   UUID,
    version            BIGINT           DEFAULT 0,
    is_deleted         BOOLEAN          DEFAULT FALSE,
    ticket_stage_id    UUID REFERENCES ticketing_schema.ticket_stage (id),
    ticket_id          UUID REFERENCES ticketing_schema.ticket (id),
    user_id            UUID REFERENCES ticketing_schema.user (id)
);

-- Table: note
CREATE TABLE ticketing_schema.note
(
    id                 UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_date       TIMESTAMP    NOT NULL DEFAULT now(),
    last_modified_date TIMESTAMP NOT NULL,
    created_by         UUID,
    last_modified_by   UUID,
    version            BIGINT           DEFAULT 0,
    is_deleted         BOOLEAN          DEFAULT FALSE,
    data               VARCHAR(1024),
    ticket_id          UUID REFERENCES ticketing_schema.ticket (id),
    user_id            UUID REFERENCES ticketing_schema.user (id)
);
