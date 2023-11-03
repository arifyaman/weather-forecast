CREATE
    EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create the forecast table
CREATE TABLE forecast
(
    id          UUID      DEFAULT uuid_generate_v4() NOT NULL,
    date        DATE,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    modified_at TIMESTAMP,
    PRIMARY KEY (id)
);

-- Create the forecast_statement table
CREATE TABLE forecast_statement
(
    id              UUID      DEFAULT uuid_generate_v4() NOT NULL,
    forecast_id     UUID,
    type            VARCHAR(50),
    phenomenon      VARCHAR(255),
    statement       TEXT,
    min_temperature INTEGER,
    max_temperature INTEGER,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    modified_at     TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (forecast_id) REFERENCES forecast (id),
    CONSTRAINT uk_forecast_and_type_id UNIQUE (forecast_id, type)
);

-- Create an index on the 'type' column
CREATE INDEX idx_forecast_statement_type ON forecast_statement (type);

-- Create the place table
CREATE TABLE place
(
    id                    UUID      DEFAULT uuid_generate_v4() NOT NULL,
    forecast_statement_id UUID,
    name                  VARCHAR(255),
    phenomenon            VARCHAR(255),
    min_temperature       INTEGER,
    max_temperature       INTEGER,
    created_at            TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    modified_at           TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (forecast_statement_id) REFERENCES forecast_statement (id),
    CONSTRAINT uk_forecast_statement_and_name_id UNIQUE (forecast_statement_id, name)
);

-- Create an index on the 'name' column
CREATE INDEX idx_place_name ON place (name);