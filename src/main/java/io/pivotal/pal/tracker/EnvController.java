package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    private Map<String, String> env = new HashMap<>();
    public EnvController(@Value("${PORT:NOT SET}") String port, @Value("${MEMORY_LIMIT:NOT SET}") String memory_limit,@Value("${CF_INSTANCE_INDEX:NOT SET}") String instance_index,@Value("${CF_INSTANCE_ADDR:NOT SET}") String instance_add) {
        env.put("PORT",port);
        env.put("MEMORY_LIMIT",memory_limit);
        env.put("CF_INSTANCE_INDEX", instance_index);
        env.put("CF_INSTANCE_ADDR", instance_add);
    }
    @GetMapping("/env")
    public  Map<String, String> getEnv(){
        return env;

    }
}
