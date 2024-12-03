CREATE TRIGGER IF NOT EXISTS trig_topic_bu
BEFORE UPDATE ON topic
FOR EACH ROW
BEGIN
    SET NEW.title = TRIM(NEW.title);
    SET NEW.description = TRIM(NEW.description);
END;