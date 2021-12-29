package me.song.transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class SampleController {

    @Autowired
    SampleServiceInterface sampleService;

    @PostMapping(value = "/sample")
    public int createSample(@RequestBody Sample sample) throws MyException {
        System.out.println("Sample Method 1 Start >>>>>");
        return sampleService.createSampleMethod(sample);
    }

    @GetMapping(value = "/sample/{name}")
    public List<Sample> findSample(@PathVariable("name") String name){
        return sampleService.findAllSampleByName(name);
    }


}
