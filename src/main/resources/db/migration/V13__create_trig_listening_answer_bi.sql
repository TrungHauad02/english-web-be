CREATE TRIGGER IF NOT EXISTS trig_listening_answer_bi
BEFORE INSERT ON listening_answer
FOR EACH ROW
BEGIN
    SET NEW.content = TRIM(NEW.content);
END;