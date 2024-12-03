CREATE TRIGGER IF NOT EXISTS trig_speaking_conversation_bu
BEFORE UPDATE ON speaking_conversation
FOR EACH ROW
BEGIN
    SET NEW.content = TRIM(NEW.content);
END;
