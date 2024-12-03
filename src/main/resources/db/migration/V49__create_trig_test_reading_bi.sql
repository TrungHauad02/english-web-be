CREATE TRIGGER IF NOT EXISTS trig_test_reading_bi
BEFORE INSERT ON test_reading
FOR EACH ROW
BEGIN
    SET NEW.content = TRIM(NEW.content);
END;
