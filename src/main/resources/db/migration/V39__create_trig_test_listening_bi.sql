CREATE TRIGGER IF NOT EXISTS trig_test_listening_bi
BEFORE INSERT ON test_listening
FOR EACH ROW
BEGIN
    SET NEW.content = TRIM(NEW.content);
    SET NEW.transcript = TRIM(NEW.transcript);
END;
