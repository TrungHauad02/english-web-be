CREATE TRIGGER IF NOT EXISTS trig_writing_bu
BEFORE UPDATE ON writing
FOR EACH ROW
BEGIN
    SET NEW.title = TRIM(NEW.title);
    SET NEW.description = TRIM(NEW.description);
    SET NEW.topic = TRIM(NEW.topic);
END;
