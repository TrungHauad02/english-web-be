CREATE TRIGGER IF NOT EXISTS trig_grammar_answer_bi
BEFORE INSERT ON grammar_answer
FOR EACH ROW
BEGIN
  SET NEW.content = TRIM(NEW.content);
END;
