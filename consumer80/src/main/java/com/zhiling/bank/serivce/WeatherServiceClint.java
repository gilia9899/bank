package com.zhiling.bank.serivce;

import com.zhiling.bank.entity.CommonResult;
import com.zhiling.bank.entity.Location;
import com.zhiling.bank.entity.Weather;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "weatherservice")
public interface WeatherServiceClint {

    @GetMapping("wc/getLocation")
    Location getLocation();

    @GetMapping("wc/getWeatherByAdcode")
    CommonResult getWeatherByAdcode();


}
