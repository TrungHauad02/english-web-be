CREATE TRIGGER IF NOT EXISTS trig_vocabulary_bu
BEFORE UPDATE ON vocabulary
FOR EACH ROW
BEGIN
    SET NEW.word = TRIM(NEW.word);
    SET NEW.example = TRIM(NEW.example);
    SET NEW.phonetic = TRIM(NEW.phonetic);
    SET NEW.meaning = TRIM(NEW.meaning);
END;
