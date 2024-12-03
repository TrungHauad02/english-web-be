CREATE TRIGGER IF NOT EXISTS trig_reading_question_bi
BEFORE INSERT ON reading_question
FOR EACH ROW
BEGIN
    SET NEW.content = TRIM(NEW.content);
    SET NEW.explanation = TRIM(NEW.explanation);
END;
