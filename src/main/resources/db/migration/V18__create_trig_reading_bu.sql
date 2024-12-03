CREATE TRIGGER IF NOT EXISTS trig_reading_bu
BEFORE UPDATE ON reading
FOR EACH ROW
BEGIN
    SET NEW.title = TRIM(NEW.title);
    SET NEW.description = TRIM(NEW.description);
END;
