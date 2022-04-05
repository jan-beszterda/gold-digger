DELETE FROM items WHERE backpack_id IS NULL AND item_id NOT IN (SELECT pickaxe_id FROM players);
DELETE FROM mines WHERE mine_id NOT IN (SELECT mine_id FROM players);
DELETE FROM shops;

INSERT INTO shops (shop_name)
VALUES ('Diggers'' den');

INSERT INTO items (category, item_name, strength, condition, item_price, shop_id)
VALUES ('Pickaxe', 'Diamond pickaxe', 2.3, 100, 2000.0, (SELECT shop_id FROM shops)),
       ('Pickaxe', 'Iron pickaxe', 1.5, 100, 300.0, (SELECT shop_id FROM shops)),
       ('Pickaxe', 'Aluminium pickaxe', 0.9, 100, 90.0, (SELECT shop_id FROM shops)),
       ('Pickaxe', 'Wooden pickaxe', 1.0, 100, 100.0, (SELECT shop_id FROM shops)),
       ('Pickaxe', 'Plastic pickaxe', 0.8, 100, 75.0, (SELECT shop_id FROM shops)),
       ('Pickaxe', 'Golden pickaxe', 2.0, 100, 1000.0, (SELECT shop_id FROM shops)),
       ('Pickaxe', 'Silver pickaxe', 1.8, 100, 700.0, (SELECT shop_id FROM shops)),
       ('Pickaxe', 'Bronze pickaxe', 1.1, 100, 150.0, (SELECT shop_id FROM shops)),
       ('Pickaxe', 'Steel pickaxe', 1.7, 100, 500.0, (SELECT shop_id FROM shops)),
       ('Pickaxe', 'Stone pickaxe', 1.3, 100, 200.0, (SELECT shop_id FROM shops));

INSERT INTO items (category, item_name, health_effect, weight, item_price, shop_id)
VALUES ('Food', 'Bread', 7, 0.5, 50.0, (SELECT shop_id FROM shops)),
       ('Food', 'Raw meat', 15, 1.0, 200.0, (SELECT shop_id FROM shops)),
       ('Food', 'Water', 2, 0.5, 10.0, (SELECT shop_id FROM shops)),
       ('Food', 'Apple', 3, 0.3, 15.0, (SELECT shop_id FROM shops)),
       ('Food', 'Cheese block', 10, 0.7, 100.0, (SELECT shop_id FROM shops)),
       ('Food', 'Steak', 20, 0.4, 500.0, (SELECT shop_id FROM shops)),
       ('Food', 'Potatoes', 6.5, 0.4, 25.0, (SELECT shop_id FROM shops)),
       ('Food', 'Stew', 16.5, 0.8, 250.0, (SELECT shop_id FROM shops)),
       ('Food', 'Grilled fish', 12, 0.4, 150.0, (SELECT shop_id FROM shops)),
       ('Food', 'Pasta with meatballs', 18, 0.6, 300.0, (SELECT shop_id FROM shops));

INSERT INTO mines (difficulty, mine_name, total_gold)
VALUES (0.3, 'Mine in the woods', 1000.0),
       (0.6, 'Desserted mine', 3500.0),
       (0.8, 'Ghost mine', 10000.0),
       (0.2, 'Shallow mine', 500.0),
       (0.5, 'Mountain den', 2000.0);