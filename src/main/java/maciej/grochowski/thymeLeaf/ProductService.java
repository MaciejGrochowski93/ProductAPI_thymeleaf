package maciej.grochowski.thymeLeaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findProdById(long id) {
        return productRepository.findById(id);
    }

    public List<Product> findProdByName(String name) {
        ArrayList<Product> products = new ArrayList<>();
        productRepository
                .findAll()
                .stream()
                .filter(p -> p.getName().toLowerCase().contains(name))
                .forEach(products::add);
        return products;
    }

    public List<Product> findProdByBrand(String brand) {
        ArrayList<Product> products = new ArrayList<>();
        productRepository
                .findAll()
                .stream()
                .filter(p -> p.getBrand().toLowerCase().equals(brand))
                .forEach(products::add);
        return products;
    }

    public List<Product> findProdByCategory(String category) {
        ArrayList<Product> products = new ArrayList<>();
        productRepository
                .findAll()
                .stream()
                .filter(p -> p.getCategory().toLowerCase().equals(category))
                .forEach(products::add);
        return products;
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public void deleteProduct(long id){
        productRepository.deleteById(id);
    }
}
