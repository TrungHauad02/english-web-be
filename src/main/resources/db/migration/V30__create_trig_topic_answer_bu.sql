CREATE TRIGGER IF NOT EXISTS trig_topic_answer_bu
BEFORE UPDATE ON topic_answer
FOR EACH ROW
BEGIN
    SET NEW.content = TRIM(NEW.content);
END;
