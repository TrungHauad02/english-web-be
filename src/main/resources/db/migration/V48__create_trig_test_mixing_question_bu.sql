CREATE TRIGGER IF NOT EXISTS trig_test_mixing_question_bu
BEFORE UPDATE ON test_mixing_question
FOR EACH ROW
BEGIN
    SET NEW.content = TRIM(NEW.content);
    SET NEW.explanation = TRIM(NEW.explanation);
END;
