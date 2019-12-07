package kr.co.papercraft.eatgo.application;

import kr.co.papercraft.eatgo.domain.MenuItem;
import kr.co.papercraft.eatgo.domain.MenuItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
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
}