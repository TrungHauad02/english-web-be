CREATE TRIGGER IF NOT EXISTS trig_grammar_answer_bu
BEFORE UPDATE ON grammar_answer
FOR EACH ROW
BEGIN
  SET NEW.content = TRIM(NEW.content);
END;
