package ru.job4j.carssale.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.carssale.models.Car;
import ru.job4j.carssale.models.CarFilter;
import ru.job4j.carssale.models.Person;
import ru.job4j.carssale.persistence.CarController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@Controller
public class MainController {

    @Autowired
    private CarController controller;

    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public void indexGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");
        var pw = resp.getWriter();
        var page = Integer.valueOf(req.getParameter("page"));
        var carId = req.getParameter("carID");
        boolean filterCarID = (carId.equals("undefined") || carId.equals(""));
        List<Car> cars;
        var newID = -2;
        CarFilter carFilter = null;
        if (!filterCarID) {
            List<String> params = new ArrayList<>();
            params.add(req.getParameter("page"));
            params.add(req.getParameter("carID"));
            params.add(req.getParameter("brand"));
            params.add(req.getParameter("model"));
            params.add(req.getParameter("priceFrom"));
            params.add(req.getParameter("priceTo"));
            params.add(req.getParameter("korobka"));
            params.add(req.getParameter("powerFrom"));
            params.add(req.getParameter("powerTo"));
            params.add(req.getParameter("yearFrom"));
            params.add(req.getParameter("yearTo"));

            carFilter = new CarFilter(params);
            cars = controller.carsParamFindPage(carFilter);

            if (!cars.isEmpty()) {
                newID = cars.get(cars.size() - 1).getId();
            }
        } else {
            cars = controller.carsFindToPage(page);
        }

        var objectMapper = new ObjectMapper();
        var arrayJson = objectMapper.createArrayNode();
        for (var entry : cars) {
            var node = objectMapper.createObjectNode();
            var arrayNode = objectMapper.createArrayNode();
            node.put("id", entry.getId());
            var innerNode = objectMapper.createObjectNode();
            var entry2 = entry;
            innerNode.put("id", entry2.getId());
            innerNode.put("brand", entry2.getBrand());
            innerNode.put("model", entry2.getModel());
            innerNode.put("price", entry2.getPrice());
            innerNode.put("mechanicGear", entry2.isMechanicGear());
            innerNode.put("power", entry2.getPower());
            innerNode.put("year", entry2.getYear());
            arrayNode.add(innerNode);
            node.set("car", innerNode);
            if (!filterCarID) {
                node.put("len", controller.carsGetParamFindPageSize(carFilter));
            } else {
                node.put("len", controller.carSize());
            }
            node.put("newId", newID);
            arrayJson.add(node);
        }
        pw.append(objectMapper.writeValueAsString(arrayJson));
        pw.flush();
    }

    @RequestMapping(value = "/img", method = RequestMethod.GET)
    public void imgGet(@RequestParam(name = "id") String carId,  HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");
        var pw = resp.getWriter();
        var image = controller.getImg(Integer.parseInt(carId));
        var objectMapper = new ObjectMapper();
        var arrayJson = objectMapper.createArrayNode();
        var node = objectMapper.createObjectNode();
        var arrayNode = objectMapper.createArrayNode();
        node.put("id", image);
        var innerNode = objectMapper.createObjectNode();
        arrayNode.add(innerNode);
        arrayJson.add(node);
        pw.append(objectMapper.writeValueAsString(arrayJson));
        pw.flush();
    }

    @RequestMapping(value = "/brand", method = RequestMethod.GET)
    public void brandGet(HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");
        var pw = resp.getWriter();
        var brands = controller.getBrands();
        var brandMap = new TreeMap<Integer, String>();
        var i = 0;
        for (var brand : brands) {
            brandMap.put(i++, brand);
        }
        var objectMapper = new ObjectMapper();
        var arrayJson = objectMapper.createArrayNode();
        for (var entry : brandMap.entrySet()) {
            var node = objectMapper.createObjectNode();
            var arrayNode = objectMapper.createArrayNode();
            node.put("id", entry.getKey());
            var innerNode = objectMapper.createObjectNode();
            var entry2 = entry.getValue();
            innerNode.put("brand2", entry2);
            arrayNode.add(innerNode);
            node.set("brand", innerNode);
            arrayJson.add(node);
        }
        pw.append(objectMapper.writeValueAsString(arrayJson));
        pw.flush();
    }

    @RequestMapping(value = "/model", method = RequestMethod.GET)
    public void modelGet(@RequestParam(name = "br") String brand, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");
        var pw = resp.getWriter();
        if (!brand.equals("-1")) {
            var models = controller.getModels(brand);
            var modelMap = new TreeMap<Integer, String>();
            var i = 0;
            for (var model : models) {
                modelMap.put(i++, model);
            }
            var objectMapper = new ObjectMapper();
            var arrayJson = objectMapper.createArrayNode();
            for (var entry : modelMap.entrySet()) {
                var node = objectMapper.createObjectNode();
                var arrayNode = objectMapper.createArrayNode();
                node.put("id", entry.getKey());
                var innerNode = objectMapper.createObjectNode();
                var entry2 = entry.getValue();
                innerNode.put("model2", entry2);
                arrayNode.add(innerNode);
                node.set("model", innerNode);
                arrayJson.add(node);
            }
            pw.append(objectMapper.writeValueAsString(arrayJson));
            pw.flush();
        }
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public void selectGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");
        int page = Integer.parseInt(req.getParameter("page"));
        int id = Integer.parseInt(req.getParameter("id"));
        var car = controller.findCar(page, id);
        var pw = resp.getWriter();
        var person = controller.getPerson(car.getOwner());
        var objectMapper = new ObjectMapper();
        var arrayJson = objectMapper.createArrayNode();
        var node = objectMapper.createObjectNode();
        var arrayNode = objectMapper.createArrayNode();
        node.put("id", 0);
        var innerNode = objectMapper.createObjectNode();
        innerNode.put("id", car.getId());
        innerNode.put("brand", car.getBrand());
        innerNode.put("model", car.getModel());
        innerNode.put("price", car.getPrice());
        innerNode.put("mechanicGear", car.isMechanicGear());
        innerNode.put("power", car.getPower());
        innerNode.put("year", car.getYear());
        innerNode.put("owner", person.getFio());
        innerNode.put("phone", person.getPhone());
        arrayNode.add(innerNode);
        node.set("car", innerNode);
        arrayJson.add(node);
        pw.append(objectMapper.writeValueAsString(arrayJson));
        pw.flush();
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newPost(HttpServletRequest req) {
        var image = req.getParameter("string");
        var brand = req.getParameter("brand");
        var model = req.getParameter("model");
        var price = req.getParameter("price");
        var korobka = req.getParameter("korobka");
        var power = req.getParameter("power");
        var year = req.getParameter("year");
        var fio = req.getParameter("fio");
        var phone = req.getParameter("phone");
        image = image.replaceAll(" ", "+");
        var login = SecurityContextHolder.getContext().getAuthentication().getName();
        controller.editPerson(login, fio, phone);
        var car = new Car(brand, model, price, korobka, power, year, login);
        controller.addData(car, image);
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String json(@RequestParam(value = "login1", required = false) String e, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            System.out.println("ошибочка вышла");
        }
        return "login";
    }

    @RequestMapping(value = "/loginValidate", method = RequestMethod.POST)
    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var sb = new StringBuilder();
        try (var reader = req.getReader()) {
            if (reader != null) {
                sb.append(reader.readLine());
            }
        }
        var mapper = new ObjectMapper();
        var person = mapper.readValue(sb.toString(), Person.class);
        if (person.getDescription().equals("N")) {
            if (controller.userIsExists(person.getLogin())) {
                var s = "Данный логин уже зарегистрирован.";
                resp.getWriter().write(s);
            } else {
                controller.addPerson(person);
            }
        } else {
            if (!controller.validatePerson(person)) {
                if (!controller.userIsExists(person.getLogin())) {
                    var s = "Такого логина нет!";
                    resp.getWriter().write(s);
                } else {
                    var s = "Неправильный пароль.";
                    resp.getWriter().write(s);
                }
            }
        }
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/selectCar", method = RequestMethod.GET)
    public String select() {
        return "selectCar";
    }

    @RequestMapping(value = "/newCar", method = RequestMethod.GET)
    public String newCar() {
        return "newCar";
    }
}
