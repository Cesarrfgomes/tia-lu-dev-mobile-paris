import jdk.jfr.Enabled
import kotlin.system.exitProcess

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

data class MenuOption(
    var optionNumber: Int,
    var optionName: String
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

enum class SystemStatusEnum{
    STARTED,
    BREAKED
}

fun main() {

    val productsList: MutableList<Product> = mutableListOf()
    val ordersList: MutableList<Order> = mutableListOf()
    val ordersItemsList: MutableList<OrderItem> = mutableListOf()
    val discountCouponsList: MutableList<DiscountCoupon> = mutableListOf()
    val mainMenuOptions: List<MenuOption> = listOf(
        MenuOption(optionNumber = 1, optionName = "Cadastrar Item"),
        MenuOption(optionNumber = 2, optionName = "Atualizar Item"),
        MenuOption(optionNumber = 3, optionName = "Criar Pedido"),
        MenuOption(optionNumber = 4, optionName = "Atualizar Pedido"),
        MenuOption(optionNumber = 5, optionName = "Consultar Pedidos"),
        MenuOption(optionNumber = 6, optionName = "Criar Cupom de Desconto"),
        MenuOption(optionNumber = 7, optionName = "Sair")
    )

    var selectedMainMenuOptions: Int? = null

    while(selectedMainMenuOptions != 7){
        println("Escolha uma opção:")
        mainMenuOptions.forEach { println("${it.optionNumber} - ${it.optionName}") }

        selectedMainMenuOptions = readlnOrNull()?.toIntOrNull()

        when (selectedMainMenuOptions){
            1 -> {
                var itemsMenu: SystemStatusEnum = SystemStatusEnum.STARTED
                while(itemsMenu == SystemStatusEnum.STARTED){
                    println("Digite o nome do produto:")
                    val productName: String = readln()

                    if(productName.isEmpty()){
                        println("O campo nome é obrigatório")
                        println("Saindo do cadastro de itens...")
                        itemsMenu = SystemStatusEnum.BREAKED
                    }

                    println("Digite a descrição do produto")
                    val productDescription: String? = readlnOrNull()

                    println("Digite o preço do produto")
                    val productPrice: Double = readln().toDouble()

                    if(productPrice.isNaN()){
                        println("O campo preço é obrigatório")
                        println("Saindo do cadastro de itens...")
                        break
                    }

                    println("Digite a quantidade inicial de estoque do produto")
                    val productQuantity: Float = readln().toFloat()

                    if(productQuantity.isNaN()){
                        println("O campo quantidade é obrigatório")
                        println("Saindo do cadastro de itens...")
                        break
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
                    break
                }

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

                var orderNum: Int

                val orderListLastIndex: Int = ordersList.lastIndex

                orderNum = when{
                    ordersList.isEmpty() -> 1
                    else -> ordersList[orderListLastIndex].orderNum + 1
                }

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
                                println("Digite 0 (zero) para voltar.")

                                for(product: Product in productsList){
                                    println("Código Produto: ${product.productCode} | Nome: ${product.name} | Preço: ${product.price} | Quantidade Disponível: ${product.qtd}")
                                }


                                val selectedOrderProduct: Int = readln().toInt()

                                if(selectedOrderProduct == 0) break

                                val productIndex: Int = productsList.indexOfFirst {it.productCode == selectedOrderProduct}

                                val productExist: Product? = productsList.find { it.productCode == selectedOrderProduct }

                                if(productExist == null){
                                    println("Produto selecionado não encontrado!")
                                    break
                                }

                                println("Digite a quantidade desejada do produto: ")
                                val productOrderQuantity: Float = readln().toFloat()

                                if(productOrderQuantity > productsList[productIndex].qtd){
                                    println("Quantidade desejada indisponível no estoque!")
                                    break
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

                                break
                            }
                        }
                        2 -> {
                            val doesOrderListHaveMoreThanZeroProduct: Boolean = ordersItemsList.none { it.orderNum == orderNum }

                            if(doesOrderListHaveMoreThanZeroProduct){
                                println("O mínimo de produtos por pedido é 1")
                                print("Voltando ao menu do pedido...")
                                break
                            }

                            println("Finalizando pedido...")
                            val orderIndex: Int = ordersList.indexOfFirst { it.orderNum == orderNum }

                            ordersList[orderIndex].status = OrderStatus.ACEITO

                            println(ordersList[orderIndex])

                            println("Pedido de número $orderNum gerado.")
                            println("Status do pedido: ${ordersList[orderIndex].status} | Valor Total: ${ordersList[orderIndex].amount}")
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
                while(true){
                    println("Digite o nome do Cupom de desconto: ")
                    val couponName: String = readln()

                    if(couponName.isEmpty()){
                        println("O campo nome é obrigatório")
                        println("Saindo do cadastro de itens...")
                        break
                    }

                    val couponAlreadyExists: DiscountCoupon? = discountCouponsList.find { it.name.uppercase() == couponName.uppercase() }

                    if(couponAlreadyExists !== null){
                        println("Já existe um cupom com esse nome no sistema.")
                        break
                    }

                    println("Digite a pocentagem de desconto (A pocentagem deve estar entre 0 e 100): ")
                    val discountPercent: Float = readln().toFloat()

                    discountCouponsList.add(DiscountCoupon(name = couponName, discount = discountPercent))

                    break
                }
            }
            7 -> {
                println("Saindo...")
                selectedMainMenuOptions = 7
            }
            else -> println("Opção inválida")
        }
    }

}