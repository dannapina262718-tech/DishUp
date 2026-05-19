package enums;

/**
 * Enumerador que define los tipos de productos disponibles en el sistema del restaurante.
 * Este catálogo permite clasificar los productos del menú según su naturaleza,
 * facilitando la organización, filtrado y gestión dentro del sistema de ventas y cocina.
 *
 * @author DishUp
 */
public enum TipoProducto {

    /**
     * Producto correspondiente a alimentos principales preparados en cocina.
     * Incluye platillos que suelen formar parte del consumo fuerte del cliente.
     */
    COMIDA,

    /**
     * Producto considerado como botana o acompañamiento.
     * Generalmente son porciones ligeras o entradas que no constituyen un platillo principal.
     */
    BOTANA,

    /**
     * Producto líquido destinado al consumo como bebida.
     * Incluye opciones frías o calientes como refrescos, jugos o café.
     */
    BEBIDA
}