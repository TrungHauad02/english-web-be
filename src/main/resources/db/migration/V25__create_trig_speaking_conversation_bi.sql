CREATE TRIGGER IF NOT EXISTS trig_speaking_conversation_bi
BEFORE INSERT ON speaking_conversation
FOR EACH ROW
BEGIN
    SET NEW.content = TRIM(NEW.content);
END;
