CREATE TRIGGER IF NOT EXISTS trig_grammar_bi
BEFORE INSERT ON grammar
FOR EACH ROW
BEGIN
  SET NEW.title = TRIM(NEW.title);
  SET NEW.description = TRIM(NEW.description);
  SET NEW.content = TRIM(NEW.content);
  SET NEW.example = TRIM(NEW.example);
END;
