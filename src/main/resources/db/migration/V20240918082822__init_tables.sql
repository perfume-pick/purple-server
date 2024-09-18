CREATE TABLE accord
(
    accord_name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_accord PRIMARY KEY (accord_name)
);

CREATE TABLE brand
(
    brand_name    VARCHAR(255) NOT NULL,
    image_url     VARCHAR(255) NULL,
    display_order INT          NULL,
    CONSTRAINT pk_brand PRIMARY KEY (brand_name)
);

CREATE TABLE complaint
(
    review_id  BIGINT   NOT NULL,
    user_id    BIGINT   NOT NULL,
    is_active  BIT(1)   NOT NULL,
    created_at datetime NOT NULL,
    updated_at datetime NOT NULL,
    CONSTRAINT pk_complaint PRIMARY KEY (review_id, user_id)
);

CREATE TABLE evaluation_statistic
(
    statistics_date CHAR(8)       NOT NULL,
    perfume_id      BIGINT        NOT NULL,
    field_code      VARCHAR(255)  NOT NULL,
    option_code     VARCHAR(255)  NOT NULL,
    votes           INT DEFAULT 0 NOT NULL,
    is_active       BIT(1)        NOT NULL,
    created_at      datetime      NOT NULL,
    updated_at      datetime      NOT NULL,
    CONSTRAINT pk_evaluation_statistic PRIMARY KEY (statistics_date, perfume_id, field_code, option_code)
);

CREATE TABLE fragrantica_evaluation
(
    perfume_id  BIGINT        NOT NULL,
    field_code  CHAR(5)       NOT NULL,
    option_code CHAR(5)       NOT NULL,
    votes       INT DEFAULT 0 NOT NULL,
    CONSTRAINT pk_fragrantica_evaluation PRIMARY KEY (perfume_id, field_code, option_code)
);

CREATE TABLE likes
(
    review_id  BIGINT   NOT NULL,
    user_id    BIGINT   NOT NULL,
    is_active  BIT(1)   NOT NULL,
    created_at datetime NOT NULL,
    updated_at datetime NOT NULL,
    CONSTRAINT pk_likes PRIMARY KEY (review_id, user_id)
);

CREATE TABLE mood
(
    mood_name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_mood PRIMARY KEY (mood_name)
);

CREATE TABLE note
(
    perfume_id BIGINT       NOT NULL,
    note_name  VARCHAR(255) NOT NULL,
    note_type  VARCHAR(255) NOT NULL,
    CONSTRAINT pk_note PRIMARY KEY (perfume_id, note_name)
);

CREATE TABLE perfume
(
    perfume_id    BIGINT           NOT NULL,
    perfume_name  VARCHAR(255)     NOT NULL,
    image_url     VARCHAR(255)     NULL,
    brand_name    VARCHAR(255)     NULL,
    average_score DOUBLE DEFAULT 0 NOT NULL,
    CONSTRAINT pk_perfume PRIMARY KEY (perfume_id)
);

CREATE TABLE perfume_accord
(
    perfume_id  BIGINT        NOT NULL,
    accord_name VARCHAR(255)  NOT NULL,
    value       INT DEFAULT 0 NOT NULL,
    CONSTRAINT pk_perfume_accord PRIMARY KEY (perfume_id, accord_name)
);

CREATE TABLE review
(
    review_id   BIGINT        NOT NULL,
    perfume_id  BIGINT        NOT NULL,
    user_id     BIGINT        NOT NULL,
    content     LONGTEXT      NOT NULL,
    review_type CHAR(6)       NOT NULL,
    like_count  INT DEFAULT 0 NOT NULL,
    is_active   BIT(1)        NOT NULL,
    created_at  datetime      NOT NULL,
    updated_at  datetime      NOT NULL,
    CONSTRAINT pk_review PRIMARY KEY (review_id)
);

CREATE TABLE review_evaluation
(
    review_id   BIGINT   NOT NULL,
    field_code  CHAR(5)  NOT NULL,
    option_code CHAR(5)  NOT NULL,
    is_active   BIT(1)   NOT NULL,
    created_at  datetime NOT NULL,
    updated_at  datetime NOT NULL,
    CONSTRAINT pk_review_evaluation PRIMARY KEY (review_id, field_code, option_code)
);

CREATE TABLE review_mood
(
    review_id  BIGINT       NOT NULL,
    mood_name  VARCHAR(255) NOT NULL,
    is_active  BIT(1)       NOT NULL,
    created_at datetime     NOT NULL,
    updated_at datetime     NOT NULL,
    CONSTRAINT pk_review_mood PRIMARY KEY (review_id, mood_name)
);

CREATE TABLE star_rating
(
    perfume_id BIGINT   NOT NULL,
    user_id    BIGINT   NOT NULL,
    score      INT      NOT NULL,
    is_active  BIT(1)   NOT NULL,
    created_at datetime NOT NULL,
    updated_at datetime NOT NULL,
    CONSTRAINT pk_star_rating PRIMARY KEY (perfume_id, user_id)
);

CREATE TABLE star_rating_statistic
(
    statistics_date CHAR(8)       NOT NULL,
    perfume_id      BIGINT        NOT NULL,
    score           INT           NOT NULL,
    votes           INT DEFAULT 0 NOT NULL,
    is_active       BIT(1)        NOT NULL,
    created_at      datetime      NOT NULL,
    updated_at      datetime      NOT NULL,
    CONSTRAINT pk_star_rating_statistic PRIMARY KEY (statistics_date, perfume_id, score)
);

CREATE TABLE user
(
    user_id               BIGINT         NOT NULL,
    nickname              VARCHAR(255)   NOT NULL,
    email                 VARCHAR(255)   NOT NULL,
    image_url             VARCHAR(255)   NULL,
    social_login_provider ENUM ('KAKAO') NOT NULL,
    is_active             BIT(1)         NOT NULL,
    created_at            datetime       NOT NULL,
    updated_at            datetime       NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (user_id)
);

CREATE TABLE user_accord
(
    user_id     BIGINT       NOT NULL,
    accord_name VARCHAR(255) NOT NULL,
    score       DOUBLE       NOT NULL,
    CONSTRAINT pk_user_accord PRIMARY KEY (user_id, accord_name)
);

ALTER TABLE review
    ADD CONSTRAINT uk_review_user_id_perfume_id UNIQUE (user_id, perfume_id);

ALTER TABLE complaint
    ADD CONSTRAINT fk_complaint_review_id FOREIGN KEY (review_id) REFERENCES review (review_id) ON DELETE NO ACTION;

ALTER TABLE complaint
    ADD CONSTRAINT fk_complaint_user_id FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE NO ACTION;

CREATE INDEX fk_complaint_user_id ON complaint (user_id);

ALTER TABLE evaluation_statistic
    ADD CONSTRAINT fk_evaluation_statistic_perfume_id FOREIGN KEY (perfume_id) REFERENCES perfume (perfume_id) ON DELETE NO ACTION;

CREATE INDEX fk_evaluation_statistic_perfume_id ON evaluation_statistic (perfume_id);

ALTER TABLE fragrantica_evaluation
    ADD CONSTRAINT fk_fragrantica_evaluation_perfume_id FOREIGN KEY (perfume_id) REFERENCES perfume (perfume_id) ON DELETE NO ACTION;

ALTER TABLE likes
    ADD CONSTRAINT fk_likes_review_id FOREIGN KEY (review_id) REFERENCES review (review_id) ON DELETE NO ACTION;

ALTER TABLE likes
    ADD CONSTRAINT fk_likes_user_id FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE NO ACTION;

CREATE INDEX fk_likes_user_id ON likes (user_id);

ALTER TABLE note
    ADD CONSTRAINT fk_note_perfume_id FOREIGN KEY (perfume_id) REFERENCES perfume (perfume_id) ON DELETE NO ACTION;

ALTER TABLE perfume_accord
    ADD CONSTRAINT fk_perfume_accord_accord_name FOREIGN KEY (accord_name) REFERENCES accord (accord_name) ON DELETE NO ACTION;

CREATE INDEX fk_perfume_accord_accord_name ON perfume_accord (accord_name);

ALTER TABLE perfume_accord
    ADD CONSTRAINT fk_perfume_accord_perfume_id FOREIGN KEY (perfume_id) REFERENCES perfume (perfume_id) ON DELETE NO ACTION;

ALTER TABLE perfume
    ADD CONSTRAINT fk_perfume_brand_name FOREIGN KEY (brand_name) REFERENCES brand (brand_name) ON DELETE NO ACTION;

CREATE INDEX fk_perfume_brand_name ON perfume (brand_name);

ALTER TABLE review_evaluation
    ADD CONSTRAINT fk_review_evaluation_review_id FOREIGN KEY (review_id) REFERENCES review (review_id) ON DELETE NO ACTION;

ALTER TABLE review_mood
    ADD CONSTRAINT fk_review_mood_mood_name FOREIGN KEY (mood_name) REFERENCES mood (mood_name) ON DELETE NO ACTION;

CREATE INDEX fk_review_mood_mood_name ON review_mood (mood_name);

ALTER TABLE review_mood
    ADD CONSTRAINT fk_review_mood_review_id FOREIGN KEY (review_id) REFERENCES review (review_id) ON DELETE NO ACTION;

ALTER TABLE review
    ADD CONSTRAINT fk_review_perfume_id FOREIGN KEY (perfume_id) REFERENCES perfume (perfume_id) ON DELETE NO ACTION;

ALTER TABLE review
    ADD CONSTRAINT fk_review_perfume_id_user_id FOREIGN KEY (perfume_id, user_id) REFERENCES star_rating (perfume_id, user_id) ON DELETE NO ACTION;

CREATE INDEX fk_review_perfume_id_user_id ON review (perfume_id, user_id);

ALTER TABLE review
    ADD CONSTRAINT fk_review_user_id FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE NO ACTION;

ALTER TABLE star_rating
    ADD CONSTRAINT fk_star_rating_perfume_id FOREIGN KEY (perfume_id) REFERENCES perfume (perfume_id) ON DELETE NO ACTION;

ALTER TABLE star_rating_statistic
    ADD CONSTRAINT fk_star_rating_statistic_perfume_id FOREIGN KEY (perfume_id) REFERENCES perfume (perfume_id) ON DELETE NO ACTION;

CREATE INDEX fk_star_rating_statistic_perfume_id ON star_rating_statistic (perfume_id);

ALTER TABLE star_rating
    ADD CONSTRAINT fk_star_rating_user_id FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE NO ACTION;

CREATE INDEX fk_star_rating_user_id ON star_rating (user_id);

ALTER TABLE user_accord
    ADD CONSTRAINT fk_user_accord_accord_name FOREIGN KEY (accord_name) REFERENCES accord (accord_name) ON DELETE NO ACTION;

CREATE INDEX fk_user_accord_accord_name ON user_accord (accord_name);

ALTER TABLE user_accord
    ADD CONSTRAINT fk_user_accord_user_id FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE NO ACTION;