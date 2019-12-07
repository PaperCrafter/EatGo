package kr.co.papercraft.eatgo.interfaces;

import kr.co.papercraft.eatgo.application.RegionService;
import kr.co.papercraft.eatgo.domain.Model.Region;
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
public class RegionController {

    @Autowired
    RegionService regionService;

    @GetMapping("/regions")
    public List<Region> list(){
        return regionService.getRegions();
    }

    @PostMapping("/regions")
    public ResponseEntity<?> create(@RequestBody Region resource) throws URISyntaxException {
        String name = resource.getName();

        Region region = regionService.addRegion(Region.builder()
                .name(name)
                .build());

        String url = "/regions/"+  region.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }

}
