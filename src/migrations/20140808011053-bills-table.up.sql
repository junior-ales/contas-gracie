CREATE TABLE IF NOT EXISTS bills (
  ID INTEGER PRIMARY KEY AUTOINCREMENT,
  NAME TEXT NOT NULL,
  AMOUNT REAL NOT NULL,
  PAID INTEGER NOT NULL DEFAULT 0,
  DUE_DATE TIMESTAMP NOT NULL,
  CREATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);