package adaptadores;

import dtos.ProductoDTO;
import dtos.IngredienteEnProductoDTO;
import dtos_infraestructura.IngredienteDTOInfraestructura;
import dtos_infraestructura.ProductoDTOInfraestructura;
import entidades.IngredienteEnProducto;
import entidades.Producto;
import enums.TipoProducto;
import enums.TipoProductoDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductoNegocioAdapter.
 * 
 * Clase encargada de convertir productos entre la capa de infraestructura,
 * la capa de dominio y los DTOs de la aplicacion.
 * 
 * Su funcion es actuar como puente de transformacion de datos,
 * permitiendo que los productos provenientes de sistemas externos
 * puedan convertirse en entidades de negocio, y que estas a su vez
 * puedan transformarse en DTOs para la capa de presentacion o servicios.
 * 
 * Tambien maneja la conversion de listas de productos e ingredientes
 * asociados.
 * 
 * @author DishUp
 */
public class ProductoNegocioAdapter {

    /**
     * Convierte un ProductoDTOInfraestructura a una entidad de dominio Producto.
     *
     * @param dto producto proveniente de infraestructura
     * @return entidad Producto construida
     */
    public Producto aDominio(ProductoDTOInfraestructura dto) {

        if (dto == null) {
            return null;
        }

        Producto producto = new Producto();

        producto.setId(dto.getId());
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setDisponible(dto.isDisponible());
        producto.setTipo(TipoProducto.valueOf(dto.getTipo().name()));
        producto.setUrlImagen(dto.getUrlImagen());

        List<IngredienteEnProducto> ingredientes = new ArrayList<>();

        if (dto.getIngredientes() != null) {
            for (IngredienteDTOInfraestructura ing : dto.getIngredientes()) {

                IngredienteEnProducto ingrediente = new IngredienteEnProducto();

                ingrediente.setNombre(ing.getNombre());
                ingrediente.setCantidad(ing.getCantidad());
                ingrediente.setRemovible(ing.isRemovible());

                ingredientes.add(ingrediente);
            }
        }

        producto.setIngredientes(ingredientes);

        return producto;
    }

    /**
     * Convierte una lista de productos de infraestructura a dominio.
     *
     * @param listaDTO lista de productos infraestructura
     * @return lista de productos en dominio
     */
    public List<Producto> listaADominio(List<ProductoDTOInfraestructura> listaDTO) {

        List<Producto> productos = new ArrayList<>();

        if (listaDTO == null) {
            return productos;
        }

        for (ProductoDTOInfraestructura dto : listaDTO) {
            productos.add(aDominio(dto));
        }

        return productos;
    }

    /**
     * Convierte una entidad Producto a ProductoDTO.
     *
     * @param producto entidad de dominio
     * @return objeto ProductoDTO
     */
    public ProductoDTO aDTO(Producto producto) {

        if (producto == null) {
            return null;
        }

        ProductoDTO dto = new ProductoDTO();

        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setDisponible(producto.isDisponible());
        dto.setTipo(TipoProductoDTO.valueOf(producto.getTipo().name()));
        dto.setUrlImagen(producto.getUrlImagen());

        List<IngredienteEnProductoDTO> ingredientes = new ArrayList<>();

        if (producto.getIngredientes() != null) {
            for (IngredienteEnProducto ingrediente : producto.getIngredientes()) {

                IngredienteEnProductoDTO dtoIngrediente = new IngredienteEnProductoDTO();

                dtoIngrediente.setNombre(ingrediente.getNombre());
                dtoIngrediente.setCantidad(ingrediente.getCantidad());
                dtoIngrediente.setRemovible(ingrediente.isRemovible());

                ingredientes.add(dtoIngrediente);
            }
        }

        dto.setIngredientes(ingredientes);

        return dto;
    }

    /**
     * Convierte una lista de productos de dominio a DTO.
     *
     * @param productos lista de productos de dominio
     * @return lista de ProductoDTO
     */
    public List<ProductoDTO> listaDominioADTO(List<Producto> productos) {

        List<ProductoDTO> lista = new ArrayList<>();

        if (productos == null) {
            return lista;
        }

        for (Producto producto : productos) {
            lista.add(aDTO(producto));
        }

        return lista;
    }
}