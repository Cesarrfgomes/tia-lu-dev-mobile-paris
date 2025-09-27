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

val productsList: MutableList<Product> = mutableListOf()
val ordersList: MutableList<Order> = mutableListOf()
val ordersItemsList: MutableList<OrderItem> = mutableListOf()
val discountCouponsList: MutableList<DiscountCoupon> = mutableListOf()

//fun readString(text: String): String{
//    var entries: String? = null
//
//    do {
//
//    } while(entries == null)
//}

fun createProduct (data: Product){
    productsList.add(data)
}

fun updateProduct(field: String, productIndex: Int, newValue: Any){
    when(field){
        "name" -> productsList[productIndex].name = newValue as String
        "description" -> productsList[productIndex].description = newValue as String
        "price" -> productsList[productIndex].price = newValue as Double
        "quantity" -> productsList[productIndex].qtd = newValue as Int
    }
}

fun createOrder(data: Order){
    ordersList.add(data)
}

fun addOrderItem(data: OrderItem){
    ordersItemsList.add(data)
}

fun cancelOrder (orderNum: Int) {
    ordersList.removeIf { it.orderNum == orderNum }

    val orderItemsList: List<OrderItem> = ordersItemsList.filter {it.orderNum == orderNum}

    for(item: OrderItem in orderItemsList){
        if(item.orderNum == orderNum){
            val productIndex = productsList.indexOfFirst { it.productCode == item.productCode }
            productsList[productIndex].qtd += item.productQuantity
        }
    }

    ordersItemsList.removeIf { it.orderNum == orderNum }
}

