package at.htl;

import at.htl.library.model.Book;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
public class DataTest {

    @Inject
    DataSource dataSource;
    @Test
    public void test01_items_are_correct() {
        Table items = new Table(this.dataSource,"item");

        assertThat(items).column("name").hasValues("How TO","Eine kleine Nachtmusik","The Great War");
        assertThat(items).column("composer").hasValues(null,"Mozart","Sabaton");
        assertThat(items).column("author").hasValues("Randall Munroe",null,null);
        assertThat(items).hasNumberOfRows(3);//3 exist already;
    }

    @Test
    public void test02_exemplars_are_correct() {
        Table exemplar = new Table(this.dataSource, "exemplar");

        assertThat(exemplar).column("item_id").hasValues(1,1,2,2,3,3);
        assertThat(exemplar).hasNumberOfRows(6);
    }

}
