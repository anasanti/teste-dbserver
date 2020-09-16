package desafio;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.DriveManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class APITest {
    @Test
    public void deveValidarRetornoDeAPIdoCarrinhodeCompras() throws IOException {
        String response = getResponseEfetuarChamadaAPI();
        Assert.assertEquals(
                "{\"shopping_cart\":1,\"sku\":[\"demo_2\",\"demo_1\",\"demo_7\"],\"color\":[\"Black\",\"Orange\",\"Yellow\"],\"size\":[\"S\",\"S\",\"S\"],\"price\":[\"27.00\",\"16.51\",\"16.40\"],\"total_shipping\":\"2.00\"}",
                response
        );
    }

    private String getResponseEfetuarChamadaAPI() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://5d9cc58566d00400145c9ed4.mockapi.io/shopping_cart").build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
