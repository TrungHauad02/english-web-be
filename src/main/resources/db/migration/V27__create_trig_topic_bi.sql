CREATE TRIGGER IF NOT EXISTS trig_topic_bi
BEFORE INSERT ON topic
FOR EACH ROW
BEGIN
    SET NEW.title = TRIM(NEW.title);
    SET NEW.description = TRIM(NEW.description);
END;
