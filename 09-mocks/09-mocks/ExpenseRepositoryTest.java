package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;

public class ExpenseRepositoryTest {

    @Test
    void loadExpenses() {
        IFancyDatabase mock = mock(IFancyDatabase.class);
        when(mock.queryAll()).thenReturn(Collections.emptyList());
        ExpenseRepository repository = new ExpenseRepository(mock);
        repository.loadExpenses();
        InOrder inOrder = inOrder(mock);
        inOrder.verify(mock).connect();
        inOrder.verify(mock).queryAll();
        inOrder.verify(mock).close();
        assertTrue(repository.getExpenses().isEmpty());
    }

    @Test
    void saveExpenses() {
        IFancyDatabase mock = mock(IFancyDatabase.class);
        when(mock.queryAll()).thenReturn(Collections.emptyList());
        ExpenseRepository repository = new ExpenseRepository(mock);
        for (int i = 1; i <= 5; i++)
        {
            Expense expense = new Expense();
            repository.addExpense(expense);
        }
        repository.saveExpenses();
        InOrder inOrder = inOrder(mock);
        inOrder.verify(mock).connect();
        verify(mock, times(5)).persist(any(Expense.class));
        inOrder.verify(mock).close();
    }
}
