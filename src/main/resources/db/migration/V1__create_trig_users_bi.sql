CREATE TRIGGER IF NOT EXISTS trig_users_bi
BEFORE INSERT ON users
FOR EACH ROW
BEGIN
  SET NEW.name = TRIM(NEW.name);
  SET NEW.email = TRIM(NEW.email);
END;