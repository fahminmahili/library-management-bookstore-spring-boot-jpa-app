INSERT INTO AUTHORS (first_name, last_name, email, gender)
VALUES ('Agatha', 'Christie', 'agatha.christie@gmail.com', 'Female'),
       ('J.K.', 'Rowling', 'jk.rowling@gmail.com', 'Female'),
       ('Dan', 'Brown', 'dan.brown@gmail.com', 'Male');

INSERT INTO BOOKS (title, isbn, description, category)
VALUES ('Murder on the Orient Express', '9780062073501',
        'Agatha Christie',
        'Mystery'),
       ('Harry Potter and the Philosopher''s Stone', '9780747532743',
        'Harry Potter is a wizard',
        'Fantasy'),
       ('The Da Vinci Code', '9780307474278',
        'While in Paris',
        'Thriller');

INSERT INTO BOOK_AUTHOR (book_id, author_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);