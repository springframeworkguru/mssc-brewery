package guru.springframework.msscbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @MockBean
    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private CustomerDto customerDto;

    @Before
    public void setUp() throws Exception {
        customerDto = CustomerDto.builder().id(UUID.randomUUID()).name("my customer").build();
    }

    @Test
    public void getCustomerById() throws Exception {
        given(customerService.getCustomerById(any(UUID.class))).willReturn(customerDto);

        String customerJson = objectMapper.writeValueAsString(customerDto);
        mockMvc.perform(get("/api/v1/customer/" + customerDto.getId().toString()))
                .andExpect(content().json(customerJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(customerDto.getId().toString())))
                .andExpect(jsonPath("$.name", is(customerDto.getName())));
    }

    @Test
    public void createCustomer() throws Exception {
        String customerJson = objectMapper.writeValueAsString(customerDto);
        given(customerService.createCustomer(any())).willReturn(customerDto);
        mockMvc.perform(post("/api/v1/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(header().stringValues("Location", "/api/v1/customer/" + customerDto.getId().toString()));
        then(customerService).should().createCustomer(any());
    }

    @Test
    public void updateCustomer() throws Exception {
        CustomerDto updatedDto = customerDto;
        updatedDto.setName("updated name");
        String customerString = objectMapper.writeValueAsString(updatedDto);
        given(customerService.updateCustomer(updatedDto.getId(), updatedDto)).willReturn(updatedDto);

        mockMvc.perform(put("/api/v1/customer/" + customerDto.getId().toString())
                .content(customerString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id", is(updatedDto.getId().toString())))
                .andExpect(jsonPath("$.name", is(updatedDto.getName())));
        then(customerService).should().updateCustomer(any(), any());
    }

    @Test
    public void deleteCustomer() throws Exception {

        mockMvc.perform(delete("/api/v1/customer/" + customerDto.getId().toString()))
                .andExpect(status().isOk());
        then(customerService).should().deleteCustomerById(any());
    }
}