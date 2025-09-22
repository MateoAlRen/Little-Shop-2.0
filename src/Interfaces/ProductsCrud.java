package Interfaces;

import Models.Products;
import Service.Food;
import java.util.Map;
import javax.swing.*;

public interface ProductsCrud<T> {
    void addProduct();
    void readProduct();
    void update(T obj);
    void delete(int id);
    void search();
    void buyProducts();

}
