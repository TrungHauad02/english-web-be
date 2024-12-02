CREATE TRIGGER IF NOT EXISTS trig_users_bu
BEFORE UPDATE ON users
FOR EACH ROW
BEGIN
  SET NEW.name = TRIM(NEW.name);
  SET NEW.email = TRIM(NEW.email);
END;
