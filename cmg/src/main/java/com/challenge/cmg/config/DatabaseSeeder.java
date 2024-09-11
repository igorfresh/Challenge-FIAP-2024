//package com.challenge.cmg.config;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//
//import com.challenge.cmg.model.Buy;
//import com.challenge.cmg.model.Client;
//import com.challenge.cmg.model.Product;
//import com.challenge.cmg.model.ProductCategory;
//import com.challenge.cmg.model.PurchasedItens;
//import com.challenge.cmg.repository.BuyRepository;
//import com.challenge.cmg.repository.ClientRepository;
//import com.challenge.cmg.repository.ProductCategoryRepository;
//import com.challenge.cmg.repository.ProductRepository;
//import com.challenge.cmg.repository.PurchasedItensRepository;
//
//@Configuration
//public class DatabaseSeeder implements CommandLineRunner {
//
//    @Autowired
//    BuyRepository buyRepository;
//
//    @Autowired
//    ClientRepository clientRepository;
//
//    @Autowired
//    ProductCategoryRepository productCategoryRepository;
//
//    @Autowired
//    ProductRepository productRepository;
//
//    @Autowired
//    PurchasedItensRepository purchasedItensRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        productCategoryRepository.saveAll(List.of(
//        ProductCategory.builder().id(1L).name("Teclados").build(),
//        ProductCategory.builder().id(2L).name("Mouses").build(),
//        ProductCategory.builder().id(3L).name("Headsets").build(),
//        ProductCategory.builder().id(4L).name("Monitor").build()
//        ));
//
//
//        productRepository.saveAll(List.of(
//            Product.builder()
//            .id(1L)
//            .name("Teclado Redragon Kumara")
//            .price(new BigDecimal(148.90))
//            .productCategory(productCategoryRepository.findById(1L).get())
//            .build(),
//            Product.builder()
//            .id(2L)
//            .name("Teclado HyperX Alloy")
//            .price(new BigDecimal(249.99))
//            .productCategory(productCategoryRepository.findById(1L).get())
//            .build(),
//            Product.builder()
//            .id(3L)
//            .name("Mouse HyperX Pulsefire")
//            .price(new BigDecimal(149.90))
//            .productCategory(productCategoryRepository.findById(2L).get())
//            .build(),
//            Product.builder()
//            .id(4L)
//            .name("Mouse Redragon Cobra")
//            .price(new BigDecimal(148.90))
//            .productCategory(productCategoryRepository.findById(2L).get())
//            .build(),
//            Product.builder()
//            .id(5L)
//            .name("Headset HyperX Cloud Stinger 2 Core")
//            .price(new BigDecimal(199.98))
//            .productCategory(productCategoryRepository.findById(3L).get())
//            .build(),
//            Product.builder()
//            .id(6L)
//            .name("Headset Razer Kraken X Lite")
//            .price(new BigDecimal(129.99))
//            .productCategory(productCategoryRepository.findById(3L).get())
//            .build(),
//            Product.builder()
//            .id(7L)
//            .name("Monitor LG Ultragear")
//            .price(new BigDecimal(1080.99))
//            .productCategory(productCategoryRepository.findById(4L).get())
//            .build(),
//            Product.builder()
//            .id(8L)
//            .name("Monitor Samsung Odyssey G3")
//            .price(new BigDecimal(1499.40))
//            .productCategory(productCategoryRepository.findById(4L).get())
//            .build()
//        ));
//
//        clientRepository.saveAll(List.of(
//            Client.builder()
//                .id(1L)
//                .name("João Pedro Costa Feitosa")
//                .cpf("987.654.321-00")
//                .email("joaopedrocostafeitosa@email.com")
//                .phone("(11) 99876-5432")
//                .adress("Rua do DevOps e Cloud, 305")
//                .city("São Caetano do Sul")
//                .state("SP")
//                .cep("09560-535")
//                .build(),
//                Client.builder()
//                .id(2L)
//                .name("Igor Miguel Silva")
//                .cpf("123.456.789-10")
//                .email("igormiguelsilva@email.com")
//                .phone("(11) 91234-5678")
//                .adress("Rua do Java Advanced, 306")
//                .city("São Paulo")
//                .state("SP")
//                .cep("01234-567")
//                .build(),
//                Client.builder()
//                .id(3L)
//                .name("Kaue Santana")
//                .cpf("246.810.121-41")
//                .email("kauesatana@email.com")
//                .phone("(11) 91122-3344")
//                .adress("Rua do Quality Assurance, 406")
//                .city("Barueri")
//                .state("SP")
//                .cep("06408-903")
//                .build()
//            ));
//
//            buyRepository.saveAll(List.of(
//                Buy.builder()
//                .id(1L)
//                .datePurchase(LocalDate.of(2018, 07, 22))
//                .purchaseStatus("ENTREGUE")
//                .totalPurchaseValue(new BigDecimal(300.53))
//                .client(clientRepository.findById(1L).get())
//                .build(),
//                Buy.builder()
//                .id(2L)
//                .datePurchase(LocalDate.of(2015, 05, 14))
//                .purchaseStatus("PENDENTE")
//                .totalPurchaseValue(new BigDecimal(300.53))
//                .client(clientRepository.findById(1L).get())
//                .build(),
//                Buy.builder()
//                .id(3L)
//                .datePurchase(LocalDate.of(2023, 10, 30))
//                .purchaseStatus("CANCELADA")
//                .totalPurchaseValue(new BigDecimal(300.53))
//                .client(clientRepository.findById(1L).get())
//                .build()
//
//            ));
//
//            purchasedItensRepository.saveAll(List.of(
//                PurchasedItens.builder()
//                .id(1L)
//                .quantityItens(3)
//                .unityPrice(new BigDecimal(55.90))
//                .product(productRepository.findById(1L).get())
//                .buy(buyRepository.findById(1L).get())
//                .build(),
//                PurchasedItens.builder()
//                .id(2L)
//                .quantityItens(10)
//                .unityPrice(new BigDecimal(427.98))
//                .product(productRepository.findById(3L).get())
//                .buy(buyRepository.findById(2L).get())
//                .build(),
//                PurchasedItens.builder()
//                .id(3L)
//                .quantityItens(3)
//                .unityPrice(new BigDecimal(27.98))
//                .product(productRepository.findById(3L).get())
//                .buy(buyRepository.findById(2L).get())
//                .build(),
//                PurchasedItens.builder()
//                .id(4L)
//                .quantityItens(2)
//                .unityPrice(new BigDecimal(270.98))
//                .product(productRepository.findById(2L).get())
//                .buy(buyRepository.findById(1L).get())
//                .build(),
//                PurchasedItens.builder()
//                .id(5L)
//                .quantityItens(1)
//                .unityPrice(new BigDecimal(300.00))
//                .product(productRepository.findById(2L).get())
//                .buy(buyRepository.findById(1L).get())
//                .build()
//            ));
//    }
//}
