package kr.co.papercraft.eatgo.interfaces;

import kr.co.papercraft.eatgo.application.CategoryService;
import kr.co.papercraft.eatgo.domain.Model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> list(){
        return categoryService.getCategories();
    }

}
