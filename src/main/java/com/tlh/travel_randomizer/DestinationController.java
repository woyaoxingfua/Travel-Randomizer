package com.tlh.travel_randomizer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List; // 需要在文件顶部引入 List
import org.springframework.web.bind.annotation.PathVariable; // 需要引入 PathVariable


@RestController
@RequestMapping("/api/destinations")
@CrossOrigin
public class DestinationController {

    @Autowired
    private DestinationRepository destinationRepository;

    @GetMapping("/random")
    public ResponseEntity<Destination> getRandomDestination() {
        return destinationRepository.findRandom()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/info/{provinceName}")
    public ResponseEntity<List<Destination>> getInfoByProvince(@PathVariable String provinceName) {
        List<Destination> destinations = destinationRepository.findByProvince(provinceName);
        if (destinations == null || destinations.isEmpty()) {
            // 如果找不到任何数据，返回 404 Not Found
            return ResponseEntity.notFound().build();
        }
        // 如果找到了，返回 200 OK 和数据列表
        return ResponseEntity.ok(destinations);
    }


    // ... getInfoByProvince(...) 方法的下方 ...

    // 全新的API接口，用于根据城市名获取详细信息
    @GetMapping("/info/city/{cityName}")
    public ResponseEntity<List<Destination>> getInfoByCity(@PathVariable String cityName) {
        List<Destination> destinations = destinationRepository.findByCity(cityName);
        if (destinations == null || destinations.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(destinations);
    }
}