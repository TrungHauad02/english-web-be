CREATE TRIGGER IF NOT EXISTS trig_topic_question_bu
BEFORE UPDATE ON topic_question
FOR EACH ROW
BEGIN
    SET NEW.content = TRIM(NEW.content);
    SET NEW.explanation = TRIM(NEW.explanation);
END;
