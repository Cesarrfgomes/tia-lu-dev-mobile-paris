<<<<<<< HEAD
data class Product (
    var name: String,
    var description: String?,
    var price: Double,
    var qtd: Float, // por que float? seria melhor se fosse um Int
    val productCode: Int
)

data class Order(
    var status: OrderStatus,
    var amount: Double, //esse amount seria o quê?
    var payment: String = "Pago",
    var orderNum: Int //o número do pedido não deveria ser modificado depois dele criado
    //cadê os itens do pedido?
    //val items: MutableList<Order>
)

data class OrderItem(
    var productCode: Int,// product
    var productQuantity: Float,
    var productSellPrice: Double, // já tem no product
    val orderNum: Int
)

// achei interessante, mas não sei se tinha tanta necessidade
data class DiscountCoupon(
    var name: String,
    var discount: Float,
    var isActive: Boolean = true
)

enum class OrderStatus{
    DIGITANDO, // interessante
    ACEITO,
    FAZENDO,
    FEITO,
    ESPERANDO_ENTREGADOR,
    SAIU_PARA_ENTREGA,
    ENTREGUE
}
=======

>>>>>>> ab48e3f0d3da864aa52e1092f2fe64128c601d58


fun main() {

    <<<<<<< HEAD
    // essa variáveis não deveria ter o nome List
    // vocês deveriam ter optado apenas pelo nome no plural
    // products
    val productsList: MutableList<Product> = mutableListOf()
    val ordersList: MutableList<Order> = mutableListOf()
    val ordersItemsList: MutableList<OrderItem> = mutableListOf()
    val discountCouponsList: MutableList<DiscountCoupon> = mutableListOf()

    =======
    >>>>>>> ab48e3f0d3da864aa52e1092f2fe64128c601d58
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

        // essa identação aqui não era necessária
        when (selectedMainMenuOptions){
            1 -> { //Cadastrar item
                var productName: String? = null

                // essa leitura deveria ser realizada com o do..while, uma vez que você quer primeiro ler
                // e depois testar
                /**
                 * do {
                 *    println("Insira o NOME do produto:")
                 *    productName = readlnOrNull()
                 * } while(productName?.isBlank())
                 */
                println("Insira o NOME do produto:")
                while(productName == null || productName.isEmpty()){
                    print("-> ")
                    productName = readlnOrNull()
                    //repetindo a condição do laço
                    if(productName == null || productName.isEmpty()){
                        println("O NOME do produto inserido é inválido. Tente novamente.")
                    }
                }


                println("Insira a DESCRIÇÃO do produto:")
                print("-> ")
                val productDescription: String? = readlnOrNull()

                // a mesma coisa aqui, vocês deveria ter usado o do.. while para fazer a leitura
                var productPrice: Double? = null
                println("Insira o PREÇO do produto:")
                while(productPrice == null || productPrice < 0.0){
                    print("-> R$ ")
                    productPrice = readlnOrNull()?.toDoubleOrNull()
                    //repetindo a condição do laço
                    if(productPrice == null || productPrice < 0.0){
                        println("O PREÇO do produto inserido é inválido. Tente novamente.")
                    }
                }

                <<<<<<< HEAD

                // ainda não vejo muito sentido dessa variável ser Float, para mim ela deveria ser Int
                var productQuantity: Float? = null
                =======
                var productQuantity: Int? = null
                >>>>>>> ab48e3f0d3da864aa52e1092f2fe64128c601d58
                println("Insira a QUANTIDADE inicial do produto:")
                while(productQuantity == null || productQuantity < 0){
                    print("-> ")
                    <<<<<<< HEAD
                    productQuantity = readlnOrNull()?.toFloatOrNull()
                    //repetindo a condição do laço
                    if(productQuantity == null  || productQuantity < 0.0){
                        =======
                        productQuantity = readlnOrNull()?.toIntOrNull()
                        if(productQuantity == null  || productQuantity < 0){
                            >>>>>>> ab48e3f0d3da864aa52e1092f2fe64128c601d58
                            println("A QUANTIDADE inicial do produto é inválida. Tente novamente.")
                        }
                    }

                    val newProduct: Product = Product(
                        name = productName,
                        description = productDescription,
                        price = productPrice,
                        qtd = productQuantity,
                        productCode = productsList.size + 1 // tudo bem que não temos a opção de excluir um produto/item de cardápio
                        // mas essa solução pode gerar um erro involuntário se adicionarmos a opção de remover um produto
                    )

                    <<<<<<< HEAD
                    productsList.add(product)
                    print(productsList) // por que temos que imprimir toda a lista de produtos?
                    =======
                    createProduct(data = newProduct)

                    print(productsList)
                    >>>>>>> ab48e3f0d3da864aa52e1092f2fe64128c601d58
                    println("Voltando ao menu principal")

                }
                2 -> {

                    /* nesse caso o uso do if teria sido mais idiomático
                        if(products.isEmpty()) {
                            println("Nenhum produto cadastrado no menu de itens.")
                        } else {
                                // o outro código
                        }
                    */
                    when{
                        productsList.isEmpty() -> println("Nenhum produto cadastrado no menu de itens.")
                        else -> {
                            <<<<<<< HEAD
                            // essa linha deveria estar depois da listagem de produtos
                            println("Digite o código do produto")
                            productsList.forEach { println("Código: ${it.productCode} | Nome: ${it.name} || Prço: R$${it.price}") }
                            print("-> ") // esse print é desnecessário
                            =======
                            println("Digite o código do produto - Digite 0 (Zero) para voltar:")
                            productsList.forEach { println(
                                "Código: ${it.productCode} | Nome: ${it.name} || Preço: R$${it.price} || Descrição: ${it.description} || Quantidade: ${it.qtd}"
                            ) }
                            print("-> ")
                            >>>>>>> ab48e3f0d3da864aa52e1092f2fe64128c601d58
                            val productCode: Int = readln().toInt()

                            //por que não usar o find? Ele vai retornar o objeto Product que tenha o código
                            // e você poderia atualizar o objeto.
                            // além disso, esse cara pode retornar -1, caso não encontre nada
                            val productIndex: Int = productsList.indexOfFirst {it.productCode == productCode}

                            var selectedUpdateProductOption: Int? = null

                            <<<<<<< HEAD
                            // aqui o ideal era um do while
                            // essa variável está usada de modo inadequado
                            while (selectedUpdateProductOption != 5){
                                // aqui você pode ter um IndexOutOfRangeException porque o indexOfFirt pode retornar -1
                                =======
                                while (selectedUpdateProductOption in 1.. 5){
                                    >>>>>>> ab48e3f0d3da864aa52e1092f2fe64128c601d58
                                    println("O produto escolhido foi o(a): ${productsList[productIndex].name}")
                                    println("Escolha o que quer alterar")
                                    println("1 - Nome")
                                    println("2 - Descrição")
                                    println("3 - Preço")
                                    println("4 - Quantidade")
                                    println("5 - Voltar")

                                    // essa variável não deveria existir, uma vez que a variável selectedUpdateProductOption
                                    // já representa a opção de atualização selecionada.
                                    // criou outra variável já tendo uma para fazer isso.
                                    val selectedProductOption: Int = readln().toInt()

                                    when(selectedProductOption){
                                        1 -> {
                                            // isso aqui deveria ser um do..while
                                            // você pode inclusive criar uma função genérica para leitura de dados
                                            // validando a condição
                                            println("Digite o NOME para qual deseja alterar:")
                                            var newProductName: String? = null
                                            while(newProductName == null || newProductName.isEmpty()){
                                                print("-> ")
                                                newProductName = readlnOrNull()
                                                if(newProductName == null || newProductName.isEmpty()){
                                                    println("O novo NOME do produto é inválido. Tente novamente.")
                                                }
                                            }

                                            <<<<<<< HEAD
                                            //poderíamos ter uma exceção de IndexOutOfBounds aqui, mas ela vai ser lançada
                                            //mais acima. O ideal era ter recuperado o objeto com o find e não ficar
                                            // usando o índice para manipular os valores.
                                            productsList[productIndex].name = newProductName
                                            print(productsList[productIndex].name) // porque eu tenho que imprimir o novo nome?
                                            // isso foi usado para debug e não foi removido?
                                            =======
                                            updateProduct(
                                                field = "name",
                                                productIndex = productIndex,
                                                newValue = newProductName
                                            )
                                            println(productsList[productIndex].name)
                                            >>>>>>> ab48e3f0d3da864aa52e1092f2fe64128c601d58
                                        }
                                        2 ->{
                                            println("Digite a DESCRIÇÃO para qual deseja alterar:")
                                            print("-> ") // remover esse print, pois piora a experiência do usuário
                                            val newProductDescription: String = readln()
                                            <<<<<<< HEAD
                                            productsList[productIndex].description = newProductDescription
                                            print(productsList[productIndex].description) // print para debug não removido
                                            =======

                                            updateProduct(
                                                field = "description",
                                                productIndex = productIndex,
                                                newValue = newProductDescription
                                            )
                                            println(productsList[productIndex].description)
                                            >>>>>>> ab48e3f0d3da864aa52e1092f2fe64128c601d58
                                        }
                                        3 -> {
                                            println("Digite o PREÇO para qual deseja alterar:")
                                            var newProductPrice: Double? = null

                                            <<<<<<< HEAD
                                            // aqui deveria tá com o do..while
                                            while(newProductPrice == null || newProductPrice < 0.0){
                                                print("-> R$ ")
                                                newProductPrice = readln().toDoubleOrNull()
                                                //esse if acaba replicando a condição que o laço já faz
                                                if(newProductPrice == null || newProductPrice < 0.0){
                                                    =======
                                                    while(newProductPrice == null || newProductPrice < 0){
                                                        print("-> R$ ")
                                                        newProductPrice = readln().toDoubleOrNull()
                                                        if(newProductPrice == null || newProductPrice < 0){
                                                            >>>>>>> ab48e3f0d3da864aa52e1092f2fe64128c601d58
                                                            println("O novo PREÇO do produto é inválido. Tente novamente.")
                                                        }
                                                    }

                                                    <<<<<<< HEAD
                                                    productsList[productIndex].price = newProductPrice
                                                    print(productsList[productIndex].price) // print de debug???
                                                    =======
                                                    updateProduct(
                                                        field = "price",
                                                        productIndex = productIndex,
                                                        newValue = newProductPrice
                                                    )
                                                    println(productsList[productIndex].price)
                                                    >>>>>>> ab48e3f0d3da864aa52e1092f2fe64128c601d58
                                                }
                                                4 -> {
                                                    println("Digite a QUANTIDADE para qual deseja alterar:")
                                                    var newProductQuantity: Int? = null

                                                    <<<<<<< HEAD
                                                    //do .. while
                                                    while(newProductQuantity == null || newProductQuantity < 0.0){
                                                        print("-> ")
                                                        newProductQuantity = readln().toFloatOrNull()
                                                        //condição do laço repetida, isso não é um bom sinal
                                                        if(newProductQuantity == null || newProductQuantity < 0.0){
                                                            =======
                                                            while(newProductQuantity == null || newProductQuantity < 0){
                                                                print("-> ")
                                                                newProductQuantity = readln().toIntOrNull()
                                                                if(newProductQuantity == null || newProductQuantity < 0){
                                                                    >>>>>>> ab48e3f0d3da864aa52e1092f2fe64128c601d58
                                                                    println("O novo PREÇO do produto é inválido. Tente novamente.")
                                                                }
                                                            }

                                                            <<<<<<< HEAD
                                                            productsList[productIndex].qtd = newProductQuantity
                                                            print(productsList[productIndex].qtd) //print de debug
                                                            =======
                                                            updateProduct(
                                                                field = "quantity",
                                                                productIndex = productIndex,
                                                                newValue = newProductQuantity
                                                            )
                                                            println(productsList[productIndex].qtd)
                                                            >>>>>>> ab48e3f0d3da864aa52e1092f2fe64128c601d58
                                                        }
                                                        5 -> {
                                                            println("Saindo da edição de produtos...")
                                                            selectedUpdateProductOption = 5; //se essa variável tivesse sido utilizada na leitura
                                                            // não precisava dessa atribuição e faria o laço sair quando a opção fosse 5
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                    }
                                    3 -> {

                                        /*
                                            verifica os comentários que coloquei lá embaixo sobre isso
                                         if(products.isEmpty()) {
                                                println("Nenhum produto cadastrado!")
                                        *   } else {
                                                @todo o restante abaixo
                                            }
                                        *
                                        * */
                                        println("Iniciado a cricao de pedidos...")

                                        //bem, eu não acho uma boa estratégia a criação de um novo índice a partir do tamanho
                                        //da coleção, mas como ela não reduz, apenas cresce, o número do pedido vai sempre crescer.
                                        // acho que esses código ficou muito complicado e pouco idiomático.
                                        /*

                                        fazendo uma linha de código baseado nessa ideia
                                        val orderNumber: Int = orders.size + 1

                                        mas vocês também poderiam ter feito uma variável global de geração do orderNumber

                                        * */
                                        var orderNum: Int //isso seria orderNumber?

                                        val orderListLastIndex: Int = ordersList.lastIndex

                                        orderNum = when{
                                            ordersList.isEmpty() -> 1
                                            else -> ordersList[orderListLastIndex].orderNum + 1
                                        }

                                        <<<<<<< HEAD

                                        //eu acho que é meio ruim criar o pedido antes de termos o mínimo necessário
                                        //a gente veria criar depois de termos um item, pelo menos.
                                        ordersList.add(Order(amount = 0.0, status = OrderStatus.DIGITANDO, orderNum = orderNum))


                                        var selectedCreateOrder: Int? = null //essa variável é subutilizada

                                        //aqui deveria ser utilizado o do..while
                                        while (selectedCreateOrder != 3 && selectedCreateOrder !=4){
                                            =======
                                            createOrder(data = Order(amount = 0.0, status = OrderStatus.DIGITANDO, orderNum = orderNum))

                                            var selectedCreateOrder: Int? = 1
                                            do{
                                                >>>>>>> ab48e3f0d3da864aa52e1092f2fe64128c601d58

                                                println("Escolha uma opcao: ")
                                                println("1 - Adicionar produtos")
                                                println("2 - Finalizar pedido")
                                                println("3 - Cancelar Digitação do pedido")

                                                <<<<<<< HEAD
                                                //nesse caso, se fosse essa variável usada, ela deveria ser val
                                                var selectOrderOption: Int = readln().toInt() // você já tem a variável selectedCreateOrder, por
                                                // que criar outra?
                                                =======
                                                val selectOrderOption: Int = readln().toInt()
                                                >>>>>>> ab48e3f0d3da864aa52e1092f2fe64128c601d58

                                                // valida as opções desse submenu
                                                when (selectOrderOption){
                                                    1 -> {
                                                        when{
                                                            //faz sentido deixar que um pedido seja criado se não tiver produtos/itens cadastrados?
                                                            //talvez não fosse melhor esse cara está no ínicio dessa opção.
                                                            /*  if(products.isEmpty()) {
                                                                    println("Nenhum produto cadastrado!")
                                                            *   } else {
                                                                    @todo o restante
                                                                }
                                                            *
                                                            * */
                                                            productsList.isEmpty() -> {
                                                                println("Nenhum produto cadastrado!")
                                                            }
                                                            else -> {

                                                                // essa opção deveria só tá com esse código:
                                                                // o for deveria estar aqui antes

                                                                //esses prints deveriam vir depois do for
                                                                println("Selecione um produto pelo código: ")
                                                                println("Digite 0 (zero) para voltar.")

                                                                // o uso do forEach é mais idiomático, esses fors desse jeito, normalmente, devem
                                                                //ser utilizados quando precisamos lidar com índices.
                                                                //products.forEach{ product ->  println("Código Produto: ${product.productCode} | Nome: ${product.name} | Preço: R$${product.price} | Quantidade Disponível: ${product.qtd}")}
                                                                for(product: Product in productsList){
                                                                    println("Código Produto: ${product.productCode} | Nome: ${product.name} | Preço: R$${product.price} | Quantidade Disponível: ${product.qtd}")
                                                                }

                                                                val selectedOrderProduct: Int = readln().toInt()

                                                                <<<<<<< HEAD
                                                                //uma pessoa pode voltar no meio do percurso da criação de um pedido?
                                                                //O pedido foi criado, mas não teve nenhum item adicionado, isso não é problemático?
                                                                //afinal, isso não atende os requisitos solicitados
                                                                // além disso, essa variável serve para duas coisas: (1) ser o código do produto escolhido
                                                                // ou (2) ser uma opção de sair do preenchimento do pedido.
                                                                if(selectedOrderProduct == 0) {
                                                                    println("Voltando...")
                                                                    selectedCreateOrder = 4
                                                                }


                                                                //qual o objetivo de usar o indexOfFirst e o find? Deveria usar apenas o find
                                                                val productIndex: Int = productsList.indexOfFirst {it.productCode == selectedOrderProduct}

                                                                //essa é a melhor escolha para recuperar um produto com aquele código.
                                                                val productExist: Product? = productsList.find { it.productCode == selectedOrderProduct }


                                                                //acho importante isso
                                                                if(productExist == null){
                                                                    println("Produto selecionado não encontrado!")
                                                                    //para isso funcionar, você teria que fazer um continue
                                                                } // mas nesse caso, seria legal ter o else, por quê? vocês podem perguntar.
                                                                // porque vamos envitar executar o que está aqui embaixo se o produto não existir

                                                                //tudo isso que está sendo feito aqui embaixo deveria ser feito apenas se o
                                                                //produto existisse, mas não há garantia disso nesse código, ou seja,
                                                                //a gente tem vários pontos de lançamento de NullPointerException
                                                                println("Digite a quantidade desejada do produto: ")
                                                                val productOrderQuantity: Float = readln().toFloat() // ainda acho que deveria ser Int

                                                                //se você já tem o objeto recuperado no find, por que estão acessando ele pelo índice?
                                                                if(productOrderQuantity > productsList[productIndex].qtd){
                                                                    println("Quantidade desejada indisponível no estoque!")
                                                                    //para isso funcionar, aqui deveria ter um continue
                                                                }

                                                                //isso vai ser executado, mesmo que a quantidade não atenda o critério da quantidade
                                                                productsList[productIndex].qtd -= productOrderQuantity

                                                                //por que não usamos o find??
                                                                //esse uso do indexOfFirst é bem ruim porque vocês tem que ficar manipulando indices toda hora
                                                                val orderIndex: Int = ordersList.indexOfFirst { it.orderNum == orderNum }

                                                                //os nomes aqui não fazem sentido nenhum
                                                                //esse newOrderAmount deveria ser o subTotal, não?
                                                                //esse nome amount não condiz com o que é desejado
                                                                val newOrderAmount: Double = ordersList[orderIndex].amount + (productOrderQuantity * productsList[productIndex].price)

                                                                //o subtotal do pedido
                                                                ordersList[orderIndex].amount = newOrderAmount

                                                                //order.subtotal = order.subtotal + (productOrderQuantity * selectedProduct.price)

                                                                println("Adicionando produto ${productsList[productIndex].name} ao pedido...")

                                                                //O OrderItem deveria ter: o product, o productQuantity, o subtotal e o order
                                                                //OrderItem(selectedProduct, productQuantity, subtotal, order)
                                                                ordersItemsList.add(OrderItem(
                                                                    productCode = productsList[productIndex].productCode,
                                                                    productQuantity = productOrderQuantity,
                                                                    productSellPrice = productsList[productIndex].price,
                                                                    orderNum = orderNum
                                                                ))
                                                                =======
                                                                when(selectedOrderProduct){
                                                                    in 1..(productsList.size + 1) -> {
                                                                        val productIndex: Int = productsList.indexOfFirst {it.productCode == selectedOrderProduct}

                                                                        val productExist: Product? = productsList.find { it.productCode == selectedOrderProduct }

                                                                        if(productExist == null){
                                                                            println("Produto selecionado não encontrado!")
                                                                        }

                                                                        println("Digite a quantidade desejada do produto: ")
                                                                        val productOrderQuantity: Int = readln().toInt()

                                                                        if(productOrderQuantity > productsList[productIndex].qtd){
                                                                            println("Quantidade desejada indisponível no estoque!")
                                                                        }

                                                                        productsList[productIndex].qtd -= productOrderQuantity

                                                                        val orderIndex: Int = ordersList.indexOfFirst { it.orderNum == orderNum }

                                                                        val newOrderAmount: Double = ordersList[orderIndex].amount + (productOrderQuantity * productsList[productIndex].price)

                                                                        ordersList[orderIndex].amount = newOrderAmount

                                                                        println("Adicionando produto ${productsList[productIndex].name} ao pedido...")

                                                                        addOrderItem(data = OrderItem(
                                                                            productCode = productsList[productIndex].productCode,
                                                                            productQuantity = productOrderQuantity,
                                                                            productSellPrice = productsList[productIndex].price,
                                                                            orderNum = orderNum
                                                                        ))
                                                                    }
                                                                    else ->{
                                                                        println("Voltando...")
                                                                    }
                                                                }
                                                                >>>>>>> ab48e3f0d3da864aa52e1092f2fe64128c601d58
                                                            }
                                                        }
                                                    }
                                                    2 -> {
                                                        //você poderia criar um OrderItem
                                                        val doesOrderListHaveMoreThanZeroProduct: Boolean = ordersItemsList.none { it.orderNum == orderNum }

                                                        //nesses casos, o ideal é vocês usarem o if.
                                                        // o if deve ser usado quando você tem uma condição e o else apenas.

                                                        /*
                                                            desta forma é mais idiomático

                                                            if(order.items().isEmpty()) {
                                                                println("O mínimo de produtos por pedido é 1")
                                                                println("Voltando ao menu do pedido...")
                                                            } else {
                                                                executa o pagamento
                                                            }
                                                        */

                                                        when{
                                                            doesOrderListHaveMoreThanZeroProduct -> {
                                                                println("O mínimo de produtos por pedido é 1")
                                                                println("Voltando ao menu do pedido...")
                                                            }
                                                            else ->{
                                                                println("Você possui algum cupom de desconto? Sim (S) - Não (N)")
                                                                var doesHaveADiscountCoupon: String? = readlnOrNull()

                                                                // acho interessante vocês terem criado uma lista de cupons.
                                                                //mas acho que se vocês fizeram isso então deveriam verificar se ela está vazia
                                                                // antes de solicitar o cupom, não dando opção se ela tiver vazia
                                                                if(discountCouponsList.isEmpty()){
                                                                    println("Desculpe! Não temos cupons de desconto dispiníveis no momento.")
                                                                    doesHaveADiscountCoupon = null
                                                                }

                                                                //esse when deveria ser um if e else
                                                                when{
                                                                    doesHaveADiscountCoupon == null || doesHaveADiscountCoupon.isEmpty() || doesHaveADiscountCoupon == "N" -> {
                                                                        println("Finalizando pedido...")

                                                                        //eu acho que aqui vocês deveria ter criado o pedido nesse momento, mas mesmo assim
                                                                        // vocês poderiam utilizar o find no lugar do indexOfFirst
                                                                        //val order = orders.find{it.orderNumber == orderNum}
                                                                        val orderIndex: Int = ordersList.indexOfFirst { it.orderNum == orderNum }

                                                                        //aqui poderia ser:
                                                                        //order.status = OrderStatus.ACEITO
                                                                        ordersList[orderIndex].status = OrderStatus.ACEITO

                                                                        println("Pedido de número $orderNum gerado.")
                                                                        println("Status do pedido: ${ordersList[orderIndex].status} | Valor Total: ${ordersList[orderIndex].amount}")
                                                                        //forçar a saída depois de criado o pedido, certo?
                                                                        selectedCreateOrder = 4
                                                                    }
                                                                    else -> {
                                                                        //poderia ter usado o find
                                                                        val orderIndex: Int = ordersList.indexOfFirst { it.orderNum == orderNum }

                                                                        println("Insira o cupom de desconto:")

                                                                        var discountCoupon: String? = null

                                                                        var discountCouponExists: DiscountCoupon? = null

                                                                        while (discountCouponExists == null){
                                                                            discountCoupon = readlnOrNull()

                                                                            discountCouponExists = discountCouponsList.find {it.name.equals(discountCoupon, ignoreCase = true)}

                                                                            //de novo, nesses casos onde temos apenas uma condição e um else, o ideal e idiomático é usar o if.
                                                                            //melhora a legibilidade
                                                                            when{

                                                                                discountCouponExists == null -> {
                                                                                    println("Cupom de desconto não encontrado")
                                                                                }
                                                                                else -> {
                                                                                    <<<<<<< HEAD

                                                                                    //essa variável pode ser declara como val, pois o valor do desconto não vai mudar
                                                                                    //a verificação se o valor é null ou não não precisa ter, pois você já fez anteriormente
                                                                                    // val discount = discountCouponExists.discount
                                                                                    var discount: Float? = discountCouponExists?.discount
                                                                                    =======
                                                                                    val discount: Float? = discountCouponExists?.discount
                                                                                    >>>>>>> ab48e3f0d3da864aa52e1092f2fe64128c601d58

                                                                                    val orderAmount: Double = ordersList[orderIndex].amount

                                                                                    //poderia ter recuperado o order com o find
                                                                                    ordersList[orderIndex].amount = orderAmount - ((orderAmount * discount!!) / 100)

                                                                                    //recuperar o order com o find
                                                                                    ordersList[orderIndex].status = OrderStatus.ACEITO

                                                                                    println("Pedido de número $orderNum gerado.")
                                                                                    println("Status do pedido: ${ordersList[orderIndex].status} | Valor Total: ${ordersList[orderIndex].amount}")
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

                                        //bom uso do filter
                                        // não precisava especificar o tipo da variável, pois o kotlin faria a inferência do tipo de dados
                                        val availableOrdersToChangeStatus: List<Order> = ordersList.filter {it.status != OrderStatus.ENTREGUE}

                                        //aqui seria melhor fazer um if.. else, melhoraria a legibilidade do código.
                                        /*  if(availableOrdersToChangeStatus.isEmpty()) {
                                                println("Sem pedidos disponiveis para alterar o status.")
                                            } else {
                                                toda a lógica
                                            } */

                                        when{
                                            availableOrdersToChangeStatus.isEmpty() -> println("Sem pedidos disponiveis para alterar o status.")
                                            else -> {
                                                println("--- Lista de pedidos disponiveis ---")
                                                println("Escolha um pedido pelo codigo:")

                                                availableOrdersToChangeStatus.forEach { println("Codigo: ${it.orderNum} | Valor Total: ${it.amount} | Status: ${it.status}") }

                                                val selectedOrder: Int = readln().toInt()

                                                //deveria ter usado o find
                                                val orderIndex: Int = ordersList.indexOfFirst { it.orderNum == selectedOrder }

                                                println("--- Lista de Status ---")

                                                <<<<<<< HEAD
                                                //aqui você poderia imprimir todos os status do enum, nesse esquema de indexado:
                                                //OrderStatus.values().forEachIndexed{ index, status -> println("${index+1} - $status")}

                                                println("1 - ACEITO")
                                                println("2 - FAZENDO")
                                                println("3 - FEITO")
                                                println("4 - ESPERANDO_ENTREGADOR")
                                                println("5 - SAIU_PARA_ENTREGA")
                                                println("6 - ENTREGUE")
                                                val selectedStatus: Int = readln().toInt()


                                                //isso aqui poderia ser simplificado, retirando o println()
                                                //vocês usariam o when como expressão como estão fazendo ai, mas o print ficaria depois do
                                                // when, ele seria mais ou menos assim:
                                                //println("Alterando status para $order.status")
                                                ordersList[orderIndex].status =  when (selectedStatus){
                                                        =======
                                                        OrderStatus.entries.forEachIndexed { index, value -> print("${index + 1} - $value") }

                                                        val selectedStatus: Int = readln().toInt()

                                                    ordersList[orderIndex].status = when(selectedStatus){
                                                            >>>>>>> ab48e3f0d3da864aa52e1092f2fe64128c601d58
                                                        1 -> {
                                                            OrderStatus.ACEITO
                                                        }

                                                        2 -> {
                                                            <<<<<<< HEAD
                                                            println("Alterando status para FAZENDO")
                                                            =======
                                                            >>>>>>> ab48e3f0d3da864aa52e1092f2fe64128c601d58
                                                            OrderStatus.FAZENDO
                                                        }

                                                        3 -> {
                                                            <<<<<<< HEAD
                                                            println("Alterando status para FEITO")
                                                            =======
                                                            >>>>>>> ab48e3f0d3da864aa52e1092f2fe64128c601d58
                                                            OrderStatus.FEITO
                                                        }

                                                        4 -> {
                                                            <<<<<<< HEAD
                                                            println("Alterando status para ESPERANDO_ENTREGADOR")
                                                            =======
                                                            >>>>>>> ab48e3f0d3da864aa52e1092f2fe64128c601d58
                                                            OrderStatus.ESPERANDO_ENTREGADOR
                                                        }

                                                        5 -> {
                                                            <<<<<<< HEAD
                                                            println("Alterando status para SAIU_PARA_ENTREGA")
                                                            =======
                                                            >>>>>>> ab48e3f0d3da864aa52e1092f2fe64128c601d58
                                                            OrderStatus.SAIU_PARA_ENTREGA
                                                        }

                                                        6 -> {
                                                            <<<<<<< HEAD
                                                            println("Alterando status para ENTREGUE")
                                                            OrderStatus.ENTREGUE
                                                        }
                                                        else -> {
                                                            println("Opção inválida. Nenhuma alteração será realizada.")
                                                            null
                                                        }
                                                    }
                                                            =======
                                                        OrderStatus.ENTREGUE
                                                }

                                                else -> println("Opção inválida. Nenhuma alteração será realizada.")
                                            } as OrderStatus

                                            println("Pedido atualizado: Status =  ${ordersList[orderIndex].status}")
                                                    >>>>>>> ab48e3f0d3da864aa52e1092f2fe64128c601d58

                                            //o print deveria ficar aqui:
                                            //println("Alterado status para $order.status")


                                        }
                                    }
                                    }
                                    5 -> {
                                        //muito bem: o uso adequado do if e else
                                        if (ordersList.isEmpty()) {
                                            println("Nenhum pedido cadastrado.")
                                        } else {
                                            println("*** Exibindo todos os pedidos ***")
                                            //aqui vocês deveriam ter usado o forEach
                                            //orders.forEach{}

                                            for (i in 0..ordersList.size - 1) {
                                                val pedido = ordersList[i]
                                                println("Pedido numero: ${pedido.orderNum}")
                                                println("Status: ${pedido.status}")
                                                println("Total: R$ ${pedido.amount}")

                                                println("Itens do pedido:")
                                                var Item = false
                                                //era para fazer o forEach
                                                for (item in ordersItemsList) {
                                                    if (item.orderNum == pedido.orderNum) {
                                                        println(" - Produto Código: ${item.productCode} - Quantidade: ${item.productQuantity} - Preço: R$${item.productSellPrice}")
                                                        Item = true
                                                    }
                                                }
                                                if (!Item) {
                                                    println("Esse pedido não tem itens")
                                                }
                                            }
                                        }
                                    }
                                    6 -> {
                                        println("Digite o nome do Cupom de desconto: ")
                                        var couponName: String? = null


                                        var couponAlreadyExists: DiscountCoupon? = null

                                        //ficou confuso de ser lido
                                        while(couponName == null || couponName.isEmpty() || couponAlreadyExists != null){
                                            print("-> ")
                                            couponName = readlnOrNull()
                                            if(couponName == null || couponName.isEmpty()){
                                                println("O NOME do cupom inserido é inválido")
                                            }

                                            //bom uso do find
                                            couponAlreadyExists = discountCouponsList.find { it.name.equals(couponName, ignoreCase = true) }

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