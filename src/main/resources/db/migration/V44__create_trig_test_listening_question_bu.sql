CREATE TRIGGER IF NOT EXISTS trig_test_listening_question_bu
BEFORE UPDATE ON test_listening_question
FOR EACH ROW
BEGIN
    SET NEW.content = TRIM(NEW.content);
END;
