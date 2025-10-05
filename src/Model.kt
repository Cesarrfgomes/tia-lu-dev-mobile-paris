data class Product (
    var name: String,
    var description: String?,
    var price: Double,
    var qtd: Int,
    val productCode: Int
)

data class Order(
    var status: OrderStatus,
    var amount: Double,
    var payment: String = "Pago",
    var orderNum: Int
)

data class OrderItem(
    var productCode: Int,
    var productQuantity: Int,
    var productSellPrice: Double,
    val orderNum: Int
)

data class DiscountCoupon(
    var name: String,
    var discount: Float,
    var isActive: Boolean = true
)

enum class OrderStatus{
    DIGITANDO,
    ACEITO,
    FAZENDO,
    FEITO,
    ESPERANDO_ENTREGADOR,
    SAIU_PARA_ENTREGA,
    ENTREGUE
}

val products: MutableList<Product> = mutableListOf()
val orders: MutableList<Order> = mutableListOf()
val ordersItems: MutableList<OrderItem> = mutableListOf()
val discountCoupons: MutableList<DiscountCoupon> = mutableListOf()

//fun readString(text: String): String{
//    var entries: String? = null
//
//    do {
//
//    } while(entries == null)
//}

fun createProduct (data: Product){
    products.add(data)
}

fun updateProduct(field: String, productIndex: Int, newValue: Any){
    when(field){
        "name" -> products[productIndex].name = newValue as String
        "description" -> products[productIndex].description = newValue as String
        "price" -> products[productIndex].price = newValue as Double
        "quantity" -> products[productIndex].qtd = newValue as Int
    }
}

fun createOrder(data: Order){
    orders.add(data)
}

fun addOrderItem(data: OrderItem){
    ordersItems.add(data)
}

fun cancelOrder (orderNum: Int) {
    orders.removeIf { it.orderNum == orderNum }

    val orderItemsList: List<OrderItem> = ordersItems.filter {it.orderNum == orderNum}

    for(item: OrderItem in orderItemsList){
        if(item.orderNum == orderNum){
            val productIndex = products.indexOfFirst { it.productCode == item.productCode }
            products[productIndex].qtd += item.productQuantity
        }
    }

    ordersItems.removeIf { it.orderNum == orderNum }
}

fun findOrderByOrderNum(orderNum: Int): Order?{
    return orders.find {it.orderNum == orderNum}
}

fun filterOrderByStatus(status: OrderStatus): List<Order> {
    return orders.filter {it.status == status}
}

fun findOrderIndexByOrderNum(orderNum: Int): Int{
    return orders.indexOfFirst { it.orderNum == orderNum }
}

fun findProductByProductCode(productCode: Int): Product?{
    return products.find {it.productCode == productCode}
}

fun findProductIndexByProductCode(productCode: Int): Int{
    return products.indexOfFirst { it.productCode == productCode }
}



