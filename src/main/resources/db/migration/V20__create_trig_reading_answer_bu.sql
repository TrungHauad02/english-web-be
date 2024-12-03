CREATE TRIGGER IF NOT EXISTS trig_reading_answer_bu
BEFORE UPDATE ON reading_answer
FOR EACH ROW
BEGIN
    SET NEW.content = TRIM(NEW.content);
END;
