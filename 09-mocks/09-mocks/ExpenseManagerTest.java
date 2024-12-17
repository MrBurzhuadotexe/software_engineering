package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.net.ConnectException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.IntToLongFunction;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.LongConversion;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import put.io.students.fancylibrary.service.FancyService;

public class ExpenseManagerTest {
    @Test
    public void calculateTotal() {
        ExpenseRepository mock = mock(ExpenseRepository.class);
        ArrayList<Expense> expenses = new ArrayList<Expense>();
        for (int i = 1; i <= 3; i++) {
            expenses.add(new Expense("test", "test", 300));
        }
        when(mock.getExpenses()).thenReturn(expenses);
        FancyService fancyService = new FancyService();
        ExpenseManager expenseManager = new ExpenseManager(mock, fancyService);
        assertEquals(900, expenseManager.calculateTotal());
    }

    @Test
    public void calculateTotalForCategory() {
        ExpenseRepository mock = mock(ExpenseRepository.class);
        ArrayList<Expense> home = new ArrayList<Expense>();
        ArrayList<Expense> car = new ArrayList<Expense>();
        for (int i = 0; i < 3; i++) {
            home.add(new Expense("test", "Home", 300));
            car.add(new Expense("test", "Car", 200));
        }
        when(mock.getExpensesByCategory(anyString())).thenReturn(Collections.emptyList());
        when(mock.getExpensesByCategory(eq("Home"))).thenReturn(home);
        when(mock.getExpensesByCategory(eq("Car"))).thenReturn(car);
        FancyService fancyService = new FancyService();
        ExpenseManager expenseManager = new ExpenseManager(mock, fancyService);
        assertEquals(900, expenseManager.calculateTotalForCategory("Home"));
        assertEquals(600, expenseManager.calculateTotalForCategory("Car"));
        assertEquals(0, expenseManager.calculateTotalForCategory("Sport"));
    }

    @Test
    public void calculateTotalInDollars() throws ConnectException {
        ExpenseRepository mock = mock(ExpenseRepository.class);
        FancyService mock2 = mock(FancyService.class);
        ArrayList<Expense> expenses = new ArrayList<Expense>();
        for (int i = 1; i <= 3; i++) {
            expenses.add(new Expense("test", "test", 300));
        }
        when(mock.getExpenses()).thenReturn(expenses);
        when(mock2.convert(anyDouble(), eq("PLN"), eq("USD"))).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();
                double con = (double) args[0] * 4;
                return con;
            }
        });//.thenThrow(new ConnectException());
        ExpenseManager expenseManager = new ExpenseManager(mock, mock2);
        expenseManager.calculateTotalInDollars();
        assertEquals(3600, expenseManager.calculateTotalInDollars());
    }
}
