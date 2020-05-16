# example-java-acceder-bdOrac
Aplicación Java a través de la que acceder a los datos almacenados en una base de datos Oracle

datos:
• La base de datos incluirá datos referentes a los PRODUCTOS que vende la empresa
• Por cada producto será necesario disponer:
o REFERENCIA: cadena de 10 caracteres como máximo que permitirá identificar a un producto en
concreto.
o NOMBRE: cadena de 100 caracteres como máximo que representa el nombre del producto. No
podrá haber dos productos con el mismo nombre
o STOCK: número de 5 dígitos que representa el número de unidades que se dispone del producto
en el almacén.
o PVP: número de 8 dígitos (de los cuales 2 se corresponden con la parte decimal) que representa
el precio de venta al público del producto

Se realiza:
o consultarProductos: esta funcionalidad permitirá obtener los datos de todos los productos
registrados en la base de datos.
o consultarProducto: esta funcionalidad permitirá obtener el producto identificado por su
referencia.
o consultarProductosPorPrecio: esta funcionalidad permitirá obtener un lista de productos cuyo
PVP sea menor o igual a un determinado valor que se pasará como parámetro a la funcionalidad.
o consultarProductosPorStock: a través de esta funcionalidad se obtendrá una lista conteniendo
los productos cuyo stock esté comprendido entre un limiteInferior y un limiteSuperior que serán
pasados como parámetros a la funcionalidad.
o insertarProducto: esta funcionalidad permitirá incluir en la tabla un nuevo producto. Será
necesario pasar como parámetro los datos del producto.
o actualizarProducto: a través de esta funcionalidad se permitirá modificar todos los datos de un
producto excepto su referencia.
o eliminarProducto: esta funcionalidad permitirá eliminar un producto en concreto identificado por
su referencia
