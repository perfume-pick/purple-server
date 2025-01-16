-- 1. datetime 컬럼 정보 확인
-- SELECT TABLE_NAME, COLUMN_NAME, DATA_TYPE
-- FROM INFORMATION_SCHEMA.COLUMNS
-- WHERE TABLE_SCHEMA = 'purple' AND DATA_TYPE = 'datetime';

-- 2-1. 컬럼 타입 변경 SQL 생성
# SELECT
#     CONCAT(
#             'ALTER TABLE `', TABLE_NAME, '` MODIFY COLUMN `', COLUMN_NAME,
#             '` TIMESTAMP NOT NULL;'
#     ) AS alter_statement
# FROM INFORMATION_SCHEMA.COLUMNS
# WHERE TABLE_SCHEMA = 'purple' AND DATA_TYPE = 'datetime';

-- 2-2. 컬럼 타입 변경 SQL 생성 (+ 시차 적용)
-- SELECT CONCAT(
--                'ALTER TABLE `', TABLE_NAME, '` ADD COLUMN `temp_', COLUMN_NAME, '` TIMESTAMP NULL; ',
--                'UPDATE `', TABLE_NAME, '` SET `temp_', COLUMN_NAME, '` = CONVERT_TZ(`', COLUMN_NAME, '`, \'UTC\', \'SYSTEM\'); ',
--                'ALTER TABLE `', TABLE_NAME, '` DROP COLUMN `', COLUMN_NAME, '`; ',
--                'ALTER TABLE `', TABLE_NAME, '` CHANGE COLUMN `temp_', COLUMN_NAME, '` `', COLUMN_NAME, '` TIMESTAMP NOT NULL;'
--        ) AS alter_queries
-- FROM INFORMATION_SCHEMA.COLUMNS
-- WHERE TABLE_SCHEMA = 'purple'
--   AND DATA_TYPE = 'datetime';

-- 3. 컬럼 타입 변경 수행
ALTER TABLE `complaint` MODIFY COLUMN `created_at` TIMESTAMP NOT NULL;
ALTER TABLE `complaint` MODIFY COLUMN `updated_at` TIMESTAMP NOT NULL;
ALTER TABLE `evaluation_statistic` MODIFY COLUMN `created_at` TIMESTAMP NOT NULL;
ALTER TABLE `evaluation_statistic` MODIFY COLUMN `updated_at` TIMESTAMP NOT NULL;
ALTER TABLE `likes` MODIFY COLUMN `created_at` TIMESTAMP NOT NULL;
ALTER TABLE `likes` MODIFY COLUMN `updated_at` TIMESTAMP NOT NULL;
ALTER TABLE `review` MODIFY COLUMN `created_at` TIMESTAMP NOT NULL;
ALTER TABLE `review` MODIFY COLUMN `updated_at` TIMESTAMP NOT NULL;
ALTER TABLE `review_evaluation` MODIFY COLUMN `created_at` TIMESTAMP NOT NULL;
ALTER TABLE `review_evaluation` MODIFY COLUMN `updated_at` TIMESTAMP NOT NULL;
ALTER TABLE `review_mood` MODIFY COLUMN `created_at` TIMESTAMP NOT NULL;
ALTER TABLE `review_mood` MODIFY COLUMN `updated_at` TIMESTAMP NOT NULL;
ALTER TABLE `star_rating` MODIFY COLUMN `created_at` TIMESTAMP NOT NULL;
ALTER TABLE `star_rating` MODIFY COLUMN `updated_at` TIMESTAMP NOT NULL;
ALTER TABLE `star_rating_statistic` MODIFY COLUMN `created_at` TIMESTAMP NOT NULL;
ALTER TABLE `star_rating_statistic` MODIFY COLUMN `updated_at` TIMESTAMP NOT NULL;
ALTER TABLE `user` MODIFY COLUMN `created_at` TIMESTAMP NOT NULL;
ALTER TABLE `user` MODIFY COLUMN `updated_at` TIMESTAMP NOT NULL;

-- 4. 수행 결과 확인
-- SELECT TABLE_NAME, COLUMN_NAME, DATA_TYPE
-- FROM INFORMATION_SCHEMA.COLUMNS
-- WHERE TABLE_SCHEMA = 'purple' AND DATA_TYPE = 'timestamp';