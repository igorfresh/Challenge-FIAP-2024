package com.challenge.cmg.views;

import com.challenge.cmg.model.Product;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Route("")
public class HomeView extends VerticalLayout {

    private static final String PRODUCT_URL = "http://localhost:8080/product";

    public HomeView() {
        add(new H1("Navi"));
        add(new Paragraph("Tradutor de textos universais"));

        // Botão para carregar e exibir produtos
        Button loadProductsButton = new Button("Carregar Produtos", event -> {
            List<Product> products = fetchProducts();
            if (!products.isEmpty()) {
                openProductsDialog(products); // Abre um diálogo com a lista de produtos
            }
        });

        // Adiciona o botão na view
        add(loadProductsButton);
    }

    private List<Product> fetchProducts() {
        RestTemplate restTemplate = new RestTemplate();
        ProductResponse response = restTemplate.getForObject(PRODUCT_URL, ProductResponse.class);
        return response != null ? response.getContent() : List.of(); // Retorna a lista de produtos ou uma lista vazia
    }

    private void openProductsDialog(List<Product> products) {
        // Cria um diálogo para exibir a lista de produtos
        Dialog dialog = new Dialog();
        dialog.setWidth("600px");
        dialog.setHeight("400px");

        // Grid para exibir a lista de produtos
        Grid<Product> grid = new Grid<>(Product.class);
        grid.setItems(products); // Define os produtos a serem exibidos no Grid
        grid.removeColumnByKey("productCategory"); // Remove a coluna de categoria completa

        // Adiciona uma coluna para mostrar o nome da categoria
        grid.addColumn(product -> product.getProductCategory().getName()).setHeader("Categoria");

        // Botão para fechar o diálogo
        Button closeButton = new Button("Fechar", event -> dialog.close());

        // Adiciona o Grid e o botão de fechar ao layout do diálogo
        VerticalLayout dialogLayout = new VerticalLayout(grid, closeButton);
        dialog.add(dialogLayout);

        // Abre o diálogo
        dialog.open();
    }

    // Classe interna para mapear a estrutura da resposta da API
    private static class ProductResponse {
        private List<Product> content;

        public List<Product> getContent() {
            return content;
        }

        public void setContent(List<Product> content) {
            this.content = content;
        }
    }
}
