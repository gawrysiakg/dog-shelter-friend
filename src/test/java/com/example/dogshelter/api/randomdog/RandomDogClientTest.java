package com.example.dogshelter.api.randomdog;
import com.example.dogshelter.api.randomDogApi.RandomDogClient;
import com.example.dogshelter.domain.api.RandomDog;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RandomDogClientTest {

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private RandomDogClient randomDogClient;

    @Test
    void shouldGetRandomDogByBreed(){
        //Given
        String breed = "labrador";
        RandomDog expectedRandomDog = new RandomDog();
        expectedRandomDog.setStatus("ok");
        expectedRandomDog.setMessage("https://dog.ceo/api/breed/labrador/images/random");
        when(restTemplate.getForObject("https://dog.ceo/api/breed/labrador/images/random", RandomDog.class))
                .thenReturn(expectedRandomDog);
        // when
        RandomDog randomDog = randomDogClient.getRandomDog(breed);
        // then
        assertNotNull(randomDog);
        assertEquals(expectedRandomDog.getStatus(), randomDog.getStatus());
        assertEquals(expectedRandomDog.getMessage(), randomDog.getMessage());
    }

    @Test
    public void testGetRandomDogWithInvalidBreed() {
        // given
        String breed = "invalidbreed";
        when(restTemplate.getForObject("https://dog.ceo/api/breed/invalidbreed/images/random", RandomDog.class))
                .thenThrow(new RuntimeException("Breed not found"));
        // when
        RuntimeException exception = assertThrows(RuntimeException.class, () -> randomDogClient.getRandomDog(breed));
        // then
        assertEquals("Breed not found", exception.getMessage());
    }
}
