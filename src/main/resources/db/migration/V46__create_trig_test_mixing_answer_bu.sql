CREATE TRIGGER IF NOT EXISTS trig_test_mixing_answer_bu
BEFORE UPDATE ON test_mixing_answer
FOR EACH ROW
BEGIN
    SET NEW.content = TRIM(NEW.content);
END;
