CREATE TRIGGER IF NOT EXISTS trig_test_speaking_bi
BEFORE INSERT ON test_speaking
FOR EACH ROW
BEGIN
    SET NEW.title = TRIM(NEW.title);
END;
