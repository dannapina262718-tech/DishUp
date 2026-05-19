package objetosNegocio;

import adaptadores.IngredienteNegocioAdapter;
import adaptadores.ProductoNegocioAdapter;
import dtos.IngredienteEnProductoDTO;
import dtos.ProductoDTO;
import dtos.ProductoIngredienteDTO;
import dtos_infraestructura.ProductoDTOInfraestructura;
import entidades.Producto;
import enums.TipoProducto;
import enums.TipoProductoDTO;
import excepcion.NegocioException;
import excepciones.InfraestructuraException;
import interfaces.ISistemaInventario;
import java.util.ArrayList;
import java.util.List;

/**
 * BO encargado de la lógica de negocio de productos.
 *
 * Se encarga de consultar productos desde el sistema de inventario,
 * filtrarlos por tipo y transformar información de infraestructura
 * a DTOs de dominio y aplicación.
 */
public class ProductoBO {

    private final ISistemaInventario fachadaInventario;
    private final ProductoNegocioAdapter productoAdapter;
    private final IngredienteNegocioAdapter ingredienteAdapter;

    public ProductoBO(ISistemaInventario fachadaInventario) {
        this.fachadaInventario = fachadaInventario;
        this.productoAdapter = new ProductoNegocioAdapter();
        this.ingredienteAdapter = new IngredienteNegocioAdapter();
    }

    /**
     * Obtiene los productos filtrados por tipo.
     *
     * @param tipo tipo de producto a consultar
     * @return lista de productos filtrados en formato DTO
     * @throws NegocioException si el tipo es nulo o ocurre un error de infraestructura
     */
    public List<ProductoDTO> obtenerProductosPorTipo(TipoProductoDTO tipo) throws NegocioException {

        if (tipo == null) {
            throw new NegocioException("El tipo de producto es obligatorio.");
        }

        try {

            List<ProductoDTOInfraestructura> productosInfra =
                    fachadaInventario.obtenerProductos();

            List<Producto> todos =
                    productoAdapter.listaADominio(productosInfra);

            List<ProductoDTO> filtrados = new ArrayList<>();

            TipoProducto tipoDominio = TipoProducto.valueOf(tipo.name());

            for (Producto producto : todos) {
                if (producto.getTipo() == tipoDominio) {
                    filtrados.add(productoAdapter.aDTO(producto));
                }
            }

            return filtrados;

        } catch (InfraestructuraException ex) {
            throw new NegocioException(
                    "No fue posible obtener los productos.",
                    ex
            );
        }
    }

    /**
     * Obtiene los ingredientes removibles de un producto.
     *
     * @param idProducto identificador del producto
     * @return lista de ingredientes removibles
     * @throws NegocioException si el id es inválido o ocurre un error de infraestructura
     */
    public List<IngredienteEnProductoDTO> obtenerIngredientesRemoviblesPorProducto(String idProducto)
            throws NegocioException {

        if (idProducto == null || idProducto.isBlank()) {
            throw new NegocioException("El id del producto es obligatorio.");
        }

        try {

            ProductoDTOInfraestructura productoInfra =
                    fachadaInventario.obtenerProductoPorId(idProducto);

            Producto producto =
                    productoAdapter.aDominio(productoInfra);

            if (producto == null) {
                return new ArrayList<>();
            }

            return ingredienteAdapter.convertirIngredientesRemovibles(
                    producto.getIngredientes()
            );

        } catch (InfraestructuraException ex) {
            throw new NegocioException(
                    "No fue posible obtener los ingredientes.",
                    ex
            );
        }
    }

    /**
     * Obtiene todos los ingredientes de un producto.
     *
     * @param idProducto identificador del producto
     * @return lista de ingredientes del producto
     * @throws NegocioException si el id es inválido o ocurre un error de infraestructura
     */
    public List<ProductoIngredienteDTO> obtenerIngredientesDeProducto(String idProducto)
            throws NegocioException {

        if (idProducto == null || idProducto.isBlank()) {
            throw new NegocioException("El id del producto es obligatorio.");
        }

        try {

            ProductoDTOInfraestructura productoInfra =
                    fachadaInventario.obtenerProductoPorId(idProducto);

            Producto producto =
                    productoAdapter.aDominio(productoInfra);

            if (producto == null) {
                return new ArrayList<>();
            }

            return ingredienteAdapter.convertirAProductoIngredienteDTO(
                    idProducto,
                    producto.getIngredientes()
            );

        } catch (InfraestructuraException ex) {
            throw new NegocioException(
                    "No fue posible obtener los ingredientes.",
                    ex
            );
        }
    }
}