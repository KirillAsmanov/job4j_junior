-- 1. Написать запрос получение всех продуктов с типом "СЫР"

SELECT * FROM product 
INNER JOIN type ON product.type_id = type.id 
WHERE type.name = 'СЫР' 


-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"

SELECT * FROM product 
WHERE product.name LIKE 'мороженное'


-- 3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.

SELECT * FROM product 
WHERE product.expired_date BETWEEN now() AND ADDDATE(now(), Interval 1 MONTH)


-- 4. Написать запрос, который выводит самый дорогой продукт.

SELECT *
FROM product
WHERE price = (SELECT MAX(price) FROM product)

-- 5. Написать запрос, который выводит количество всех продуктов определенного типа.

SELECT COUNT(*) AS cheese_count FROM product 
INNER JOIN type ON product.type_id = type.id 
WHERE type.name = 'СЫР' 


-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"

SELECT * FROM product 
INNER JOIN type ON product.type_id = type.id 
WHERE type.name = 'СЫР' OR type.name = 'МОЛОКО'


-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.  

SELECT type.name AS tname, COUNT(*) AS type_count FROM product 
INNER JOIN type ON product.type_id = type.id 
GROUP BY type.name
HAVING COUNT(*) < 10  


-- 8. Вывести все продукты и их тип.

SELECT * FROM product 
INNER JOIN type ON product.type_id = type.id 

