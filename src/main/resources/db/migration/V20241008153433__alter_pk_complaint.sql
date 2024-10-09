ALTER TABLE complaint
    DROP FOREIGN KEY fk_complaint_review_id;

ALTER TABLE complaint
    DROP FOREIGN KEY fk_complaint_user_id;

ALTER TABLE complaint DROP PRIMARY KEY;

ALTER TABLE complaint
    ADD complaint_id BIGINT        NOT NULL,
    ADD token        VARCHAR(255)  NOT NULL;

ALTER TABLE complaint
    ADD CONSTRAINT pk_complaint PRIMARY KEY (complaint_id);

ALTER TABLE complaint
    ADD CONSTRAINT fk_complaint_review_id FOREIGN KEY (review_id) REFERENCES review (review_id) ON DELETE NO ACTION;

ALTER TABLE complaint
    ADD CONSTRAINT fk_complaint_user_id FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE NO ACTION;
