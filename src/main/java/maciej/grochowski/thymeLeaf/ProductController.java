package maciej.grochowski.thymeLeaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("listProduct", productService.getAllProducts());
        return "index";
    }

    @GetMapping("/showNewProductForm")
    public String showNewProductForm (Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "new_product";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute("product") Product product){
        productService.addProduct(product);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(Model model, @PathVariable (value = "id") long id){
        Optional<Product> product = productService.findProdById(id);
        model.addAttribute("product", product);
        return "update_product";
    }

    @GetMapping("/showFormForDelete/{id}")
    public String showFormForDelete(@PathVariable long id){
        productService.deleteProduct(id);
        return "redirect:/";
    }

}
