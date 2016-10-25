package com.somo.userPersonalisation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserControllerTest {

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    String userId = "john_smith";

    UserSettings settings = new UserSettings(userId, "backGroundColor");

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        UserController controller = new UserController(userService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetUserSettingsAsynch() throws Exception {

        Mockito.when(userService.getUserSettingsById(userId)).thenReturn(Optional.of(settings));

        MvcResult mvcResult = this.mockMvc.perform(get("/userSettings/" + userId))
                .andExpect(request().asyncStarted())
                .andReturn();

        assertEquals(settings,mvcResult.getAsyncResult());

        this.mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{'userId':" + userId + "}"))
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.backGroundStyle").value("backGroundColor"));

        verify(userService).getUserSettingsById(userId);
    }
}