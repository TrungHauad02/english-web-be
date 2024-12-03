CREATE TRIGGER IF NOT EXISTS trig_listening_answer_bu
BEFORE UPDATE ON listening_answer
FOR EACH ROW
BEGIN
    SET NEW.content = TRIM(NEW.content);
END;
