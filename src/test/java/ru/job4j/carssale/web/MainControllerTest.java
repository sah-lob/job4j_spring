package ru.job4j.carssale.web;
import com.google.common.collect.Lists;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.carssale.domain.Car;
import ru.job4j.carssale.domain.CarFilter;
import ru.job4j.carssale.domain.Person;
import ru.job4j.carssale.persistence.CarController;
import java.util.ArrayList;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "a", roles = "USER")
public class MainControllerTest {

    @Autowired
    private MockMvc mvc;


    @MockBean
    private CarController carController;

    @Test
    public void whenGetOneCarWithoutParams() throws Exception {
        given(this.carController.carsFindToPage(0)).willReturn(
                new ArrayList<>(
                        Lists.newArrayList(new Car("Ford",
                                "Mondeo",
                                1_900_000,
                                false,
                                240,
                                2019,
                                "Alexander"))
                )
        );
        this.mvc.perform(
                get("/json")
                        .param("page", "0")
                        .param("carID", "")
                        .accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string(containsString("\"brand\":\"Ford\""))
        ).andExpect(
                content().string(containsString("\"model\":\"Mondeo\""))
        ).andExpect(
                content().string(containsString("\"price\":1900000"))
        ).andExpect(
                content().string(containsString("\"power\":240"))
        );
    }

    @Test
    public void whenGetOneCarWithParams() throws Exception {
        var params = new ArrayList<String>();
        params.add("0");
        params.add("1");
        params.add("Ford");
        params.add("");
        params.add("");
        params.add("");
        params.add("");
        params.add("");
        params.add("");
        params.add("");
        params.add("");
        var carFilter = new CarFilter(params);

        given(this.carController.carsParamFindPage(carFilter)).willReturn(
                new ArrayList<>(
                        Lists.newArrayList(new Car("1", "Ford",
                                "Mondeo",
                                1_900_000,
                                false,
                                240,
                                2019,
                                "Alexander"))
                )
        );

        this.mvc.perform(
                get("/json")
                        .param("page", "0")
                        .param("carID", "1")
                        .param("brand", "Ford")
                        .param("model", "")
                        .param("priceFrom", "")
                        .param("priceTo", "")
                        .param("korobka", "")
                        .param("powerFrom", "")
                        .param("powerTo", "")
                        .param("yearFrom", "")
                        .param("yearTo", "")
                        .accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string(containsString("\"brand\":\"Ford\""))
        ).andExpect(
                content().string(containsString("\"model\":\"Mondeo\""))
        ).andExpect(
                content().string(containsString("\"price\":1900000"))
        ).andExpect(
                content().string(containsString("\"power\":240"))
        );
    }

    @Test
    public void whenGetImages() throws Exception {
        given(this.carController.getImg(0)).willReturn(
                "image"
        );
        this.mvc.perform(
                get("/img").param("id", "0").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string("[{\"id\":\"image\"}]")
        );
    }

    @Test
    public void whenGetAllBrands() throws Exception {
        given(this.carController.getBrands()).willReturn(
                new ArrayList<String>(
                        Lists.newArrayList("Ford")
                )
        );
        this.mvc.perform(
                get("/brand").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string("[{\"id\":0,\"brand\":{\"brand2\":\"Ford\"}}]")
        );
    }

    @Test
    public void whenGetAllModelsFromBrandFord() throws Exception {
        given(this.carController.getModels("Ford")).willReturn(
                new ArrayList<String>(
                        Lists.newArrayList("Mondeo")
                )
        );
        this.mvc.perform(
                get("/model").accept(MediaType.TEXT_HTML).param("br", "Ford")
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string("[{\"id\":0,\"model\":{\"model2\":\"Mondeo\"}}]")
        );
    }

    @Test
    public void whenSelectCar() throws Exception {
        Person person = new Person();
        person.setFio("Alexander");
        person.setPhone("phone");
        given(this.carController.findCar(0, 0)).willReturn(
                new Car("Ford", "Mondeo", "1900000", "m", "240", "2019", "Alexander")
        );
        given(this.carController.getPerson("Alexander")).willReturn(
                person
        );
        this.mvc.perform(
                get("/select")
                        .param("id", "0")
                        .param("page", "0")
                        .accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string(containsString("\"brand\":\"Ford\""))
        ).andExpect(
                content().string(containsString("\"model\":\"Mondeo\""))
        ).andExpect(
                content().string(containsString("\"price\":1900000"))
        ).andExpect(
                content().string(containsString("\"power\":240"))
        );
    }

    @Test
    public void whenAddNewCar() throws  Exception {
        this.mvc.perform(
                post("/newCar")
                        .param("string", "image")
                        .param("brand", "Ferrari")
                        .param("model", "California")
                        .param("price", "20000")
                        .param("korobka", "m")
                        .param("power", "450")
                        .param("year", "2019")
                        .param("fio", "Alexander")
                        .param("phone", "iphone")
        ).andExpect(status().is3xxRedirection());
    }
}