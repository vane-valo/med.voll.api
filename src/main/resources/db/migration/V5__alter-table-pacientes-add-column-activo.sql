ALTER TABLE pacientes ADD activo tinyint;
UPDATE pacientes SET activo = 1;
ALTER TABLE pacientes modify activo tinyint NOT NULL;