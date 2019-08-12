package tachyonis.space;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import tachyonis.space.entity.Location;
import tachyonis.space.entity.Weather;
import tachyonis.space.repository.WeatherRepository;

@Controller
public class WeatherController {
    WeatherRepository weatherRepository;
    public WeatherController( WeatherRepository weatherRepository ){
        this.weatherRepository = weatherRepository;
    }

    @GetMapping("/get")
    public String getTemp(Location location,  Weather weather, Model model, @RequestParam(name = "submitted", required = false) boolean submitted ) {
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
