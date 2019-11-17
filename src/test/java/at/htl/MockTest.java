package at.htl;

import at.htl.library.business.ExemplarDao;
import at.htl.library.business.ItemDao;
import at.htl.library.model.Book;
import at.htl.library.model.CD;
import at.htl.library.model.Item;
import io.quarkus.test.junit.QuarkusTest;
import org.hibernate.PessimisticLockException;
import org.hibernate.QueryTimeoutException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class MockTest {

    static ItemDao itemDaoMock = mock(ItemDao.class);
    static ExemplarDao exemplarDaoMock = mock(ExemplarDao.class);
    static Book b = new Book("test",5,"test","test",5);
    @BeforeAll
    public static void init(){
        EntityManager mocked = mock(EntityManager.class);
        itemDaoMock.em = mocked;
        Query mockedQuery = mock(Query.class);

        EntityManager anothermocked  =mock(EntityManager.class);
        exemplarDaoMock.em =anothermocked;
        when(mockedQuery.getResultList()).thenThrow(new QueryTimeoutException("test",null,null));
        when(anothermocked.createNamedQuery(anyString())).thenReturn(mockedQuery);

        Mockito.doThrow(new PessimisticLockException("test",null,null)).when(mocked).persist(any(CD.class));

        when(itemDaoMock.getBook(anyLong())).thenReturn(b);
    }

    @Test
    public void t01_insertMock(){
        Book book = itemDaoMock.getBook(1);

        assertThat(book).hasFieldOrPropertyWithValue("genre","test");
    }

    @Test
    public void t02_saveCD_is_locked_return_null(){
        CD cd= itemDaoMock.saveCD(new CD());
        // exception gets caught , should be null
        assertThat(cd).isNull();
    }
    @Test
    public void t03_getAllItems_return_null(){
       List<Item> items = itemDaoMock.get();
        System.out.println(items);
        // exception gets caught, should be empty list
        assertThat(items).isEmpty();
    }
}
