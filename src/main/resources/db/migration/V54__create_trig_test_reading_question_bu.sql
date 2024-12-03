CREATE TRIGGER IF NOT EXISTS trig_test_reading_question_bu
BEFORE UPDATE ON test_reading_question
FOR EACH ROW
BEGIN
    SET NEW.content = TRIM(NEW.content);
    SET NEW.explantion = TRIM(NEW.explantion);
END;
