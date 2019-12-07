package kr.co.papercraft.eatgo.application;

import kr.co.papercraft.eatgo.domain.Model.MenuItem;
import kr.co.papercraft.eatgo.domain.Repository.MenuItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class MenuItemServiceTest {


    private MenuItemService menuItemService;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        menuItemService = new MenuItemService(menuItemRepository);
    }

    @Test
    public void bulkUpdate(){
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        menuItems.add(MenuItem.builder()
            .name("김치")
            .build());

        menuItems.add(MenuItem.builder()
                .id(12L)
                .name("감자탕")
                .build());

        menuItems.add(MenuItem.builder()
                .id(1L)
                .destroy(true)
                .build());
        menuItemService.bulkUpdate(1L, menuItems);

        verify(menuItemRepository, times(2)).save(any());
        verify(menuItemRepository, times(1)).deleteById(1L);
    }

    @Test
    public void getMenuItems(){
        List<MenuItem> mockMenuItems = new ArrayList<>();
        mockMenuItems.add(MenuItem.builder().name("국밥").build());

        given( menuItemService.findAllByRestaurantId(1004L)).willReturn(mockMenuItems);

        MenuItem menuItem = mockMenuItems.get(0);
        assertThat(menuItem.getName(), is("국밥"));
    }

}