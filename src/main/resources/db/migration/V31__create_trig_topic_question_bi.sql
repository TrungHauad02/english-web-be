CREATE TRIGGER IF NOT EXISTS trig_topic_question_bi
BEFORE INSERT ON topic_question
FOR EACH ROW
BEGIN
    SET NEW.content = TRIM(NEW.content);
    SET NEW.explanation = TRIM(NEW.explanation);
END;
