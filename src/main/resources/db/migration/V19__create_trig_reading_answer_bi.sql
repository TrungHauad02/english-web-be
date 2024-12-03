CREATE TRIGGER IF NOT EXISTS trig_reading_answer_bi
BEFORE INSERT ON reading_answer
FOR EACH ROW
BEGIN
    SET NEW.content = TRIM(NEW.content);
END;
