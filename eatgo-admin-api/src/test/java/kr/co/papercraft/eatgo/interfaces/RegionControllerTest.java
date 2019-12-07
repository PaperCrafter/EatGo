package kr.co.papercraft.eatgo.interfaces;

import kr.co.papercraft.eatgo.application.RegionService;
import kr.co.papercraft.eatgo.domain.Model.Region;
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
@WebMvcTest(RegionController.class)
public class RegionControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    RegionService regionService;


    @Test
    public void list() throws Exception {
        List<Region> regions = new ArrayList<>();
        regions.add(Region.builder().name("Seoul").build());
        given(regionService.getRegions()).willReturn(regions);

        mvc.perform(get("/regions"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Seoul")));
    }

    @Test
    public void filteredList() throws Exception {
        List<Region> regions = new ArrayList<>();
        regions.add(Region.builder().name("Seoul").build());
        given(regionService.getRegions()).willReturn(regions);

        mvc.perform(get("/regions"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Seoul")));
    }

    @Test
    public void create() throws Exception {
        Region region = Region.builder().id(1234L).name("Seoul").build();
        given(regionService.addRegion(any())).willReturn(region);

        mvc.perform(post("/regions")
                .content("{\"name\":\"Seoul\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/regions/1234"))
                .andExpect(content().string("{}"));

        //verify(regionService.addRegion(region).toString(), is());
    }
}