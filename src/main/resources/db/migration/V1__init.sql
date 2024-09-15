CREATE TABLE event (
    event_id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL
);

CREATE TABLE theme (
    theme_id INTEGER PRIMARY KEY AUTOINCREMENT,
    description TEXT NOT NULL,
    date_for DATE NOT NULL,
    event_id INTEGER NOT NULL,
    FOREIGN KEY (event_id) REFERENCES event(event_id)
);

CREATE TABLE submission (
    submission_id INTEGER PRIMARY KEY AUTOINCREMENT,
    upload_timestamp INTEGER NOT NULL,
    comment TEXT,
    image BLOB,
    theme_id INTEGER NOT NULL,
    FOREIGN KEY (theme_id) REFERENCES theme(theme_id)
);
