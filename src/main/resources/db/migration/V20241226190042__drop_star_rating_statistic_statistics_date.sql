TRUNCATE TABLE star_rating_statistic;

ALTER TABLE star_rating_statistic DROP PRIMARY KEY;

ALTER TABLE star_rating_statistic
    ADD CONSTRAINT pk_star_rating_statistic PRIMARY KEY (perfume_id, score);

ALTER TABLE star_rating_statistic DROP COLUMN statistics_date;