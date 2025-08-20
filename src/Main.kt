data class Product (
    var name: String,
    var description: String,
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

enum class OrderStatus{
    DIGITANDO,
    ACEITO,
    FAZENDO,
    FEITO,
    ESPERANDO_ENTREGADOR,
    SAIU_PARA_ENTREGA,
    ENTREGUE
}

fun main() {

    val productsList: MutableList<Product> = mutableListOf<Product>()
    val ordersList: MutableList<Order> = mutableListOf<Order>()
    val ordersItemsList: MutableList<OrderItem> = mutableListOf<OrderItem>()

    while (true){
        println("Escolha uma opção:")
        println("1 - Cadastrar Item")
        println("2 - Atualizar Item")
        println("3 - Criar Pedido")
        println("4 - Atualizar Pedido")
        println("5 - Consultar Pedidos")
        println("6 - Sair")

        val selectedOption: Int = readln().toInt()

            when (selectedOption){
                1 -> {
                    println("Digite o nome do produto:")
                    val productName: String = readln()
                    println("Digite a descrição do produto")
                    val productDescription: String = readln()
                    println("Digite o preço do produto")
                    val productPrice: Double = readln().toDouble()
                    println("Digite a quantidade inicial de estoque do produto")
                    val productQuantity: Float = readln().toFloat()

                    val product: Product = Product(
                        name = productName,
                        description = productDescription,
                        price = productPrice,
                        qtd = productQuantity,
                        productCode = productsList.size + 1
                    )

                    productsList.add(product)
                    print(productsList)
                }
                2 -> {
                    println("Digite o código do produto")
                    val productCode: Int = readln().toInt()

                    val productIndex: Int = productsList.indexOfFirst {it.productCode == productCode}

                    while (true){
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
                                break;
                            }
                        }
                    }
                }
                3 -> {
                    println("Iniciado a cricao de pedidos...")
                    val orderNum: Int = ordersList.size + 1

                    ordersList.add(Order(amount = 0.0, status = OrderStatus.DIGITANDO, orderNum = orderNum))

                    while (true){

                        println("Escolha uma opcao: ")
                        println("1 - Adicionar produtos")
                        println("2 - Finalizar pedido")
                        println("3 - Cancelar Digitação do pedido")

                        val selectOrderOption: Int = readln().toInt()

                        when (selectOrderOption){
                            1 -> {
                                while (true){

                                    if (productsList.isEmpty()) {
                                        println("Nenhum produto cadastrado!")
                                        break
                                    }

                                    println("Selecione um produto pelo código: ")

                                    for(product: Product in productsList){
                                        println("Código Produto: ${product.productCode} | Nome: ${product.name} | Preço: ${product.price} | Quantidade Disponível: ${product.qtd}")
                                    }


                                    val selectedOrderProduct: Int = readln().toInt()
                                    val productIndex: Int = productsList.indexOfFirst {it.productCode == selectedOrderProduct}

                                    val productExist: Product? = productsList.find { it.productCode == selectedOrderProduct }

                                    if(productExist == null){
                                        println("Produto selecionado não encontrado!")
                                        break
                                    }

                                    println("Digite a quantidade desejada do produto: ")
                                    val productOrderQuantity: Float = readln().toFloat()

                                    if(productOrderQuantity > productsList[productIndex].qtd){
                                        println("Quantidade desejada indisponível no estoque")
                                        break
                                    }

                                    productsList[productIndex].qtd -= productOrderQuantity

                                    val orderIndex: Int = ordersList.indexOfFirst { it.orderNum == orderNum }

                                    val newOrderAmount: Double = ordersList[orderIndex].amount + (productOrderQuantity * productsList[productIndex].price)

                                    ordersList[orderIndex].amount = newOrderAmount

                                    println("Adicionando produto ${productsList[productIndex].name} ao pedido")

                                    ordersItemsList.add(OrderItem(
                                        productCode = productsList[productIndex].productCode,
                                        productQuantity = productOrderQuantity,
                                        productSellPrice = productsList[productIndex].price,
                                        orderNum = orderNum
                                    ))

                                    break
                                }
                            }
                            2 -> {
                                val doesListHaveMoreThanZeroProduct: List<OrderItem> = ordersItemsList.filter { it.orderNum == orderNum }

                                if(doesListHaveMoreThanZeroProduct.isEmpty()){
                                    println("O mínimo de produtos por pedido é 1")
                                    print("Voltando ao menu do pedido...")
                                    break
                                }

                                val orderIndex: Int = ordersList.indexOfFirst { it.orderNum == orderNum }
                                println(ordersList[orderIndex])
                                break
                            }
                            3 ->{
                                println("Cancelando digitação do pedido...")

                                ordersList.removeIf { it.orderNum == orderNum }
                                ordersItemsList.removeIf { it.orderNum == orderNum }
                                break
                            }
                        }
                    }
                }
                6 -> {
                    println("Saindo...")
                    break;
                }
                else -> println("Opção inválida")
            }
    }

}