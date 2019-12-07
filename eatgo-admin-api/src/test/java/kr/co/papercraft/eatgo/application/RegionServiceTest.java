package kr.co.papercraft.eatgo.application;

import kr.co.papercraft.eatgo.domain.Model.Region;
import kr.co.papercraft.eatgo.domain.Repository.RegionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

public class RegionServiceTest {

    @Mock
    private RegionService regionService;

    @Mock
    private RegionRepository regionRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        regionService = new RegionService(regionRepository);
    }

    @Test
    public void getRegions(){
        List<Region> mockRegions = regionService.getRegions();
        mockRegions.add(Region.builder().name("Seoul").build());
        given(regionRepository.findAll()).willReturn(mockRegions);

        List<Region> regions = regionService.getRegions();

        Region region = regions.get(0);

        assertThat(region.getName(), is("Seoul"));

    }

    @Test
    public void addRegions(){
        Region mockRegion = Region.builder().name("Seoul").build();
        given(regionService.addRegion(mockRegion)).willReturn(mockRegion);

        Region region = regionService.addRegion(mockRegion);
        assertThat(region.getName(), is("Seoul"));
    }

}