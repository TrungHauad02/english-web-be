CREATE TRIGGER IF NOT EXISTS trig_listening_bi
BEFORE INSERT ON listening
FOR EACH ROW
BEGIN
    SET NEW.title = TRIM(NEW.title);
    SET NEW.description = TRIM(NEW.description);
END;
