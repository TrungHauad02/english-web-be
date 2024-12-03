CREATE TRIGGER IF NOT EXISTS trig_test_speaking_question_bu
BEFORE UPDATE ON test_speaking_question
FOR EACH ROW
BEGIN
    SET NEW.content = TRIM(NEW.content);
END;
