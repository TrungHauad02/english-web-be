CREATE TRIGGER IF NOT EXISTS trig_test_writing_bi
BEFORE INSERT ON test_writing
FOR EACH ROW
BEGIN
    SET NEW.content = TRIM(NEW.content);
END;
