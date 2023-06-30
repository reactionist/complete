package clientserver.network_protocol;

import clientserver.database.Database;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestHandler implements HttpHandler {

    static final private Database db = new Database();

    @Override
    public void handle(HttpExchange response) throws IOException {
        db.initialization();

        if ("GET".equals(response.getRequestMethod())) {
            get(response);
        } else if ("DELETE".equals(response.getRequestMethod())) {
            //del(response);
        } else if ("PUT".equals(response.getRequestMethod())) {
           // put(response);
        } else if ("POST".equals(response.getRequestMethod())) {
            //post(response);
        }

    }

    public void get(HttpExchange response) throws IOException {
        String[] path = response.getRequestURI().getPath().split("/");
        int id_prod = Integer.parseInt(path[-1]);
        System.out.println("get");
        ResultSet productResult = db.getById(id_prod);
        String rs = "";
        try {
            String name_json = "'product_name':"+productResult.getString(1);
            String category_json = "'product_category':"+productResult.getString(2);
            String amount_json = "'product_amount':"+String.valueOf(productResult.getInt(3));
            String price_json = "'product_price':"+String.valueOf(productResult.getDouble(4));
            rs = name_json + "," + category_json + "," + amount_json + "," + price_json;
        } catch (SQLException e){
            response.sendResponseHeaders(404, -1);
        }
        response.sendResponseHeaders(200, rs.length());
        OutputStream outputStream = response.getResponseBody();
        outputStream.write(rs.getBytes());
        outputStream.close();
    }


}
