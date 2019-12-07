package kr.co.papercraft.eatgo.interfaces;

import kr.co.papercraft.eatgo.application.RegionService;
import kr.co.papercraft.eatgo.domain.Model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionController {

    @Autowired
    RegionService regionService;

    @GetMapping("/regions")
    public List<Region> list(){
        return regionService.getRegions();
    }

}
