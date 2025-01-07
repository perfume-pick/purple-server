TRUNCATE TABLE evaluation_statistic;

ALTER TABLE evaluation_statistic DROP PRIMARY KEY;

ALTER TABLE evaluation_statistic
    ADD CONSTRAINT pk_evaluation_statistic PRIMARY KEY (perfume_id, field_code, option_code);

ALTER TABLE evaluation_statistic DROP COLUMN statistics_date;