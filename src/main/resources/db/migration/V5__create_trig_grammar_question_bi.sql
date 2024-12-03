CREATE TRIGGER IF NOT EXISTS trig_grammar_question_bi
BEFORE INSERT ON grammar_question
FOR EACH ROW
BEGIN
  SET NEW.content = TRIM(NEW.content);
  SET NEW.explanation = TRIM(NEW.explanation);
END;
