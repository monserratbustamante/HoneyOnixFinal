
data class Producto(
    val id: String,
    val nombre: String,
    val precio: Double,
    val descripcion: String,
    val cantidad: Int,
    val imagenes: List<String> = emptyList()
)
