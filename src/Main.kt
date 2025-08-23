data class Product (
    var name: String,
    var description: String?,
    var price: Double,
    var qtd: Float,
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
    var productQuantity: Float,
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

enum class MenusStatus{
    OPENED,
    CLOSED
}

fun main() {

    val productsList: MutableList<Product> = mutableListOf()
    val ordersList: MutableList<Order> = mutableListOf()
    val ordersItemsList: MutableList<OrderItem> = mutableListOf()
    val discountCouponsList: MutableList<DiscountCoupon> = mutableListOf()

    var selectedMainMenuOptions: Int? = null

    while (selectedMainMenuOptions != 7){
        println("Escolha uma opção:")
        println("1 - Cadastrar Item")
        println("2 - Atualizar Item")
        println("3 - Criar Pedido")
        println("4 - Atualizar Pedido")
        println("5 - Consultar Pedidos")
        println("6 - Criar Cupom de Desconto")
        println("7 - Sair")

        selectedMainMenuOptions = readln().toInt()

            when (selectedMainMenuOptions){
               1 -> {
                   var productName: String? = null
                   println("Insira o NOME do produto:")
                   while(productName == null || productName.isEmpty()){
                       print("-> ")
                       productName = readlnOrNull()
                       if(productName == null || productName.isEmpty()){
                           println("O NOME do produto inserido é inválido. Tente novamente.")
                       }
                   }

                   println("Insira a DESCRIÇÃO do produto:")
                   var productDescription: String? = readlnOrNull()


                   var productPrice: Double? = null
                   println("Insira o PREÇO do produto:")
                   while(productPrice == null){
                       print("-> ")
                       productPrice = readlnOrNull()?.toDoubleOrNull()
                       if(productPrice == null){
                           println("O PREÇO do produto inserido é inválido. Tente novamente.")
                       }
                   }


                   var productQuantity: Float? = null
                   println("Insira a QUANTIDADE inicial do produto:")
                   while(productQuantity == null){
                       print("-> ")
                       productQuantity = readlnOrNull()?.toFloatOrNull()
                       if(productQuantity == null){
                           println("A QUANTIDADE inicial do produto é inválida. Tente novamente.")
                       }
                   }

                    val product: Product = Product(
                        name = productName,
                        description = productDescription,
                        price = productPrice,
                        qtd = productQuantity,
                        productCode = productsList.size + 1
                    )

                    productsList.add(product)
                    print(productsList)
                    println("Voltando ao menu principal")

                }
                2 -> {
                    println("Digite o código do produto")
                    productsList.forEach { println("Código: ${it.productCode} | Nome: ${it.name}") }
                    val productCode: Int = readln().toInt()

                    val productIndex: Int = productsList.indexOfFirst {it.productCode == productCode}

                    var selectedUpdateProductOption: Int? = null
                    while (selectedUpdateProductOption != 4){
                        println("O produto escolhido foi o(a): ${productsList[productIndex].name}")
                        println("Escolha o que quer alterar")
                        println("1 - Nome")
                        println("2 - Descrição")
                        println("3 - Preço")
                        println("4 - Voltar")

                        val selectedProductOption: Int = readln().toInt()

                        when(selectedProductOption){
                            1 -> {
                                println("Digite o nome para qual deseja alterar")
                                val newProductName: String = readln()
                                productsList[productIndex].name = newProductName
                                print(productsList[productIndex].name)
                            }
                            2 ->{
                                println("Digite a descrição para qual deseja alterar")
                                val newProductDescription: String = readln()
                                productsList[productIndex].description = newProductDescription
                                print(productsList[productIndex].description)
                            }
                            3 -> {
                                println("Digite o preço para qual deseja alterar")
                                val newProductPrice: Double = readln().toDouble()
                                productsList[productIndex].price = newProductPrice
                                print(productsList[productIndex].price)
                            }
                            4 -> {
                                println("Saindo da edição de produtos...")
                                selectedUpdateProductOption = 4;
                            }
                        }
                    }
                }
                3 -> {
                    println("Iniciado a cricao de pedidos...")

                    var orderNum: Int

                    val orderListLastIndex: Int = ordersList.lastIndex

                    orderNum = when{
                        ordersList.isEmpty() -> 1
                        else -> ordersList[orderListLastIndex].orderNum + 1
                    }

                    ordersList.add(Order(amount = 0.0, status = OrderStatus.DIGITANDO, orderNum = orderNum))


                    var selectedCreateOrder: Int? = null
                    while (selectedCreateOrder != 3 && selectedCreateOrder !=4){

                        println("Escolha uma opcao: ")
                        println("1 - Adicionar produtos")
                        println("2 - Finalizar pedido")
                        println("3 - Cancelar Digitação do pedido")

                        var selectOrderOption: Int = readln().toInt()

                        when (selectOrderOption){
                            1 -> {
                                when{
                                    productsList.isEmpty() -> {
                                        println("Nenhum produto cadastrado!")
                                    }
                                    else -> {
                                        println("Selecione um produto pelo código: ")
                                        println("Digite 0 (zero) para voltar.")

                                        for(product: Product in productsList){
                                            println("Código Produto: ${product.productCode} | Nome: ${product.name} | Preço: ${product.price} | Quantidade Disponível: ${product.qtd}")
                                        }


                                        val selectedOrderProduct: Int = readln().toInt()

                                        if(selectedOrderProduct == 0) {
                                            println("Voltando...")
                                            selectedCreateOrder = 3
                                        }

                                        val productIndex: Int = productsList.indexOfFirst {it.productCode == selectedOrderProduct}

                                        val productExist: Product? = productsList.find { it.productCode == selectedOrderProduct }

                                        if(productExist == null){
                                            println("Produto selecionado não encontrado!")
                                        }

                                        println("Digite a quantidade desejada do produto: ")
                                        val productOrderQuantity: Float = readln().toFloat()

                                        if(productOrderQuantity > productsList[productIndex].qtd){
                                            println("Quantidade desejada indisponível no estoque!")
                                        }

                                        productsList[productIndex].qtd -= productOrderQuantity

                                        val orderIndex: Int = ordersList.indexOfFirst { it.orderNum == orderNum }

                                        val newOrderAmount: Double = ordersList[orderIndex].amount + (productOrderQuantity * productsList[productIndex].price)

                                        ordersList[orderIndex].amount = newOrderAmount

                                        println("Adicionando produto ${productsList[productIndex].name} ao pedido...")

                                        ordersItemsList.add(OrderItem(
                                            productCode = productsList[productIndex].productCode,
                                            productQuantity = productOrderQuantity,
                                            productSellPrice = productsList[productIndex].price,
                                            orderNum = orderNum
                                        ))
                                    }
                                }
                            }
                            2 -> {
                                val doesOrderListHaveMoreThanZeroProduct: Boolean = ordersItemsList.none { it.orderNum == orderNum }

                                when{
                                    doesOrderListHaveMoreThanZeroProduct -> {
                                        println("O mínimo de produtos por pedido é 1")
                                        println("Voltando ao menu do pedido...")
                                    }
                                    else ->{
                                        println("Finalizando pedido...")
                                        val orderIndex: Int = ordersList.indexOfFirst { it.orderNum == orderNum }

                                        ordersList[orderIndex].status = OrderStatus.ACEITO

                                        println(ordersList[orderIndex])

                                        println("Pedido de número $orderNum gerado.")
                                        println("Status do pedido: ${ordersList[orderIndex].status} | Valor Total: ${ordersList[orderIndex].amount}")
                                        selectedCreateOrder = 4
                                    }
                                }
                            }
                            3 ->{
                                println("Cancelando digitação do pedido...")

                                ordersList.removeIf { it.orderNum == orderNum }
                                ordersItemsList.removeIf { it.orderNum == orderNum }
                                selectedCreateOrder = 3
                            }
                        }
                    }
                }
                6 -> {
                    println("Digite o nome do Cupom de desconto: ")
                    val couponName: String = readln()

                    if(couponName.isEmpty()){
                        println("O campo nome é obrigatório")
                        println("Saindo do cadastro de itens...")
                        selectedMainMenuOptions = 0
                    }

                    val couponAlreadyExists: DiscountCoupon? = discountCouponsList.find { it.name.uppercase() == couponName.uppercase() }

                    if(couponAlreadyExists !== null){
                        println("Já existe um cupom com esse nome no sistema.")
                        selectedMainMenuOptions = 0
                    }

                    println("Digite a pocentagem de desconto (A pocentagem deve estar entre 0 e 100): ")
                    val discountPercent: Float = readln().toFloat()

                    discountCouponsList.add(DiscountCoupon(name = couponName, discount = discountPercent))
                }
                7 -> {
                    println("Saindo...")
                    selectedMainMenuOptions = 7
                }
                else -> println("Opção inválida")
            }
    }
}