CREATE TRIGGER IF NOT EXISTS trig_listen_and_writeaword_bu
BEFORE UPDATE ON listen_and_writeaword
FOR EACH ROW
BEGIN
    SET NEW.sentence = TRIM(NEW.sentence);
    SET NEW.correct_answer = TRIM(NEW.correct_answer);
END;
