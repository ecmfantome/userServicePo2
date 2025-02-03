# ALTER TABLE user
#     ADD `role` SMALLINT NULL;
#
# ALTER TABLE user
#     MODIFY `role` SMALLINT NOT NULL;

ALTER TABLE user
    DROP
        COLUMN password;



# ALTER TABLE user
# DROP
# COLUMN gender;
#
# ALTER TABLE user
#     MODIFY age INT NULL;
#
# ALTER TABLE user
#     ADD gender VARCHAR(255) NOT NULL;