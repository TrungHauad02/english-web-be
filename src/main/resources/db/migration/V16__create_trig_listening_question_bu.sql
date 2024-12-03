CREATE TRIGGER IF NOT EXISTS trig_listening_question_bu
BEFORE UPDATE ON listening_question
FOR EACH ROW
BEGIN
    SET NEW.content = TRIM(NEW.content);
    SET NEW.explanation = TRIM(NEW.explanation);
END;
