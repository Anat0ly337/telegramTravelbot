CREATE SCHEMA telegrambot;
SET SEARCH_PATH = telegrambot;

DROP TABLE IF EXISTS city;
CREATE TABLE IF NOT EXISTS city
(
    id     BIGSERIAL PRIMARY KEY,
    name   VARCHAR(64) NOT NULL
);

INSERT INTO city
(id, name)
VALUES (1, 'minsk'),
       (2, 'moscow'),
       (3, 'newyork'),
       (4, 'paris');

DROP TABLE IF EXISTS cityinfo;
CREATE TABLE IF NOT EXISTS cityinfo
(
    id             BIGSERIAL PRIMARY KEY,
    name           VARCHAR(128) NOT NULL,
    description varchar(256),
    city_id BIGSERIAL,
    FOREIGN KEY (city_id) REFERENCES city (id) ON DELETE CASCADE
);


INSERT INTO cityinfo
    (id, name,description,city_id)
VALUES (1,'библиоткека', 'Национальная библиотека является главным достоянием РБ, находится по проспекту Независимости',1),
       (2,'парк победы', 'Парк победы, самый красивый и чистый парк в Минске',1),
       (3, 'красная площадь','Красная Площадь, тут похоронен дедушка Ленин',2),
       (4, 'москоу сити','Москоу сити, очень крутая застройка',2),
       (5,'5 авеню', '5 авеню, самая длинная улица в городе',2),
       (6, 'всемирный торговый центр','тут заключаются сделки на 10^1000000млрд долларов',3),
       (7, 'эфелева башня','национальная достопримечательность, супервысокая и красивая',4),
       (8, 'шабаны','здесь легко потеряться :)',1);