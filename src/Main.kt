


fun main() {

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
        print("-> ")

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
                   print("-> ")
                   val productDescription: String? = readlnOrNull()

                   var productPrice: Double? = null
                   println("Insira o PREÇO do produto:")
                   while(productPrice == null || productPrice < 0.0){
                       print("-> R$ ")
                       productPrice = readlnOrNull()?.toDoubleOrNull()
                       if(productPrice == null || productPrice < 0.0){
                           println("O PREÇO do produto inserido é inválido. Tente novamente.")
                       }
                   }

                   var productQuantity: Int? = null
                   println("Insira a QUANTIDADE inicial do produto:")
                   while(productQuantity == null || productQuantity < 0){
                       print("-> ")
                       productQuantity = readlnOrNull()?.toIntOrNull()
                       if(productQuantity == null  || productQuantity < 0){
                           println("A QUANTIDADE inicial do produto é inválida. Tente novamente.")
                       }
                   }

                    val newProduct: Product = Product(
                        name = productName,
                        description = productDescription,
                        price = productPrice,
                        qtd = productQuantity,
                        productCode = products.size + 1
                    )

                    createProduct(data = newProduct)

                    println("Produto cadastrado com sucesso.")

                    println("Voltando ao menu principal")

                }
                2 -> {
                    if(products.isEmpty()){
                        println("Nenhum produto cadastrado no menu de itens.")
                    }else{
                        println("Digite o código do produto - Digite 0 (Zero) para voltar:")
                        products.forEach { println(
                            "Código: ${it.productCode} | Nome: ${it.name} || Preço: R$${it.price} || Descrição: ${it.description} || Quantidade: ${it.qtd}"
                        ) }
                        print("-> ")
                        val productCode: Int = readln().toInt()

                        val productIndex: Int = findProductIndexByProductCode(productCode)

                        var selectedUpdateProductOption: Int? = null

                        while (selectedUpdateProductOption in 1.. 5){
                            println("O produto escolhido foi o(a): ${products[productIndex].name}")
                            println("Escolha o que quer alterar")
                            println("1 - Nome")
                            println("2 - Descrição")
                            println("3 - Preço")
                            println("4 - Quantidade")
                            println("5 - Voltar")

                            val selectedProductOption: Int = readln().toInt()

                            when(selectedProductOption){
                                1 -> {
                                    println("Digite o NOME para qual deseja alterar:")
                                    var newProductName: String? = null
                                    while(newProductName == null || newProductName.isEmpty()){
                                        print("-> ")
                                        newProductName = readlnOrNull()
                                        if(newProductName == null || newProductName.isEmpty()){
                                            println("O novo NOME do produto é inválido. Tente novamente.")
                                        }
                                    }

                                    updateProduct(
                                        field = "name",
                                        productIndex = productIndex,
                                        newValue = newProductName
                                    )
                                    println(products[productIndex].name)
                                }
                                2 ->{
                                    println("Digite a DESCRIÇÃO para qual deseja alterar:")
                                    print("-> ")
                                    val newProductDescription: String = readln()

                                    updateProduct(
                                        field = "description",
                                        productIndex = productIndex,
                                        newValue = newProductDescription
                                    )
                                    println(products[productIndex].description)
                                }
                                3 -> {
                                    println("Digite o PREÇO para qual deseja alterar:")
                                    var newProductPrice: Double? = null

                                    while(newProductPrice == null || newProductPrice < 0){
                                        print("-> R$ ")
                                        newProductPrice = readln().toDoubleOrNull()
                                        if(newProductPrice == null || newProductPrice < 0){
                                            println("O novo PREÇO do produto é inválido. Tente novamente.")
                                        }
                                    }

                                    updateProduct(
                                        field = "price",
                                        productIndex = productIndex,
                                        newValue = newProductPrice
                                    )
                                    println(products[productIndex].price)
                                }
                                4 -> {
                                    println("Digite a QUANTIDADE para qual deseja alterar:")
                                    var newProductQuantity: Int? = null

                                    while(newProductQuantity == null || newProductQuantity < 0){
                                        print("-> ")
                                        newProductQuantity = readln().toIntOrNull()
                                        if(newProductQuantity == null || newProductQuantity < 0){
                                            println("O novo PREÇO do produto é inválido. Tente novamente.")
                                        }
                                    }

                                    updateProduct(
                                        field = "quantity",
                                        productIndex = productIndex,
                                        newValue = newProductQuantity
                                    )
                                    println(products[productIndex].qtd)
                                }
                                5 -> {
                                    println("Saindo da edição de produtos...")
                                    selectedUpdateProductOption = 5;
                                }
                            }
                        }
                    }
                }
                3 -> {
                    println("Iniciado a cricao de pedidos...")

                    var orderNum: Int

                    val orderListLastIndex: Int = orders.lastIndex

                    orderNum = when{
                        orders.isEmpty() -> 1
                        else -> orders[orderListLastIndex].orderNum + 1
                    }

                    createOrder(data = Order(amount = 0.0, status = OrderStatus.DIGITANDO, orderNum = orderNum))

                    var selectedCreateOrder: Int? = 1
                    do{

                        println("Escolha uma opcao: ")
                        println("1 - Adicionar produtos")
                        println("2 - Finalizar pedido")
                        println("3 - Cancelar Digitação do pedido")

                        val selectOrderOption: Int = readln().toInt()

                        when (selectOrderOption){
                            1 -> {
                                when{
                                    products.isEmpty() -> {
                                        println("Nenhum produto cadastrado!")
                                    }
                                    else -> {
                                        println("Selecione um produto pelo código: ")
                                        println("Digite 0 (zero) para voltar.")

                                        for(product: Product in products){
                                            println("Código Produto: ${product.productCode} | Nome: ${product.name} | Preço: R$${product.price} | Quantidade Disponível: ${product.qtd}")
                                        }


                                        val selectedOrderProduct: Int = readln().toInt()

                                        when(selectedOrderProduct){
                                            in 1..(products.size + 1) -> {
                                                val productIndex: Int = findProductIndexByProductCode(productCode = selectedOrderProduct)

                                                val productExist: Product? = findProductByProductCode(productCode = selectedOrderProduct)

                                                when {
                                                    productExist == null -> println("Produto selecionado não encontrado!")
                                                    else -> {
                                                        println("Digite a quantidade desejada do produto: ")
                                                        val productOrderQuantity: Int = readln().toInt()

                                                        if(productOrderQuantity > products[productIndex].qtd){
                                                            println("Quantidade desejada indisponível no estoque!")
                                                        }

                                                        products[productIndex].qtd -= productOrderQuantity

                                                        val orderIndex: Int = findOrderIndexByOrderNum(orderNum)

                                                        val newOrderAmount: Double = orders[orderIndex].amount + (productOrderQuantity * products[productIndex].price)

                                                        orders[orderIndex].amount = newOrderAmount

                                                        println("Adicionando produto ${products[productIndex].name} ao pedido...")

                                                        addOrderItem(data = OrderItem(
                                                            productCode = products[productIndex].productCode,
                                                            productQuantity = productOrderQuantity,
                                                            productSellPrice = products[productIndex].price,
                                                            orderNum = orderNum
                                                        ))
                                                    }
                                                }
                                            }
                                            else ->{
                                                println("Voltando...")
                                            }
                                        }
                                    }
                                }
                            }
                            2 -> {
                                val doesOrderListHaveMoreThanZeroProduct: Boolean = ordersItems.none { it.orderNum == orderNum }

                                when{
                                    doesOrderListHaveMoreThanZeroProduct -> {
                                        println("O mínimo de produtos por pedido é 1")
                                        println("Voltando ao menu do pedido...")
                                    }
                                    else ->{
                                        println("Você possui algum cupom de desconto? Sim (S) - Não (N)")
                                        var doesHaveADiscountCoupon: String? = readlnOrNull()

                                        if(discountCoupons.isEmpty()){
                                            println("Desculpe! Não temos cupons de desconto dispiníveis no momento.")
                                            doesHaveADiscountCoupon = null
                                        }

                                        when{
                                            doesHaveADiscountCoupon == null || doesHaveADiscountCoupon.isEmpty() || doesHaveADiscountCoupon == "N" -> {
                                                println("Finalizando pedido...")
                                                val orderIndex: Int = findOrderIndexByOrderNum(orderNum)

                                                orders[orderIndex].status = OrderStatus.ACEITO

                                                println("Pedido de número $orderNum gerado.")
                                                println("Status do pedido: ${orders[orderIndex].status} | Valor Total: ${orders[orderIndex].amount}")
                                                selectedCreateOrder = 4
                                            }
                                            else -> {
                                                val orderIndex: Int = orders.indexOfFirst { it.orderNum == orderNum }

                                                println("Insira o cupom de desconto:")

                                                var discountCoupon: String? = null

                                                var discountCouponExists: DiscountCoupon? = null

                                                while (discountCouponExists == null){
                                                    discountCoupon = readlnOrNull()

                                                    discountCouponExists = discountCoupons.find {it.name.equals(discountCoupon, ignoreCase = true)}

                                                    when{
                                                        discountCouponExists == null -> {
                                                            println("Cupom de desconto não encontrado")
                                                        }
                                                        else -> {
                                                            val discount: Float = discountCouponExists.discount

                                                            val orderAmount: Double = orders[orderIndex].amount

                                                            orders[orderIndex].amount = orderAmount - ((orderAmount * discount) / 100)

                                                            orders[orderIndex].status = OrderStatus.ACEITO

                                                            println("Pedido de número $orderNum gerado.")
                                                            println("Status do pedido: ${orders[orderIndex].status} | Valor Total: ${orders[orderIndex].amount}")
                                                            selectedCreateOrder = 4
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            3 ->{
                                println("Cancelando digitação do pedido...")

                                cancelOrder(orderNum = orderNum)

                                selectedCreateOrder = 3
                            }
                        }
                    }while (selectedCreateOrder in 1..2)
                }
                4 -> {
                    val availableOrdersToChangeStatus: List<Order> = orders.filter {it.status != OrderStatus.ENTREGUE}
                    when{
                        availableOrdersToChangeStatus.isEmpty() -> println("Sem pedidos disponiveis para alterar o status.")
                        else -> {
                            println("--- Lista de pedidos disponiveis ---")
                            println("Escolha um pedido pelo codigo:")

                            availableOrdersToChangeStatus.forEach { println("Codigo: ${it.orderNum} | Valor Total: ${it.amount} | Status: ${it.status}") }

                            val selectedOrder: Int = readln().toInt()

                            val orderIndex: Int = orders.indexOfFirst { it.orderNum == selectedOrder }

                            println("--- Lista de Status ---")

                            OrderStatus.entries.forEachIndexed { index, value -> print("${index + 1} - $value") }

                            val selectedStatus: Int = readln().toInt()

                            orders[orderIndex].status = when(selectedStatus){
                                1 -> {
                                    OrderStatus.ACEITO
                                }

                                2 -> {
                                    OrderStatus.FAZENDO
                                }

                                3 -> {
                                    OrderStatus.FEITO
                                }

                                4 -> {
                                    OrderStatus.ESPERANDO_ENTREGADOR
                                }

                                5 -> {
                                    OrderStatus.SAIU_PARA_ENTREGA
                                }

                                6 -> {
                                    OrderStatus.ENTREGUE
                                }

                                else -> println("Opção inválida. Nenhuma alteração será realizada.")
                            } as OrderStatus

                            println("Pedido atualizado: Status =  ${orders[orderIndex].status}")

                        }
                    }
                }
                5 -> {
                    when{
                        orders.isEmpty() ->  println("Nenhum pedido cadastrado.")
                        else -> {
                            println("--- Filtrar Pedidos por Status ---")
                            println("--- Lista de Status ---")

                            OrderStatus.entries.forEachIndexed { index, value -> print("${index + 1} - $value") }

                            println("--- Selecione um Status ---")

                            val selectedStatus: Int = readln().toInt()

                            val status: OrderStatus = OrderStatus.entries[selectedStatus - 1]

                            val filteredOrders: List<Order> = filterOrderByStatus(status)

                            if(filteredOrders.isEmpty()){
                                println("Nenhum pedido encotrado com esses Status.")
                            }else{
                                filteredOrders.forEach { order ->
                                    println("Número do pedido: ${order.orderNum} | Total: R$ ${order.amount}")
                                }
                                println("\nTotal de pedidos: ${filteredOrders.size}")
                            }

                        }
                    }
                }
                6 -> {
                    println("Digite o nome do Cupom de desconto: ")
                    var couponName: String? = null


                    var couponAlreadyExists: DiscountCoupon? = null

                    while(couponName == null || couponName.isEmpty() || couponAlreadyExists != null){
                        print("-> ")
                        couponName = readlnOrNull()
                        if(couponName == null || couponName.isEmpty()){
                            println("O NOME do cupom inserido é inválido")
                        }

                        couponAlreadyExists = discountCoupons.find { it.name.equals(couponName, ignoreCase = true) }

                        if(couponAlreadyExists !== null){
                            println("Já existe um cupom com esse nome no sistema.")
                        }
                    }


                    println("Digite a pocentagem de desconto (A pocentagem deve estar entre 0 e 100): ")
                    var discountPercent: Float? = null
                    while(discountPercent == null || discountPercent < 0.0){
                        print("-> ")
                        discountPercent = readln().toFloatOrNull()
                        if(discountPercent == null || discountPercent < 0.0){
                            println("Valor inserido para a PORCENTAGEM de desconto inválido. Tente novamente.")
                        }
                    }

                    discountCoupons.add(DiscountCoupon(name = couponName, discount = discountPercent))
                }
                7 -> {
                    println("Saindo...")
                    selectedMainMenuOptions = 7
                }
                else -> println("Opção inválida")
            }
    }
}