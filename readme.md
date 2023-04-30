# Spring REST API Project

The Personal Journal API is a RESTful web service that provides a platform for users to create, manage and maintain
their personal journal entries. The API is designed to allow users to easily register, login, and perform CRUD
operations on their journal entries, tags, and comments.

The API provides a simple and intuitive interface for users to interact with their personal journals, allowing them to
easily create, view, update, and delete journal entries, tags, and comments. Users can also view other users' journal
entries by searching for them through their username.

The Personal Journal API is built on the OpenAPI 3.0.3 specification and uses HTTPS for secure communication. It
provides a base URL for easy access to its various endpoints.

The project aims to provide a reliable, scalable, and user-friendly platform for personal journaling. It is suitable for
individuals who want to maintain a personal journal or for organizations that want to provide a journaling platform for
their users.

Frontend is available at https://github.com/b14ck0ps/personaljournal-frontend using NextJS.

## Database

```mysql
CREATE TABLE User
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    name     VARCHAR(255),
    address  VARCHAR(255),
    email    VARCHAR(255) UNIQUE,
    image    VARCHAR(255)
);

CREATE TABLE Journal_Entry
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    title      VARCHAR(255),
    body       TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id    INT,
    FOREIGN KEY (user_id) REFERENCES User (id)
);

CREATE TABLE Tags
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) UNIQUE
);

CREATE TABLE Journal_Entry_Tags
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    journal_entry_id INT,
    tag_id           INT,
    FOREIGN KEY (journal_entry_id) REFERENCES Journal_Entry (id),
    FOREIGN KEY (tag_id) REFERENCES Tags (id)
);
CREATE TABLE Journal_Entry_Comments
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    body             TEXT,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id          INT,
    journal_entry_id INT,
    FOREIGN KEY (user_id) REFERENCES User (id),
    FOREIGN KEY (journal_entry_id) REFERENCES Journal_Entry (id)
);
ALTER TABLE `journal_entry`
    ADD COLUMN `isDeleted` BOOLEAN NOT NULL DEFAULT false;
```

## Usage

Once the project is running, you can access the API at `http://localhost:8080/`.

The API supports the following endpoints:

## User Endpoints

### `PUT /api/user`

Updates user information.

### `GET /api/user`

Returns user information.

### `DELETE /api/user/{id}`

Deletes a user by ID.

### `GET /api/user/{username}`

Returns user information by username.

### `POST /guest/register`

Registers a new guest user.

### `POST /guest/login`

Logs in a guest user.

## Journal Entry Tag Endpoints

### `POST /api/journalEntryTag`

Creates a new journal entry tag.

### `DELETE /api/journalEntryTag`

Deletes a journal entry tag.

### `GET /api/journalEntryTag/{id}`

Returns a journal entry tag by ID.

### `GET /api/journalEntryTags`

Returns a list of all journal entry tags.

## Tag Endpoints

### `GET /api/tags`

Returns a list of all tags.

### `GET /api/tags/{id}`

Returns a tag by ID.

## Journal Entry Endpoints

### `PUT /api/journalEntry`

Updates a journal entry.

### `POST /api/journalEntry`

Creates a new journal entry.

### `GET /api/journalEntry`

Returns a list of all journal entries.

### `DELETE /api/journalEntry/{id}`

Deletes a journal entry by ID.

### `GET /api/journalEntry/{id}`

Returns a journal entry by ID.

### `GET /api/journalEntry/username/{username}`

Returns a list of journal entries by username.

## Journal Comment Endpoints

### `PUT /api/journal-comments`

Updates a journal comment.

### `POST /api/journal-comments`

Creates a new journal comment.

### `GET /api/journal-comments`

Returns a list of all journal comments.

### `DELETE /api/journal-comments/{id}`

Deletes a journal comment by ID.

### `GET /api/journal-comments/{journalId}`

Returns a list of journal comments by journal entry ID.

## Dependencies

This project uses the following dependencies:

- `spring-context` (version 5.3.21)
- `spring-webmvc` (version 5.3.21)
- `spring-orm` (version 5.3.21)
- `spring-tx` (version 5.3.21)
- `mysql-connector-java` (version 8.0.32)
- `hibernate-validator` (version 6.2.3.Final)
- `hibernate-core` (version 5.6.12.Final)
- `log4j` (version 1.2.17)
- `jackson-databind` (version 2.13.4.1)
- `jstl-impl` (version 1.2)
- `spring-security-web` (version 5.7.2)
- `spring-security-config` (version 5.7.2)
- `junit` (version 3.8.1)
- `jackson-datatype-jsr310` (version 2.13.4)

### Schema

![Schema](https://i.imgur.com/tPXzBUv.png)