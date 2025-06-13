DROP TABLE IF EXISTS KINGDOMS;

CREATE TABLE KINGDOMS (
                          id VARCHAR(255) PRIMARY KEY,
                          dateOfCreation DATE,
                          gold INT NOT NULL,
                          citizens INT NOT NULL,
                          food INT NOT NULL
);
