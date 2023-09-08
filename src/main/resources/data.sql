INSERT INTO work (name, description, estimated_time, price)
SELECT 'Maquiagem Noiva', NULL, 14400000000000, 100.00
WHERE NOT EXISTS (
    SELECT 1 FROM work WHERE name = 'Maquiagem Noiva'
);

INSERT INTO work (name, description, estimated_time, price)
SELECT 'Maquiagem Madrinha', NULL, 7200000000000, 100.00
WHERE NOT EXISTS (
    SELECT 1 FROM work WHERE name = 'Maquiagem Madrinha'
);

INSERT INTO work (name, description, estimated_time, price)
SELECT 'Maquiagem Noiva + Penteado', NULL, 21600000000000, 100.00
WHERE NOT EXISTS (
    SELECT 1 FROM work WHERE name = 'Maquiagem Noiva + Penteado'
);

INSERT INTO work (name, description, estimated_time, price)
SELECT 'Maquiagem Madrinha + Penteado', NULL, 10800000000000, 100.00
WHERE NOT EXISTS (
    SELECT 1 FROM work WHERE name = 'Maquiagem Madrinha + Penteado'
);

INSERT INTO work (name, description, estimated_time, price)
SELECT 'Volume Brasileiro', NULL, 7200000000000, 75.00
WHERE NOT EXISTS (
    SELECT 1 FROM work WHERE name = 'Volume Brasileiro'
);

INSERT INTO work (name, description, estimated_time, price)
SELECT 'Volume Russo', NULL, 10800000000000, 75.00
WHERE NOT EXISTS (
    SELECT 1 FROM work WHERE name = 'Volume Russo'
);

INSERT INTO work (name, description, estimated_time, price)
SELECT 'Fio a fio', NULL, 10800000000000, 75.00
WHERE NOT EXISTS (
    SELECT 1 FROM work WHERE name = 'Fio a fio'
);

INSERT INTO appointment_status (name)
SELECT 'Pendente'
WHERE NOT EXISTS (
    SELECT 1 FROM appointment_status WHERE name = 'Pendente'
);

INSERT INTO appointment_status (name)
SELECT 'Cancelado'
WHERE NOT EXISTS (
    SELECT 1 FROM appointment_status WHERE name = 'Cancelado'
);

INSERT INTO appointment_status (name)
SELECT 'Concluído'
WHERE NOT EXISTS (
    SELECT 1 FROM appointment_status WHERE name = 'Concluído'
);

