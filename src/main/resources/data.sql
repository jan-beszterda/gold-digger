DELETE
FROM items;

INSERT INTO items (category, item_name, strength, condition)
VALUES ('Pickaxe', 'Diamond pickaxe', 100, 100),
       ('Pickaxe', 'Iron pickaxe', 70, 100),
       ('Pickaxe', 'Aluminium pickaxe', 50, 100),
       ('Pickaxe', 'Wooden pickaxe', 20, 100),
       ('Pickaxe', 'Plastic pickaxe', 10, 100);

INSERT INTO items (category, item_name, health_effect, weight)
VALUES ('Food', 'Bread', 20, 20),
       ('Food', 'Meat', 50, 30),
       ('Food', 'Water', 10, 10),
       ('Food', 'Vegetables', 30, 20),
       ('Food', 'Wine', 40, 60);