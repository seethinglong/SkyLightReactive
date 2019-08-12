package tachyonis.space;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tachyonis.space.entity.Location;
import tachyonis.space.entity.Weather;
import tachyonis.space.repository.WeatherRepository;

@RestController
@RequestMapping("/api/weather")
public class LocationRestController {
    private WeatherRepository weatherRepository;
    public LocationRestController(WeatherRepository weatherRepository ){
        this.weatherRepository = weatherRepository;
    }

    @GetMapping("/get")
    public String getTemp(Location location, Weather weather, Model model) {
        model.addAttribute("location", new Location());
        model.addAttribute("weather", new Weather());
        return "weather/get";
    }
    @PostMapping("/view")
    public String setTemp(Location location,  Weather weather, Model model, RedirectAttributes ra) {
        Location result = this.weatherRepository.save(location);
        model.addAttribute("location", new Location());
        model.addAttribute("weather", new Weather());
        ra.addAttribute("submitted", true);
        return "redirect:weather/view";
    }

    @GetMapping("/all")
    public String getIssueReport(Location location,  Weather weather, Model model) {
        model.addAttribute("allWeather", this.weatherRepository.findAll());
        return "weather/get";
    }
}
