CREATE PROCEDURE `get_session`(in correoIN VARCHAR(100))
BEGIN
DECLARE theOffset INT;
SELECT c1.id, c1.correo, c1.clave, c1.apellido, c1.nombre, c1.direccion, c1.dni, c1.foto, c1.created_at, c1.updated_at
FROM user_projections c1
where c1.correo = correoIN;
END