package kr.co.papercraft.eatgo.application;

import kr.co.papercraft.eatgo.domain.Model.Category;
import kr.co.papercraft.eatgo.domain.Repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

public class CategoryServiceTest {

    @Mock
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    public void getRegions(){
        List<Category> mockCategories = categoryService.getCategories();
        mockCategories.add(Category.builder().name("Korean Food").build());
        given(categoryRepository.findAll()).willReturn(mockCategories);

        List<Category> categories = categoryService.getCategories();

        Category category= categories.get(0);

        assertThat(category.getName(), is("Korean Food"));

    }

}