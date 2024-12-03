CREATE TRIGGER IF NOT EXISTS trig_listening_question_bi
BEFORE INSERT ON listening_question
FOR EACH ROW
BEGIN
    SET NEW.content = TRIM(NEW.content);
    SET NEW.explanation = TRIM(NEW.explanation);
END;
