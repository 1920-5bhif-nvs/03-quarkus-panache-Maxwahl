package at.htl;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;
import javax.inject.Inject;
import javax.sql.DataSource;

import static io.restassured.RestAssured.given;
import static org.assertj.db.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class DataModelTest {


    @Inject
    DataSource dataSource;
    @Test
    public void test01_correct_person() {
        Table person = new Table(this.dataSource, "person");

        assertThat(person).column("id").isNumber(true);
        assertThat(person).column("name").isText(true);
        assertThat(person).hasNumberOfColumns(2);
    }

    @Test
    public void test02_correct_exemplar() {
        Table exemplar = new Table(this.dataSource, "exemplar");

        assertThat(exemplar).column("id").isNumber(true);
        assertThat(exemplar).column("weariness").isText(true);
        assertThat(exemplar).column("item_id").isNumber(true);
        assertThat(exemplar).hasNumberOfColumns(3);
    }

    @Test
    public void test03_correct_item() {
        Table item = new Table(this.dataSource, "item");
        //just some columns
        assertThat(item).column("id").isNumber(true);
        assertThat(item).column("name").isText(true);
        assertThat(item).column("composer").isText(true);
        assertThat(item).column("runtime").isNumber(true);
        assertThat(item).hasNumberOfColumns(9);
    }


    @Test
    public void test04_correct_loan() {
        Table loan = new Table(this.dataSource, "loan");

        assertThat(loan).column("id").isNumber(true);
        assertThat(loan).column("dor").isDate((true));
        assertThat(loan).column("person_id").isNumber(true);
        assertThat(loan).hasNumberOfColumns(5);
    }

}