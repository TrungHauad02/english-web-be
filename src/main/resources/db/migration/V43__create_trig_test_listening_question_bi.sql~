CREATE TRIGGER IF NOT EXISTS trig_test_listening_answer_bi
BEFORE INSERT ON test_listening_answer
FOR EACH ROW
BEGIN
    SET NEW.content = TRIM(NEW.content);
END;
