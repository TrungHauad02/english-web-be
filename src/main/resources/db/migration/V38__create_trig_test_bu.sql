CREATE TRIGGER IF NOT EXISTS trig_test_bu
BEFORE UPDATE ON test
FOR EACH ROW
BEGIN
    SET NEW.title = TRIM(NEW.title);
END;
