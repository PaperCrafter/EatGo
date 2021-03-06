package kr.co.papercraft.eatgo.interfaces;

import kr.co.papercraft.eatgo.application.CategoryService;
import kr.co.papercraft.eatgo.domain.Model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> list(){
        return categoryService.getCategories();
    }

    @PostMapping("/categories")
    public ResponseEntity<?> create(@RequestBody Category resource) throws URISyntaxException {
        String name = resource.getName();

        Category category = categoryService.addCategory(Category.builder()
                .name(name)
                .build());

        String url = "/categories/"+  category.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }

}
