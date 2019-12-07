package kr.co.papercraft.eatgo.interfaces;

import kr.co.papercraft.eatgo.application.CategoryService;
import kr.co.papercraft.eatgo.domain.Model.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    CategoryService categoryService;


    @Test
    public void list() throws Exception {
        List<Category> categories = new ArrayList<>();
        categories.add(Category.builder().name("Korean Food").build());
        given(categoryService.getCategories()).willReturn(categories);

        mvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Korean Food")));
    }

    @Test
    public void create() throws Exception {
        Category category = Category.builder().id(1234L).name("Korean Food").build();
        given(categoryService.addCategory(any())).willReturn(category);

        mvc.perform(post("/categories")
                .content("{\"name\":\"Korean Food\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/categories/1234"))
                .andExpect(content().string("{}"));

        //verify(regionService.addRegion(region).toString(), is());
    }
}